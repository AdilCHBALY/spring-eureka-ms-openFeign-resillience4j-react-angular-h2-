package ma.adil.carmsservice.controller;


import ma.adil.carmsservice.entities.Car;
import ma.adil.carmsservice.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/car")
public class CarController {
    @Autowired
    private CarService carService;

    @GetMapping
    public List<Car> findAll(){
        return carService.findAll();
    }

    @GetMapping("/{id}")
    public Car findById(@PathVariable Long id) throws Exception {return carService.findById(id);}

    @PostMapping
    public void add(@RequestBody Car car){carService.save(car);}

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) throws Exception {carService.delete(id);}

    @PutMapping("/{id}")
    public void update(@PathVariable Long id,@RequestBody Car carResponse) throws Exception {carService.update(carResponse,id);}
}
