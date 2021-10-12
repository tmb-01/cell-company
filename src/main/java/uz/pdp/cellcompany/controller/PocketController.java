package uz.pdp.cellcompany.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.cellcompany.entity.Pocket;
import uz.pdp.cellcompany.payload.ApiResponse;
import uz.pdp.cellcompany.payload.BuyPocket;
import uz.pdp.cellcompany.service.PocketService;

@RestController
@RequestMapping("api/v1/pocket")
public class PocketController {

    @Autowired
    PocketService pocketService;

    @PostMapping
    public ApiResponse addPocket(@RequestBody Pocket pocket) {
        return pocketService.addPocket(pocket);
    }

    @PutMapping
    public ApiResponse buyPocket(@RequestBody BuyPocket buyPocket) {
        return pocketService.buyPocket(buyPocket);
    }

    @DeleteMapping("{id}")
    public ApiResponse deletePocket(@PathVariable Long id) {
        return pocketService.deletePocket(id);
    }

    @PutMapping("{id}")
    public ApiResponse updatePocket(@PathVariable Long id, @RequestBody Pocket pocket) {
        return pocketService.updatePocket(id, pocket);
    }
}
