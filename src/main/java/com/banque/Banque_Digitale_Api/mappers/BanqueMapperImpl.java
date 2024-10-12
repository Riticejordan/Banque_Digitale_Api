package com.banque.Banque_Digitale_Api.mappers;

import com.banque.Banque_Digitale_Api.dto.ClientDto;
import com.banque.Banque_Digitale_Api.dto.CompteCourantDto;
import com.banque.Banque_Digitale_Api.dto.CompteEpargneDto;
import com.banque.Banque_Digitale_Api.entities.Client;
import com.banque.Banque_Digitale_Api.entities.CompteCourant;
import com.banque.Banque_Digitale_Api.entities.CompteEpargne;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class BanqueMapperImpl {
    public ClientDto deClient(Client client){
        ClientDto clientDto =new ClientDto();
        BeanUtils.copyProperties(client,clientDto);
        return clientDto;
    }
    public Client deClientDto(ClientDto clientDto){
        Client client =new Client();
        BeanUtils.copyProperties(clientDto,client);
        return client;
    }


    public CompteEpargneDto deCompteEpargne(CompteEpargne compteEpargne){
        return null;
    }

    public CompteEpargne  deCompteEpargneDto(CompteEpargneDto compteEpargneDto){
        return null;

    }

    public CompteCourantDto deCompteCourant(CompteCourant compteCourant){
        return null;
    }

    public CompteCourant deCompteCourantDto(CompteCourantDto compteCourantDto){

        return null;
    }
}
