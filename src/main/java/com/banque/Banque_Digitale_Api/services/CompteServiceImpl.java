package com.banque.Banque_Digitale_Api.services;

import com.banque.Banque_Digitale_Api.Exception.ClientNotFoundException;
import com.banque.Banque_Digitale_Api.Exception.CompteNotFoundException;
import com.banque.Banque_Digitale_Api.Exception.SoldeNotFoundException;
import com.banque.Banque_Digitale_Api.dto.CompteCourantDto;
import com.banque.Banque_Digitale_Api.entities.*;
import com.banque.Banque_Digitale_Api.enums.TypeOperation;
import com.banque.Banque_Digitale_Api.repositories.ClientRepository;
import com.banque.Banque_Digitale_Api.repositories.CompteRepository;
import com.banque.Banque_Digitale_Api.repositories.OperationRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class CompteServiceImpl implements CompteService {


    @Autowired
    private CompteRepository compteRepository;
    @Autowired
    private OperationRepository operationRepository;
    @Autowired
    private ClientRepository clientRepository;

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
        return savedCompteCourant;
    }

    @Override
    public CompteEpargne saveCompteEpargne(double solde, double touxInteret, Long clientId) throws ClientNotFoundException {
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
        return savedCompteEpargne;
    }


    @Override
    public Compte getCompte(String compteid) throws CompteNotFoundException {
        Compte compte= compteRepository.findById(compteid)
                .orElseThrow(()->new CompteNotFoundException("Compte non reconnu"));

        return compte;
    }

    @Override
    public void debit(String compteId, double montant, String description) throws SoldeNotFoundException, CompteNotFoundException {
        Compte compte =getCompte(compteId);
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

        Compte compte =getCompte(compteId);
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

    @Override
    public List<Compte> compteList(){
        return compteRepository.findAll();
    }
}


