package ma.adil.clientservice;

import ma.adil.clientservice.entity.Client;
import ma.adil.clientservice.repository.ClientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class ClientServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClientServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(ClientRepository clientRepository){
        return args -> {
            List<Client> clientList = List.of(
                    Client.builder()
                            .age(23)
                            .name("Adil")
                            .build(),
                    Client.builder()
                            .age(22)
                            .name("Badr")
                            .build()
            );
            clientRepository.saveAll(clientList);
        };
    }

}
