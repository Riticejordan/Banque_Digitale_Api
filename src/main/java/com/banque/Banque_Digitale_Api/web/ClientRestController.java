package com.banque.Banque_Digitale_Api.web;

import com.banque.Banque_Digitale_Api.Exception.ClientNotFoundException;
import com.banque.Banque_Digitale_Api.dto.ClientDto;
import com.banque.Banque_Digitale_Api.services.ClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api")
public class ClientRestController {

    @Autowired
   private ClientService banqueService;

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

    @PutMapping("client/{id}")
    public ClientDto updateDto(@PathVariable(name = "id") Long id,@RequestBody ClientDto clientDto){
        clientDto.setId(id);
        return banqueService.updateClient(clientDto);
    }

    @DeleteMapping("client/{id}")
    public void deleteClient(@PathVariable(name = "id") Long id){
        banqueService.deleteClient(id);
    }
}
