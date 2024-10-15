package com.banque.Banque_Digitale_Api.services;

import com.banque.Banque_Digitale_Api.Exception.ClientNotFoundException;

import com.banque.Banque_Digitale_Api.dto.ClientDto;
import com.banque.Banque_Digitale_Api.entities.*;

import com.banque.Banque_Digitale_Api.mappers.BanqueMapperImpl;
import com.banque.Banque_Digitale_Api.repositories.ClientRepository;
import com.banque.Banque_Digitale_Api.repositories.CompteRepository;
import com.banque.Banque_Digitale_Api.repositories.OperationRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional
public class ClientServiceImp implements ClientService {

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private CompteRepository compteRepository;
    @Autowired
    private OperationRepository operationRepository;
    @Autowired
    private BanqueMapperImpl banaueMapperImpl;

    // Logger log= (Logger) LoggerFactory.getLogger(this.getClass().getName());

    @Override
    public ClientDto saveClient(ClientDto clientDto) {
        //  log.info("enregistrement d'un nouveau Client");
        Client client = banaueMapperImpl.deClientDto(clientDto);
        Client savedClient = clientRepository.save(client);
        return banaueMapperImpl.deClient(savedClient);
    }

    public ClientDto getClient(long id) throws ClientNotFoundException {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new ClientNotFoundException("CLIENT_INTROUVABLE"));
        return banaueMapperImpl.deClient(client);
    }

    @Override
    public ClientDto updateClient(ClientDto clientDto) {
        Client client = banaueMapperImpl.deClientDto(clientDto);
        Client savedClient = clientRepository.save(client);
        return banaueMapperImpl.deClient(savedClient);
    }

    @Override
    public void deleteClient(Long id) {
        clientRepository.deleteById(id);
    }

    @Override
    public List<ClientDto> searchClient(String keyword) {
        List<Client> clients=clientRepository.searchCustomer(keyword);
        List<ClientDto> clientDtos = clients.stream().map(cust -> banaueMapperImpl.deClient(cust)).collect(Collectors.toList());
        return clientDtos;
    }

    @Override
    public List<ClientDto> listeClient() {
        List<Client> clients = clientRepository.findAll();
        List<ClientDto> clientDtos = clients.stream()
                .map(client -> banaueMapperImpl.deClient(client))
                .toList();
        return clientDtos;
    }
}

