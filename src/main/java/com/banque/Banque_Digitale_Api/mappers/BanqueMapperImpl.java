package com.banque.Banque_Digitale_Api.mappers;

import com.banque.Banque_Digitale_Api.dto.ClientDto;
import com.banque.Banque_Digitale_Api.dto.CompteCourantDto;
import com.banque.Banque_Digitale_Api.dto.CompteEpargneDto;
import com.banque.Banque_Digitale_Api.dto.OperationDto;
import com.banque.Banque_Digitale_Api.entities.Client;
import com.banque.Banque_Digitale_Api.entities.CompteCourant;
import com.banque.Banque_Digitale_Api.entities.CompteEpargne;
import com.banque.Banque_Digitale_Api.entities.Operation;
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
        CompteEpargneDto compteEpargneDto=new CompteEpargneDto();
        BeanUtils.copyProperties(compteEpargne,compteEpargneDto);
        compteEpargneDto.setClientDto(deClient(compteEpargne.getClient()));
        compteEpargneDto.setType(compteEpargne.getClass().getSimpleName());
        return compteEpargneDto;
    }

    public CompteEpargne  deCompteEpargneDto(CompteEpargneDto compteEpargneDto){
        CompteEpargne compteEpargne =new CompteEpargne();
        BeanUtils.copyProperties(compteEpargneDto,compteEpargne);
        compteEpargne.setClient(deClientDto(compteEpargneDto.getClientDto()));
        return null;

    }

    public CompteCourantDto deCompteCourant(CompteCourant compteCourant){
        CompteCourantDto compteCourantDto=new CompteCourantDto();
        BeanUtils.copyProperties(compteCourant,compteCourantDto);
        compteCourantDto.setClientDto(deClient(compteCourant.getClient()));
        compteCourantDto.setType(compteCourant.getClass().getSimpleName());
        return compteCourantDto;
    }

    public CompteCourant deCompteCourantDto(CompteCourantDto compteCourantDto){
        CompteCourant compteCourant=new CompteCourant();
        BeanUtils.copyProperties(compteCourantDto, compteCourant);
        compteCourant.setClient(deClientDto(compteCourantDto.getClientDto()));
        return compteCourant;
    }

    public OperationDto deoperationDto(Operation operation){
        OperationDto operationDto =new OperationDto();
        BeanUtils.copyProperties(operation,operationDto);
        return operationDto;


    }
}
