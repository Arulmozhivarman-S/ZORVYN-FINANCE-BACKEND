package com.arul.finance_backend.dtos;

import com.arul.finance_backend.enums.Category;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryResponseDto {

    private Category category;
    private Double total;

}
