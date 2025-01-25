package com.ufc.pix.service.impl;

import com.ufc.pix.dto.CreateTransactionByIdDto;
import com.ufc.pix.dto.CreateTransactionByPixDto;
import com.ufc.pix.dto.SearchTrasactionDto;
import com.ufc.pix.dto.ViewTransactionDto;
import com.ufc.pix.exception.BusinessException;
import com.ufc.pix.model.Transaction;
import com.ufc.pix.repository.AccountRepository;
import com.ufc.pix.repository.TransactionRepository;
import com.ufc.pix.repository.UserRepository;
import com.ufc.pix.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public void create(CreateTransactionByIdDto userDto) {
        if (userDto.getValue() < 1){
            throw new BusinessException("transfer value is less than 1");
        }

        var receiver = accountRepository.findById(userDto.getReceiver_id()).orElseThrow(
                () -> new BusinessException("Account receiver not found", HttpStatus.NOT_FOUND)
        );
        var sender = accountRepository.findById(userDto.getSender_id()).orElseThrow(
                () -> new BusinessException("Account sender not found", HttpStatus.NOT_FOUND)
        );

        if (sender.getBalance() < userDto.getValue()){
            throw new BusinessException("the shipping account does not have sufficient balance");
        }

        receiver.setBalance(receiver.getBalance() + userDto.getValue());
        sender.setBalance(sender.getBalance() - userDto.getValue());
        var transactionToSave = new Transaction(receiver,sender,userDto.getValue());
        this.transactionRepository.save(transactionToSave);
    }

    @Override
    public List<ViewTransactionDto> list(SearchTrasactionDto dto) {
        return this.transactionRepository.list(
                dto.getId(),
                dto.getValueStart(),
                dto.getValueEnd(),
                dto.getTransactionDateStart(),
                dto.getTransactionDateEnd(),
                dto.getSenderId(),
                dto.getReceiverId()
        ).stream().map(Transaction::toView).toList();
    }

    @Override
    public void createByPix(CreateTransactionByPixDto dto) {

    }
}
