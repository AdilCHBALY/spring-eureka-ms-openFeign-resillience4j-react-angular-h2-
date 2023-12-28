package ma.adil.carmsservice;

import ma.adil.carmsservice.entities.Car;
import ma.adil.carmsservice.entities.Client;
import ma.adil.carmsservice.external.RestClient;
import ma.adil.carmsservice.repository.CarRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableFeignClients
public class CarMsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarMsServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(CarRepository carRepository, RestClient restClient){
		return args -> {
			List<Client> clients = restClient.findAll();
			List<Car> cars = new ArrayList<>();
			clients.forEach(client -> {
				cars.add(Car.builder()
						.brand("Audi")
						.model("A3")
						.client_id(client.getId())
						.matricue("1234567A6")
						.build());
			});

			carRepository.saveAll(cars);
		};
	}

}
