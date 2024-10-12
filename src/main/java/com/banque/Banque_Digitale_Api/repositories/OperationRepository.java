package com.banque.Banque_Digitale_Api.repositories;

import com.banque.Banque_Digitale_Api.entities.Operation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperationRepository extends JpaRepository<Operation,Long> {
}
