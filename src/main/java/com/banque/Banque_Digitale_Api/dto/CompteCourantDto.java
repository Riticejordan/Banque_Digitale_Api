package com.banque.Banque_Digitale_Api.dto;


import com.banque.Banque_Digitale_Api.entities.Client;
import com.banque.Banque_Digitale_Api.entities.Operation;
import com.banque.Banque_Digitale_Api.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public  class CompteCourantDto extends CompteDto{
    private String id;
    private double solde;
    private Date  dateCreation;
    private Status status;
    private ClientDto clientDto ;

    double decouvert;



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getSolde() {
        return solde;
    }

    public void setSolde(double solde) {
        this.solde = solde;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public ClientDto getClientDto() {
        return clientDto;
    }

    public void setClientDto(ClientDto clientDto) {
        this.clientDto = clientDto;
    }

    public double getDecouvert() {
        return decouvert;
    }

    public void setDecouvert(double decouvert) {
        this.decouvert = decouvert;
    }
}
