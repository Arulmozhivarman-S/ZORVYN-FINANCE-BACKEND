package com.arul.finance_backend.dtos;

import com.arul.finance_backend.enums.UserRole;
import com.arul.finance_backend.enums.UserStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {


    Long userId;
    UserRole userRole;
    UserStatus userStatus;
    
}
