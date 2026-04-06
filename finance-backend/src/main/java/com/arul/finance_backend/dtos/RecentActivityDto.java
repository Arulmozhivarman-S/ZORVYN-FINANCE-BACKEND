package com.arul.finance_backend.dtos;

import java.util.List;

import com.arul.finance_backend.model.Transactions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RecentActivityDto {

    List<Transactions> recentActivity;
        
}
