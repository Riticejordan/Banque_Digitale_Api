package com.banque.Banque_Digitale_Api.web;

import com.banque.Banque_Digitale_Api.Exception.ClientNotFoundException;
import com.banque.Banque_Digitale_Api.dto.ClientDto;
import com.banque.Banque_Digitale_Api.dto.OperationDto;
import com.banque.Banque_Digitale_Api.entities.Operation;
import com.banque.Banque_Digitale_Api.services.ClientService;
import com.banque.Banque_Digitale_Api.services.CompteService;
import com.banque.Banque_Digitale_Api.services.OperationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class ClientRestController {

    @Autowired
   private ClientService banqueService;

    CompteService compteService;

    @Autowired
    private OperationService operationService;

    @GetMapping("/clients")
    public List<ClientDto> clients(){
        return banqueService.listeClient();
    }

    @GetMapping("/clients/{id}")
    public ClientDto getClient(@PathVariable(name = "id")  Long id) throws ClientNotFoundException {
        return banqueService.getClient(id);
    }

    @PostMapping("/clients")
    public ClientDto saveClientDto( @RequestBody ClientDto clientDto){
        return banqueService.saveClient(clientDto) ;
    }

    @PutMapping("clients/{id}")
    public ClientDto updateDto(@PathVariable(name = "id") Long id,@RequestBody ClientDto clientDto){
        clientDto.setId(id);
        return banqueService.updateClient(clientDto);
    }

    @DeleteMapping("clients/{id}")
    public void deleteClient(@PathVariable(name = "id") Long id){
        banqueService.deleteClient(id);
    }

    @GetMapping("/clients/search")
    public List<ClientDto> searchCustomers(@RequestParam(name = "keyword",defaultValue = "") String keyword){
        return banqueService.searchClient("%"+keyword+"%");


    }

    }
