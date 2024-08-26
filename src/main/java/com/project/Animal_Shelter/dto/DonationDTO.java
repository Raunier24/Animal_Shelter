package com.project.Animal_Shelter.dto;

import java.math.BigDecimal;

public class DonationDTO {

    private Long id;
    private String donorName;
    private String donorEmail;
    private BigDecimal amount;
    private String message;

    public DonationDTO() {
    }

    public DonationDTO(Long id, String donorName, String donorEmail, BigDecimal amount, String message) {
        this.id = id;
        this.donorName = donorName;
        this.donorEmail = donorEmail;
        this.amount = amount;
        this.message = message;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDonorName() {
        return donorName;
    }

    public void setDonorName(String donorName) {
        this.donorName = donorName;
    }

    public String getDonorEmail() {
        return donorEmail;
    }

    public void setDonorEmail(String donorEmail) {
        this.donorEmail = donorEmail;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
