package com.banque.Banque_Digitale_Api.repositories;

import com.banque.Banque_Digitale_Api.entities.Compte;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompteRepository extends JpaRepository<Compte,String> {
}
