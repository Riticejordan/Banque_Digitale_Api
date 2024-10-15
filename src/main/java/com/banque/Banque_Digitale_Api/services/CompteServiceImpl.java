package com.banque.Banque_Digitale_Api.services;

import com.banque.Banque_Digitale_Api.Exception.ClientNotFoundException;
import com.banque.Banque_Digitale_Api.Exception.CompteNotFoundException;
import com.banque.Banque_Digitale_Api.Exception.SoldeNotFoundException;
import com.banque.Banque_Digitale_Api.dto.CompteCourantDto;
import com.banque.Banque_Digitale_Api.dto.CompteDto;
import com.banque.Banque_Digitale_Api.dto.CompteEpargneDto;
import com.banque.Banque_Digitale_Api.entities.*;
import com.banque.Banque_Digitale_Api.enums.TypeOperation;
import com.banque.Banque_Digitale_Api.mappers.BanqueMapperImpl;
import com.banque.Banque_Digitale_Api.repositories.ClientRepository;
import com.banque.Banque_Digitale_Api.repositories.CompteRepository;
import com.banque.Banque_Digitale_Api.repositories.OperationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CompteServiceImpl implements CompteService {


    @Autowired
    private CompteRepository compteRepository;
    @Autowired
    private OperationRepository operationRepository;
    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private BanqueMapperImpl banqueMapperImpl;

    @Override
    public CompteCourantDto saveCompteCourrant(double solde, double decouvert, Long clientId) throws ClientNotFoundException {
        Client client =clientRepository.findById(clientId).orElse(null);
        if (client==null)
            throw new ClientNotFoundException("Client non reconnu");
        CompteCourant compteCourant=new CompteCourant();
        compteCourant.setId(UUID.randomUUID().toString());
        compteCourant.setDateCreation(new Date());
        compteCourant.setSolde(solde);
        compteCourant.setDecouvert(decouvert);
        compteCourant.setClient(client);
        CompteCourant savedCompteCourant= compteRepository.save(compteCourant);
        return banqueMapperImpl.deCompteCourant(savedCompteCourant);
    }

    @Override
    public CompteEpargneDto saveCompteEpargne(double solde, double touxInteret, Long clientId) throws ClientNotFoundException {
        Client client =clientRepository.findById(clientId).orElse(null);
        if (client==null)
            throw new ClientNotFoundException("Client non reconnu");
        CompteEpargne compteEpargne=new CompteEpargne();
        compteEpargne.setId(UUID.randomUUID().toString());
        compteEpargne.setDateCreation(new Date());
        compteEpargne.setSolde(solde);
        compteEpargne.setTouxInteret(touxInteret);
        compteEpargne.setClient(client);
        CompteEpargne savedCompteEpargne= compteRepository.save(compteEpargne);
        return banqueMapperImpl.deCompteEpargne(savedCompteEpargne);
    }


    @Override
    public CompteDto getCompte(String compteId) throws CompteNotFoundException {
        Compte compte = compteRepository.findById(compteId)
                .orElseThrow(() -> new CompteNotFoundException("Compte non reconnu"));

        if (compte instanceof CompteEpargne) {

            CompteEpargne compteEpargne =(CompteEpargne) compte;
            return banqueMapperImpl.deCompteEpargne(compteEpargne);
        } else {
           CompteCourant compteCourant=(CompteCourant) compte;
           return banqueMapperImpl.deCompteCourant(compteCourant);
        }
    }

    @Override
    public List<CompteDto> compteList(){
        List<Compte> comptes = compteRepository.findAll();
        List<CompteDto> compteDtos= comptes.stream().map(compte -> {
            if (compte instanceof CompteEpargne ){
                CompteEpargne compteEpargne=(CompteEpargne) compte;
                return banqueMapperImpl.deCompteEpargne(compteEpargne);
            }else{
                CompteCourant compteCourant=(CompteCourant) compte;
                return banqueMapperImpl.deCompteCourant(compteCourant);

            }
        }).collect(Collectors.toList());
        return compteDtos;
    }


    @Override
    public void debit(String compteId, double montant, String description) throws SoldeNotFoundException, CompteNotFoundException {
        Compte compte = compteRepository.findById(compteId)
                .orElseThrow(() -> new CompteNotFoundException("Compte non reconnu"));
        if (compte.getSolde()<montant )
            throw new SoldeNotFoundException("Solde insuffisant");

        Operation operation =new Operation();
        operation.setTypeOperation(TypeOperation.DEBIT);
        operation.setMontant(montant);
        operation.setDateOperation(new Date());
        operation.setDescription(description);
        operation.setCompte(compte);
        operationRepository.save(operation);
        compte.setSolde(compte.getSolde()-montant);
        compteRepository.save(compte);

    }

    @Override
    public void credit(String compteId, double montant, String description) throws CompteNotFoundException {

        Compte compte = compteRepository.findById(compteId)
                .orElseThrow(() -> new CompteNotFoundException("Compte non reconnu"));
        Operation operation =new Operation();
        operation.setTypeOperation(TypeOperation.CREDIT);
        operation.setMontant(montant);
        operation.setDateOperation(new Date());
        operation.setDescription(description);
        operation.setCompte(compte);
        operationRepository.save(operation);
        compte.setSolde(compte.getSolde()+montant);
        compteRepository.save(compte);

    }

    @Override
    public void transfert(String compteIdSource, String compteIdDestination, double solde) throws SoldeNotFoundException, CompteNotFoundException {
        debit(compteIdSource,solde,"transfert vers le compte"+compteIdDestination);
        credit(compteIdDestination,solde,"transfert de "+compteIdSource);
    }


}


