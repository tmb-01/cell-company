package uz.pdp.cellcompany.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.cellcompany.entity.SimCard;

import java.util.Optional;

public interface SimCardRepository extends JpaRepository<SimCard,Long> {
    Optional<SimCard> findByCompanyCodeAndPhoneNumber(String companyCode, String phoneNumber);
}
