package ma.adil.carmsservice.service;

import ma.adil.carmsservice.entities.Car;
import ma.adil.carmsservice.entities.Client;
import ma.adil.carmsservice.external.RestClient;
import ma.adil.carmsservice.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarServiceImpl implements CarService{
    @Autowired
    private CarRepository carRepository;
    @Autowired
    private RestClient clientRestClient;
    @Override
    public List<Car> findAll() {
        List<Car> cars = carRepository.findAll();
        List<Client> clients = clientRestClient.findAll();
        return cars.stream().map(car -> mapToCarResponse(car, clients)).toList();
    }

    private Car mapToCarResponse(Car car, List<Client> clients) {
        Client foundClient = clients.stream()
                .filter(client -> client.getId() == car.getClient_id())
                .findFirst()
                .orElse(null);

        return Car.builder()
                .id(car.getId())
                .brand(car.getBrand())
                .client(foundClient)
                .matricue(car.getMatricue())
                .model(car.getModel())
                .build();
    }

    @Override
    public Car findById(Long id) throws Exception {
        Car car= carRepository.findById(id).orElseThrow(()->new Exception("Invalid Car Id"));
        Client client = clientRestClient.findById(id);
        return Car.builder()
                .id(car.getId())
                .brand(car.getBrand())
                .client(client)
                .matricue(car.getMatricue())
                .model(car.getModel())
                .build();
    }

    @Override
    public void save(Car car) {
        Car car1 = new Car();
        car1.setClient_id(car.getClient().getId());
        car1.setModel(car.getModel());
        car1.setBrand(car.getBrand());
        car1.setMatricue(car.getMatricue());

        carRepository.save(car1);
    }

    @Override
    public void delete(Long id) throws Exception {
        Car car = carRepository.findById(id).orElseThrow(()->new Exception("Invalid Id"));
        carRepository.delete(car);
    }

    @Override
    public void update(Car carResponse, Long id) throws Exception {
        Car car = carRepository.findById(id).orElseThrow(()->new Exception("Invalid ID"));
        car.setClient_id(carResponse.getClient().getId());
        car.setModel(carResponse.getModel());
        car.setBrand(carResponse.getBrand());
        car.setMatricue(carResponse.getMatricue());

        carRepository.save(car);
    }
}
