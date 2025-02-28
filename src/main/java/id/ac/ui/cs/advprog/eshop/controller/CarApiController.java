package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/car")
public class CarApiController {

    private final CarService carService;

    @Autowired
    public CarApiController(CarService carService) {
        this.carService = carService;
    }

    @PostMapping("/createCar")
    public String createCar(@ModelAttribute Car car) {
        carService.create(car);
        return "redirect:listCar";
    }

    @PostMapping("/editCar")
    public String editCar(@ModelAttribute Car car) {
        carService.update(car.getCarId(), car);
        return "redirect:listCar";
    }

    @PostMapping("/deleteCar")
    public String deleteCar(@ModelAttribute Car car) {
        carService.deleteCarById(car.getCarId());
        return "redirect:listCar";
    }
}
