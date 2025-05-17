package com.example.candidateonboardingsystem.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "notification")
public class JobOfferNotification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @JoinColumn(name = "candidate_id", referencedColumnName = "id")
    private CandidateModel candidate;

    private boolean sent;

    private LocalDateTime sentAt;

    private int retry_count;

    private String error_message;

}
