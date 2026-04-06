package com.arul.finance_backend.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MonthlyTrendDto {
    
    public int year;
    public int month;
    public Double total;

}
