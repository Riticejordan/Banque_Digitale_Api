package com.banque.Banque_Digitale_Api.web;


import com.banque.Banque_Digitale_Api.Exception.CompteNotFoundException;
import com.banque.Banque_Digitale_Api.Exception.SoldeNotFoundException;
import com.banque.Banque_Digitale_Api.dto.*;
import com.banque.Banque_Digitale_Api.services.CompteService;
import com.banque.Banque_Digitale_Api.services.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class CompteRestController {

    @Autowired
    private CompteService compteService;
    @Autowired
   private OperationService operationService;


    @GetMapping("/comptes/{compteId}")
    public CompteDto getCompte(@PathVariable(name = "compteId")  String compteId) throws CompteNotFoundException {
        return compteService.getCompte(compteId);

    }

    @GetMapping("/comptes")
    public List<CompteDto> listeCompte(){
        return  compteService.compteList();
    }

    @GetMapping("/comptes/{compteId}/operations")
    public List<OperationDto> getHistory(@PathVariable(name = "compteId") String compteId){
        return operationService.historiqueCompte(compteId);
    }

    @GetMapping("/comptes/{compteId}/pageOperation")
    public HistoriqueCompteDto getHistoriqueCompte(
                                        @PathVariable String compteId,
                                        @RequestParam(name="page" ,defaultValue="0")int page,
                                        @RequestParam(name="taille", defaultValue="7" )int taille) throws CompteNotFoundException {
        return operationService.getHistoriqueCompte(compteId,page,taille);
    }

    @PostMapping("/comptes/debit")
    public DebitDto debit(@RequestBody DebitDto  debitDto) throws CompteNotFoundException, SoldeNotFoundException {
        this.compteService.debit(debitDto.getCompteId(), debitDto.getMontant(), debitDto.getDescription());
        return debitDto;
    }
    @PostMapping("/comptes/credit")
    public CreditDto credit(@RequestBody CreditDto creditDto) throws CompteNotFoundException {
        this.compteService.credit( creditDto.getCompteId(),creditDto.getMontant(), creditDto.getDescription());
        return  creditDto;
    }
    @PostMapping("/comptes/transfert")
    public void transfer(@RequestBody TransfertDto transfertDto) throws CompteNotFoundException, SoldeNotFoundException {
        this.compteService.transfert(
                transfertDto.getCompteSource(),
                transfertDto.getCompteDestination(),
                transfertDto.getMontant());
    }

}
