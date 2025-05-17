package com.example.candidateonboardingsystem.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobOfferPayload implements Serializable {
    private String email;
    private String candidateName;
    private String jobTitle;
    private String offerDetails;
}

