package com.banque.Banque_Digitale_Api.services;

import com.banque.Banque_Digitale_Api.Exception.CompteNotFoundException;
import com.banque.Banque_Digitale_Api.dto.HistoriqueCompteDto;
import com.banque.Banque_Digitale_Api.dto.OperationDto;

import java.util.List;

public interface OperationService {
    List<OperationDto> historiqueCompte(String compteId);
    HistoriqueCompteDto getHistoriqueCompte(String compteId, int page, int taille) throws CompteNotFoundException;
}
