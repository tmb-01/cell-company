package uz.pdp.cellcompany.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.cellcompany.entity.Tariff;

public interface TariffRepository extends JpaRepository<Tariff,Long> {
}
