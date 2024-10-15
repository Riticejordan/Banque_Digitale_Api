package com.banque.Banque_Digitale_Api.services;

import com.banque.Banque_Digitale_Api.Exception.ClientNotFoundException;
import com.banque.Banque_Digitale_Api.Exception.CompteNotFoundException;
import com.banque.Banque_Digitale_Api.Exception.SoldeNotFoundException;
import com.banque.Banque_Digitale_Api.dto.ClientDto;
import com.banque.Banque_Digitale_Api.entities.Compte;
import com.banque.Banque_Digitale_Api.entities.CompteCourant;
import com.banque.Banque_Digitale_Api.entities.CompteEpargne;

import java.util.List;

public interface ClientService {
     ClientDto  saveClient(ClientDto clientDto);
     ClientDto getClient(long id) throws ClientNotFoundException;
     List<ClientDto> listeClient();
     ClientDto updateClient(ClientDto clientDto);
     void deleteClient(Long id);
     List<ClientDto> searchClient(String keyword);








}
