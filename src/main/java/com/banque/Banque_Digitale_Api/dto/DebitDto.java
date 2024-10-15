package com.banque.Banque_Digitale_Api.dto;

public class DebitDto {

    private String compteId;
    private double montant;
    private String description;

    public String getCompteId() {
        return compteId;
    }

    public void setcompteId(String compteId) {
        this.compteId= compteId;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
