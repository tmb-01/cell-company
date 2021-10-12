package uz.pdp.cellcompany.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.cellcompany.entity.ServiceEntertainment;
import uz.pdp.cellcompany.payload.ApiResponse;
import uz.pdp.cellcompany.payload.BuyServiceDto;
import uz.pdp.cellcompany.service.ServiceEntertainmentService;

import java.util.List;

@RestController
@RequestMapping("api/v1/service-entertainment")
public class ServiceEntertainmentController {

    @Autowired
    ServiceEntertainmentService serviceEntertainmentService;

    @GetMapping
    public List<ServiceEntertainment> getStat() {
        return serviceEntertainmentService.getStat();
    }

    @PostMapping
    public ApiResponse addService(ServiceEntertainment serviceEntertainment) {
        return serviceEntertainmentService.add(serviceEntertainment);
    }

    @PutMapping
    public ApiResponse buyService(@RequestBody BuyServiceDto buyServiceDto) {
        return serviceEntertainmentService.buyService(buyServiceDto);
    }

    @PutMapping("{id}")
    public ApiResponse updateService(@PathVariable Long id, @RequestBody ServiceEntertainment serviceEntertainment) {
        return serviceEntertainmentService.update(id, serviceEntertainment);
    }

    @DeleteMapping("{id}")
    public ApiResponse deleteService(@PathVariable Long id) {
        return serviceEntertainmentService.delete(id);
    }
}
