package com.arul.finance_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arul.finance_backend.dtos.DashBoardResponseDto;
import com.arul.finance_backend.service.DashBoardService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/dashBoard")
public class DashBoardController {
    

    @Autowired
    DashBoardService dashBoardService;


    @GetMapping("/{userId}")
    public DashBoardResponseDto getDashboard(@PathVariable Long userId) {
        return dashBoardService.getDashboard(userId);
    }

}
