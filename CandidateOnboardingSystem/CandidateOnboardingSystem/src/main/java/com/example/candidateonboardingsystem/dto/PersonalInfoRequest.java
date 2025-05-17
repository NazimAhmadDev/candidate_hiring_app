package com.example.candidateonboardingsystem.dto;

import lombok.Data;

@Data
public class PersonalInfoRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNo;
}

