package com.example.candidateonboardingsystem.dto;

import lombok.Data;

@Data
public class EducationalInfoRequest {
    private String highestDegree;
    private String university;
    private int yearOfGraduation;
}
