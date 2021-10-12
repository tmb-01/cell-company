package uz.pdp.cellcompany.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.cellcompany.entity.Payment;

import java.sql.Timestamp;
import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findAllByCreatedAtAfter(Timestamp createdAt);
}
