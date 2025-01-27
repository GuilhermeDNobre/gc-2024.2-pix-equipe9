package com.ufc.pix.service.impl;

import com.ufc.pix.dto.CreateTransactionByIdDto;
import com.ufc.pix.dto.CreateTransactionByPixDto;
import com.ufc.pix.dto.SearchTrasactionDto;
import com.ufc.pix.dto.ViewTransactionDto;
import com.ufc.pix.enumeration.AccountStatus;
import com.ufc.pix.enumeration.TransactionStatus;
import com.ufc.pix.exception.BusinessException;
import com.ufc.pix.model.Account;
import com.ufc.pix.model.PixKey;
import com.ufc.pix.model.Transaction;
import com.ufc.pix.repository.AccountRepository;
import com.ufc.pix.repository.PixKeyRepository;
import com.ufc.pix.repository.TransactionRepository;
import com.ufc.pix.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PixKeyRepository pixKeyRepository;

    @Override
    public void createById(CreateTransactionByIdDto dto) {
        if (dto.getValue() < 1) {
            throw new BusinessException("transfer value is less than 1");
        }

        var receiver = validateAccount(dto.getReceiverId());
        var sender = validateAccount(dto.getSenderId());

        if (dto.getSendDate().isAfter(LocalDate.now())) {
            scheduleTransaction(new Transaction(receiver, sender, dto.getValue(), dto.getSendDate(), LocalDateTime.now()));
            return;
        }

        finishTransaction(new Transaction(receiver, sender, dto.getValue(), dto.getSendDate(), LocalDateTime.now()));
    }
    private Account searchAccountByUserId(UUID userId){
        return this.accountRepository.findByUserId(userId).orElseThrow(
                () -> new BusinessException("User does not have an account", HttpStatus.NOT_FOUND));
    }
    private PixKey searchPixKeyByKey(String key){
        return this.pixKeyRepository.findByKeyValue(key).orElseThrow(
                () -> new BusinessException("Pix key " + key + " not found ", HttpStatus.NOT_FOUND));
    }
    @Override
    public void createByPix(UUID userSenderId, CreateTransactionByPixDto dto) {

        if (dto.getValue() < 1) {
            throw new BusinessException("transfer value is less than 1");
        }

        //busca a conta vinculada a esse usuario
        var sender = searchAccountByUserId(userSenderId);
        //busca a chave pix informada
        var pixKey = searchPixKeyByKey(dto.getPixKey());
        //busca conta de recebimento
        var receiver = validateAccount(pixKey.getAccount().getId());
        validateAccount(sender.getId());

        if (dto.getSendDate().isAfter(LocalDate.now())) {
            scheduleTransaction(new Transaction(sender, receiver,dto.getValue(), dto.getSendDate(), LocalDateTime.now()));
            return;
        }

        finishTransaction(new Transaction(sender, receiver, dto.getValue(), dto.getSendDate(), LocalDateTime.now()));
    }

    private Account validateAccount(UUID accountId) {
        var account = accountRepository.findById(accountId).orElseThrow(
                () -> new BusinessException("Account with id " + accountId + " not found", HttpStatus.NOT_FOUND)
        );

        if (!account.getStatus().equals(AccountStatus.ACTIVE)) {
            throw new BusinessException("Account with id " + accountId + " is not active");
        }
        return account;
    }

    private void updateBalance(Account sender, Account receiver, Double value) {
        if (sender.getBalance() < value) {
            throw new BusinessException("the shipping account does not have sufficient balance");
        }

        receiver.setBalance(receiver.getBalance() + value);
        sender.setBalance(sender.getBalance() - value);

        this.accountRepository.save(receiver);
        this.accountRepository.save(sender);
    }

    @Override
    public List<ViewTransactionDto> list(SearchTrasactionDto dto) {
        return this.transactionRepository.list(
                dto.getId(),
                dto.getTransferValueStart(),
                dto.getTransferValueEnd(),
                dto.getStatus(),
                dto.getCreatedAtStart(),
                dto.getCreatedAtEnd(),
                dto.getFinishedAtStart(),
                dto.getFinishedAtEnd(),
                dto.getSendDateStart(),
                dto.getSendDateEnd(),
                dto.getSenderId(),
                dto.getReceiverId()
        ).stream().map(Transaction::toView).toList();
    }


    private void finishTransaction(Transaction transaction) {
        updateBalance(transaction.getSender(), transaction.getReceiver(), transaction.getTransferValue());
        transaction.setFinishedAt(LocalDateTime.now());
        transaction.setStatus(TransactionStatus.COMPLETED);
        this.transactionRepository.save(transaction);
    }

    private void scheduleTransaction(Transaction transaction) {
        transaction.setFinishedAt(null);
        transaction.setStatus(TransactionStatus.PENDING);
        this.transactionRepository.save(transaction);
    }

    // Agendador que executa às 5h da manhã todos os dias
    @Scheduled(cron = "0 0 5 * * *") // Segundos, Minutos, Hora, Dia do mês, Mês, Dia da semana
    public void processScheduledTransactions() {
        var scheduledTransactions = this.transactionRepository.findByStatus(TransactionStatus.PENDING);

        for (var transaction : scheduledTransactions) {
            if (LocalDate.from(transaction.getSendDate()).equals(LocalDate.now())) {
                finishTransaction(transaction);
            }
        }
    }

    public void cancelPendingTransaction(UUID transactionId) {
        var transaction = this.transactionRepository.findById(transactionId).orElseThrow();
//        if(transaction.getStatus().equals(TransactionStatus.PENDING)) {
//            transaction.setStatus(TransactionStatus.CANCELED);
//            this.transactionRepository.save(transaction);
//        }
        transaction.setStatus(TransactionStatus.CANCELED);
        this.transactionRepository.save(transaction);
    }
}
