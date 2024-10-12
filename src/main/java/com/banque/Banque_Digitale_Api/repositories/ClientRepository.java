package com.banque.Banque_Digitale_Api.repositories;

import com.banque.Banque_Digitale_Api.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client,Long> {

}
