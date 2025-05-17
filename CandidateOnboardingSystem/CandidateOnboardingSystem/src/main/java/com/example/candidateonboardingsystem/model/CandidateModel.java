package com.example.candidateonboardingsystem.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "candidates")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CandidateModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "First name is required")
    private String first_name;

    @NotBlank(message = "Last name is required")
    private String last_name;

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Phone is required")
    private String phone_no;

    @Enumerated(EnumType.STRING)
    private Status status;

    //private boolean hired;

    public enum Status {
        APPLIED,
        INTERVIEWED,
        OFFERED,
        ONBOARDED
    }

    @Enumerated(EnumType.STRING)
    private OnboardingStatus onboardingStatus;

    public enum OnboardingStatus {
        PENDING,
        COMPLETED,
        IN_PROGRESS,
        FAILED
    }

    private LocalDateTime created_at;

    private LocalDateTime updated_at;

}

