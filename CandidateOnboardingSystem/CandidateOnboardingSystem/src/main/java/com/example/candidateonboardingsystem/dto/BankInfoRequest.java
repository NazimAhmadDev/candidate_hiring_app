package com.example.candidateonboardingsystem.dto;

import lombok.Data;

@Data
public class BankInfoRequest {
    private String bankName;
    private String accountNumber;
    private String ifscCode;
}

