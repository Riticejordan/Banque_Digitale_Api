package com.banque.Banque_Digitale_Api.services;

import com.banque.Banque_Digitale_Api.Exception.ClientNotFoundException;
import com.banque.Banque_Digitale_Api.Exception.CompteNotFoundException;
import com.banque.Banque_Digitale_Api.Exception.SoldeNotFoundException;
import com.banque.Banque_Digitale_Api.dto.CompteCourantDto;
import com.banque.Banque_Digitale_Api.dto.CompteDto;
import com.banque.Banque_Digitale_Api.dto.CompteEpargneDto;


import java.util.List;

public interface CompteService {

    CompteCourantDto saveCompteCourrant(double solde, double decouvert, Long clientId) throws ClientNotFoundException;
    CompteEpargneDto saveCompteEpargne(double solde, double touxInteret, Long clientId) throws ClientNotFoundException;
    CompteDto getCompte(String compteId) throws CompteNotFoundException;
    void debit(String compteId, double montant,String description) throws SoldeNotFoundException, CompteNotFoundException;
    void credit(String compteId, double montant,String description) throws CompteNotFoundException;
    void transfert(String compteIdSource,String compteIdDestination, double solde) throws SoldeNotFoundException, CompteNotFoundException;
    List<CompteDto> compteList();
}
