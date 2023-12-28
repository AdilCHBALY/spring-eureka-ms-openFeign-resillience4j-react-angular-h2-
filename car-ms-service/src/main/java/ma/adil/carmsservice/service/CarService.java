package ma.adil.carmsservice.service;

import ma.adil.carmsservice.entities.Car;
import org.springframework.stereotype.Service;

import java.util.List;



public interface CarService {
    List<Car> findAll();
    Car findById(Long id) throws Exception;
    void save(Car car);
    void delete(Long id) throws Exception;

    void update(Car carResponse, Long id) throws Exception;
}
