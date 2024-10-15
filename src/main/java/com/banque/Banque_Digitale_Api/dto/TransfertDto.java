package com.banque.Banque_Digitale_Api.dto;

public class TransfertDto {

    private String compteSource;
    private String compteDestination;
    private double montant;
    private String description;

    public String getCompteSource() {
        return compteSource;
    }

    public void setCompteSource(String compteSource) {
        this.compteSource = compteSource;
    }

    public String getCompteDestination() {
        return compteDestination;
    }

    public void setCompteDestination(String compteDestination) {
        this.compteDestination = compteDestination;
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
