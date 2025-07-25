package com.ufc.pix.service.impl;

import com.ufc.pix.Observer.EmailSubject;
import com.ufc.pix.dto.CreateAccountDto;
import com.ufc.pix.dto.UpdateAccountDto;
import com.ufc.pix.dto.UpdateDailyLimitDto;
import com.ufc.pix.dto.ViewAccountDto;
import com.ufc.pix.enumeration.AccountStatus;
import com.ufc.pix.enumeration.UserStatus;
import com.ufc.pix.exception.BusinessException;
import com.ufc.pix.model.Account;
import com.ufc.pix.model.Transaction;
import com.ufc.pix.repository.AccountRepository;
import com.ufc.pix.repository.TransactionRepository;
import com.ufc.pix.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AccountService extends EmailSubject {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TransactionServiceImpl transactionService;

    public void createAccount(CreateAccountDto accountDTO) {
        var account = this.accountRepository.findByAgencyAndNumber(accountDTO.getAgency(), accountDTO.getNumber());

        if (account.isPresent()) throw new BusinessException("There is already an account with that number and agency");

        //verifica se o usuario existe
        var user = this.userRepository.findById(accountDTO.getUserId()).orElseThrow(
                () -> new BusinessException("User not found", HttpStatus.NOT_FOUND)
        );
        //verifica se o usuario esta ativo ou foi deletado
        if (user.getStatus() != UserStatus.ACTIVE) {
            throw new BusinessException("User is not active");
        }

        //verifica se esse usuario ja tem conta
        if (user.getAccount() != null) {
            throw new BusinessException("User already has an account");
        }

        Account accountToSave = new Account();
        accountToSave.setUser(user);
        accountToSave.setAgency(accountDTO.getAgency());
        accountToSave.setBalance(accountDTO.getBalance());
        accountToSave.setType(accountDTO.getType());
        accountToSave.setNumber(accountDTO.getNumber());
        accountToSave.setPixKeys(null);
        accountToSave.setStatus(AccountStatus.ACTIVE);
        accountToSave.setDailyValueLimit(1000.00);
        accountToSave.setPassword(passwordEncoder.encode(accountDTO.getPassword())); //adicione senha com hash

        var savedAccount = accountRepository.save(accountToSave);
        user.setAccount(savedAccount);
        userRepository.save(user);

        notifyObservers(savedAccount.getUser().getEmail(), "Conta cadastrada",
                String.format(
                        "Olá %s,\n\n"
                                + "Bem-vindo(a)! Sua conta foi cadastrada com sucesso e está ativa. "
                                + "Agora você pode acessar a plataforma e aproveitar todos os nossos recursos.\n\n"
                                + "Se precisar de qualquer suporte, não hesite em entrar em contato conosco.\n\n"
                                + "Atenciosamente,\n"
                                + "Equipe PIX",
                        savedAccount.getUser().getName()
                ));
    }

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public Optional<Account> getOneAccount(UUID id) {
        return Optional.ofNullable(accountRepository.findAccountsById(id));
    }

    public void updateDailyLimit(UUID accountId, UpdateDailyLimitDto dto) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new BusinessException("Account not found", HttpStatus.NOT_FOUND));

        if (dto.getDailyValueLimit() > account.getDailyValueLimit() * 2) {
            throw new BusinessException("The requested limit was not approved");
        }

        account.setDailyValueLimit(dto.getDailyValueLimit());
        accountRepository.save(account);
    }

    public void updateAccount(UUID id, UpdateAccountDto dto) {
        var account = this.accountRepository.findById(id).orElseThrow(() -> new BusinessException("Account not found", HttpStatus.NOT_FOUND));

        if (dto.getAgency() != null) account.setAgency(dto.getAgency());
        if (dto.getType() != null) account.setAgency(dto.getAgency());
        if (dto.getPassword() != null) account.setPassword(passwordEncoder.encode(dto.getPassword()));
        if (dto.getNumber() != null) account.setNumber(dto.getNumber());

        this.accountRepository.save(account);

    }

    public void deleteAccount(UUID id) {
        var account = this.accountRepository.findById(id).orElseThrow(() -> new BusinessException("Account not found", HttpStatus.NOT_FOUND));
        account.setStatus(AccountStatus.DELETED);
        this.accountRepository.save(account);
    }

    public void blockAccount(UUID id) {
        var account = this.accountRepository.findById(id).orElseThrow(() -> new BusinessException("Account not found", HttpStatus.NOT_FOUND));
        account.setStatus(AccountStatus.BLOCKED);
        var savedAccount = this.accountRepository.save(account);

        notifyObservers(savedAccount.getUser().getEmail(), "Conta cadastrada",
                String.format(
                        "Olá %s,\n\n"
                                + "Sua conta foi bloqueada!. \n\n"
                                + "Para mais informação sobre o bloqueio e como desbloquear, entre em contato conosco.\n\n"
                                + "Atenciosamente,\n"
                                + "Equipe PIX",
                        savedAccount.getUser().getName()
                ));
    }

    public ViewAccountDto findById(UUID id) {
        return this.accountRepository.findById(id).orElseThrow(
                () -> new BusinessException("Account not found", HttpStatus.NOT_FOUND)).toView();
    }

    public void reportAccount(UUID id) {
        var account = this.accountRepository.findById(id).orElseThrow(() -> new BusinessException("Account not found", HttpStatus.NOT_FOUND));
        reverseTransaction(account.getId());
        switch (account.getStatus()) {
            case ACTIVE:
                account.setStatus(AccountStatus.SUSPICIOUS);
                this.accountRepository.save(account);
                break;
            case SUSPICIOUS:
                account.setStatus(AccountStatus.BLOCKED);
                this.accountRepository.save(account);
                break;
            case BLOCKED:
                break;
            default:
                throw new BusinessException("Invalid status");
        }
    }

    public void reverseTransaction(UUID id) {
        var account = this.accountRepository.findAccountsById(id);
        // Estornar a última transação recebida
        Transaction lastTransaction = transactionService.getLastTransactionReceived(account.getId());
        Account senderAccount = lastTransaction.getSender();
        Account receiverAccount = lastTransaction.getReceiver();
        double amount = lastTransaction.getTransferValue();

        // Reverter a transação
        senderAccount.setBalance(senderAccount.getBalance() + amount);
        receiverAccount.setBalance(receiverAccount.getBalance() - amount);

        // Salvar as contas atualizadas
        accountRepository.save(senderAccount);
        accountRepository.save(receiverAccount);
    }
}