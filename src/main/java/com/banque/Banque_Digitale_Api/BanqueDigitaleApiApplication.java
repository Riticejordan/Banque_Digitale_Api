package com.banque.Banque_Digitale_Api;

import com.banque.Banque_Digitale_Api.dto.ClientDto;
import com.banque.Banque_Digitale_Api.entities.CompteCourant;
import com.banque.Banque_Digitale_Api.entities.CompteEpargne;
import com.banque.Banque_Digitale_Api.entities.Operation;
import com.banque.Banque_Digitale_Api.enums.Status;
import com.banque.Banque_Digitale_Api.enums.TypeOperation;
import com.banque.Banque_Digitale_Api.repositories.ClientRepository;
import com.banque.Banque_Digitale_Api.repositories.CompteRepository;
import com.banque.Banque_Digitale_Api.repositories.OperationRepository;
import com.banque.Banque_Digitale_Api.services.ClientService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;



import java.util.Date;
import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class BanqueDigitaleApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BanqueDigitaleApiApplication.class, args);
	}

	@Bean
	CommandLineRunner start(
			ClientRepository clientRepository,
			CompteRepository compteRepository,
			OperationRepository operationRepository,
			ClientService banqueService
	){
		return args -> {
			Stream.of("keumeni","jordan","ngougueu").forEach(nom->{
				ClientDto clientDto= new ClientDto();
				clientDto.setNom(nom);
				clientDto.setEmail(nom+"@gmail.com");
				banqueService.saveClient(clientDto);
			});

			clientRepository.findAll().forEach(cli->{
				CompteCourant compteCourant=new CompteCourant();
				compteCourant.setId(UUID.randomUUID().toString());
				compteCourant.setSolde(Math.random()*1000 );
				compteCourant.setDateCreation(new Date());
				compteCourant.setStatus(Status.CREER);
				compteCourant.setClient(cli);
				compteCourant.setDecouvert(9000);
				compteRepository.save(compteCourant);


				CompteEpargne compteEpargne=new CompteEpargne();
				compteEpargne.setId(UUID.randomUUID().toString());
				compteEpargne.setSolde(Math.random()*3000);
				compteEpargne.setDateCreation(new Date());
				compteEpargne.setStatus(Status.CREER);
				compteEpargne.setClient(cli);
				compteEpargne.setTouxInteret(0.5);
				compteRepository.save(compteEpargne);

			});

			 compteRepository.findAll().forEach(cpte->{
				 for(int i=0;i<5;i++){
					 Operation operation=new Operation();
					 operation.setDateOperation(new Date());
					 operation.setMontant(Math.random()*25000);
					 operation.setTypeOperation(Math.random()>0.5? TypeOperation.CREDIT:TypeOperation.DEBIT );
					operation.setCompte(cpte);
					operationRepository.save(operation);

				 }
			 });
		};
	}
}
