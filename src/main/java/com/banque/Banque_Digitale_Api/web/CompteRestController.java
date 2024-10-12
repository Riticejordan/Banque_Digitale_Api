package com.banque.Banque_Digitale_Api.web;

import com.banque.Banque_Digitale_Api.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CompteRestController {
    @Autowired
    private ClientService banqueService;

}
