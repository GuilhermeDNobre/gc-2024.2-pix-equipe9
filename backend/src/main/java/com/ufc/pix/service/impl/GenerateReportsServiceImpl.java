package com.ufc.pix.service.impl;

import com.ufc.pix.dto.ViewGenerateReportsDto;
import com.ufc.pix.dto.ViewTransactionDto;
import com.ufc.pix.enumeration.UserStatus;
import com.ufc.pix.exception.BusinessException;
import com.ufc.pix.model.Transaction;
import com.ufc.pix.repository.TransactionRepository;
import com.ufc.pix.repository.UserRepository;
import com.ufc.pix.service.GenerateReportsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class GenerateReportsServiceImpl implements GenerateReportsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public Optional<ViewGenerateReportsDto> generateByUserId(UUID userId, LocalDateTime startDate, LocalDateTime endDate) {
        //verifica se o usuario existe
        var user = this.userRepository.findById(userId).orElseThrow(
                () -> new BusinessException("User not found", HttpStatus.NOT_FOUND)
        );
        //verifica se o usuario esta ativo ou foi deletado
        if (user.getStatus() != UserStatus.ACTIVE){
            throw new BusinessException("User is not active");
        }

        // Filtra as transações do usuário
        List<Transaction> transactions = transactionRepository.findAllByUserId(userId).stream()
                .filter(transaction -> isWithinDateRange(transaction, startDate, endDate))
                .collect(Collectors.toList());

        // Calcula os valores para o relatório
        float initialBalance = 0;
        float totalCredits = (float) transactions.stream()
                .filter(transaction -> transaction.getReceiver().getUser().getId().equals(userId))
                .mapToDouble(Transaction::getTransferValue)
                .sum();
        float totalDebits = (float) transactions.stream()
                .filter(transaction -> transaction.getSender().getUser().getId().equals(userId))
                .mapToDouble(Transaction::getTransferValue)
                .sum();
        float finalBalance = initialBalance + totalCredits - totalDebits;
        int totalTransactions = transactions.size();

        ViewGenerateReportsDto report = new ViewGenerateReportsDto();

        report.setUserName(user.getName());
        report.setUserCpf(user.getCpf());
        report.setUserAgency(String.valueOf(user.getAccount().getAgency()));
        report.setUserAccount(String.valueOf(user.getAccount().getNumber()));
        report.setStartDate(startDate != null ? Date.from(startDate.atZone(java.time.ZoneId.systemDefault()).toInstant()) : null);
        report.setEndDate(endDate != null ? Date.from(endDate.atZone(java.time.ZoneId.systemDefault()).toInstant()) : null);
        report.setTotalCredits(totalCredits);
        report.setTotalDebits(totalDebits);
        report.setTotalTransactions(totalTransactions);
        report.setTransactions(transactions.stream().map(transaction ->
                new ViewTransactionDto(
                        transaction.getId(),
                        transaction.getTransferValue(),
                        transaction.getStatus(),
                        transaction.getSendDate(),
                        transaction.getFinishedAt(),
                        transaction.getSender().toViewInTransaction(),
                        transaction.getReceiver().toViewInTransaction()
                )
        ).collect(Collectors.toList()));

        return Optional.of(report);
    }

    private boolean isWithinDateRange(Transaction transaction, LocalDateTime startDate, LocalDateTime endDate) {
        return (startDate == null || !transaction.getCreatedAt().isBefore(startDate)) &&
                (endDate == null || !transaction.getCreatedAt().isAfter(endDate));
    }
}
