package com.ufc.pix.service;

import com.ufc.pix.dto.CreateTransactionByIdDto;
import com.ufc.pix.dto.CreateTransactionByPixDto;
import com.ufc.pix.dto.SearchTrasactionDto;
import com.ufc.pix.dto.ViewTransactionDto;
import com.ufc.pix.model.User;

import java.util.List;
import java.util.UUID;

public interface TransactionService {

    void createById(CreateTransactionByIdDto userDto);

    List<ViewTransactionDto> list(SearchTrasactionDto dto);

    void createByPix(UUID userSenderId, CreateTransactionByPixDto dto);

}
