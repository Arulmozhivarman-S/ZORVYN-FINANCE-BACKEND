package com.arul.finance_backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arul.finance_backend.dtos.DashBoardResponseDto;
import com.arul.finance_backend.repository.TransactionsRepository;

@Service
public class DashBoardService {
    
    @Autowired
    TransactionsRepository transactionsRepository;


    public DashBoardResponseDto getDashboard(Long userId){
        return new DashBoardResponseDto();
    }

   
}
