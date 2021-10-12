package uz.pdp.cellcompany.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.cellcompany.entity.Payment;
import uz.pdp.cellcompany.payload.ApiResponse;
import uz.pdp.cellcompany.repository.PaymentRepository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class PaymentService {

    @Autowired
    PaymentRepository paymentRepository;

    public ApiResponse pay(Payment payment) {
        paymentRepository.save(payment);
        return new ApiResponse("paid", true);
    }

    public List<Payment> getMonthlyStat(){
        return paymentRepository.findAllByCreatedAtAfter(Timestamp.valueOf(LocalDateTime.now().minusMonths(1L)));
    }

    public List<Payment> getDailyStat(){
        return paymentRepository.findAllByCreatedAtAfter(Timestamp.valueOf(LocalDateTime.now().minusDays(1L)));
    }

    public List<Payment> getYearlyStat(){
        return paymentRepository.findAllByCreatedAtAfter(Timestamp.valueOf(LocalDateTime.now().minusYears(1L)));
    }
}
