package uz.pdp.cellcompany.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.cellcompany.entity.Payment;
import uz.pdp.cellcompany.payload.ApiResponse;
import uz.pdp.cellcompany.service.PaymentService;

@RestController
@RequestMapping("api/v1/payment")
public class PaymentController {

    @Autowired
    PaymentService paymentService;

    @PostMapping("pay")
    public ApiResponse pay(@RequestBody Payment payment) {
        return paymentService.pay(payment);
    }

}
