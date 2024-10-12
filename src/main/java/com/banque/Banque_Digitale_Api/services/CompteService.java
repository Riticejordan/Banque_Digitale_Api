package com.banque.Banque_Digitale_Api.services;

import com.banque.Banque_Digitale_Api.Exception.ClientNotFoundException;
import com.banque.Banque_Digitale_Api.Exception.CompteNotFoundException;
import com.banque.Banque_Digitale_Api.Exception.SoldeNotFoundException;
import com.banque.Banque_Digitale_Api.dto.CompteCourantDto;
import com.banque.Banque_Digitale_Api.entities.Compte;
import com.banque.Banque_Digitale_Api.entities.CompteCourant;
import com.banque.Banque_Digitale_Api.entities.CompteEpargne;

import java.util.List;

public interface CompteService {

    CompteCourantDto saveCompteCourrant(double solde, double decouvert, Long clientId) throws ClientNotFoundException;
    CompteEpargne saveCompteEpargne(double solde, double touxInteret, Long clientId) throws ClientNotFoundException;
    Compte getCompte(String compteid) throws CompteNotFoundException;
    void debit(String compteId, double montant,String description) throws SoldeNotFoundException, CompteNotFoundException;
    void credit(String compteId, double montant,String description) throws CompteNotFoundException;
    void transfert(String compteIdSource,String compteIdDestination, double solde) throws SoldeNotFoundException, CompteNotFoundException;
    List<Compte> compteList();
}
