package com.banque.Banque_Digitale_Api.services;

import com.banque.Banque_Digitale_Api.Exception.CompteNotFoundException;
import com.banque.Banque_Digitale_Api.dto.HistoriqueCompteDto;
import com.banque.Banque_Digitale_Api.dto.OperationDto;
import com.banque.Banque_Digitale_Api.entities.Compte;
import com.banque.Banque_Digitale_Api.entities.Operation;
import com.banque.Banque_Digitale_Api.mappers.BanqueMapperImpl;
import com.banque.Banque_Digitale_Api.repositories.CompteRepository;
import com.banque.Banque_Digitale_Api.repositories.OperationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OperationServiceImpl implements OperationService{

    @Autowired
    private BanqueMapperImpl banqueMapperImpl;
    @Autowired
    private OperationRepository operationRepository;

    @Autowired
    private CompteRepository compteRepository;

    @Override
    public List<OperationDto> historiqueCompte(String id) {
        List<Operation> operations=operationRepository.findByCompteId(id);
       return operations.stream().map(operation-> banqueMapperImpl.deoperationDto(operation)).collect(Collectors.toList());
    }

    public HistoriqueCompteDto getHistoriqueCompte(String compteId, int page, int taille) throws CompteNotFoundException {
        Compte compte= compteRepository.findById(compteId).orElseThrow(null);
        if (compte==null) throw new CompteNotFoundException("Compte not found");
       Page<Operation> operations= operationRepository.findByCompteId(compteId, PageRequest.of(page,taille));
        HistoriqueCompteDto historiqueCompteDto=new HistoriqueCompteDto();
       List<OperationDto> operationDtos=operations.getContent().stream().map(op->banqueMapperImpl.deoperationDto(op)).collect(Collectors.toList());
       historiqueCompteDto.setOperationDtos(operationDtos);
        historiqueCompteDto.setCompteId(compte.getId());
        historiqueCompteDto.setSolde(compte.getSolde());
        historiqueCompteDto.setPageCourante(page);
        historiqueCompteDto.setTaile(taille);
        historiqueCompteDto.setTotalPage(operations.getTotalPages());
        return historiqueCompteDto;
    }
}
