package uz.pdp.cellcompany.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.cellcompany.entity.SimCard;
import uz.pdp.cellcompany.payload.ApiResponse;
import uz.pdp.cellcompany.payload.RegisterNumberDto;
import uz.pdp.cellcompany.service.SimCardService;

import java.util.List;

@RestController
@RequestMapping("api/v1/sim-card")
public class SimCardController {

    @Autowired
    SimCardService simCardService;

    @GetMapping
    public List<SimCard> getAll() {
        return simCardService.getAll();
    }

    @PostMapping
    public ApiResponse addSimCard(SimCard simCard) {
        return simCardService.addSimCard(simCard);
    }

    @PutMapping("register-number")
    public ApiResponse registerNumber(@RequestBody RegisterNumberDto registerNumberDto) {
        return simCardService.registerNumber(registerNumberDto);
    }

}
