package com.banque.Banque_Digitale_Api.dto;

import com.banque.Banque_Digitale_Api.entities.Compte;
import com.banque.Banque_Digitale_Api.enums.TypeOperation;
import jakarta.persistence.*;

import java.util.Date;


public class OperationDto {

    private Long id;
    private Date dateOperation;
    private Double montant;
    private TypeOperation typeOperation;
    private String description;


    public Date getDateOperation() {
        return dateOperation;
    }

    public void setDateOperation(Date dateOperation) {
        this.dateOperation = dateOperation;
    }

    public Double getMontant() {
        return montant;
    }

    public void setMontant(Double montant) {
        this.montant = montant;
    }

    public TypeOperation getTypeOperation() {
        return typeOperation;
    }

    public void setTypeOperation(TypeOperation typeOperation) {
        this.typeOperation = typeOperation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
