package com.banque.Banque_Digitale_Api.repositories;

import com.banque.Banque_Digitale_Api.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client,Long> {
    @Query("select c from Client c where c.nom like :kw")
    List<Client> searchCustomer(@Param("kw") String keyword);

}
