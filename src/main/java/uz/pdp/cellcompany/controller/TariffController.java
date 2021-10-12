package uz.pdp.cellcompany.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.cellcompany.entity.Tariff;
import uz.pdp.cellcompany.payload.ApiResponse;
import uz.pdp.cellcompany.payload.BuyTariffDto;
import uz.pdp.cellcompany.service.TariffService;

@RestController
@RequestMapping("api/v1/tariff")
public class TariffController {

    @Autowired
    TariffService tariffService;

    @PostMapping
    public ApiResponse addTariff(Tariff tariff) {
        return tariffService.addTariff(tariff);
    }

    @PutMapping("{id}")
    public ApiResponse updateTariff(@PathVariable Long id, @RequestBody Tariff tariff) {
        return tariffService.updateTariff(id, tariff);
    }

    @PutMapping
    public ApiResponse buyTariff(@RequestBody BuyTariffDto buyTariffDto){
        return tariffService.buyTariff(buyTariffDto);
    }
}
