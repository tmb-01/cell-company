package uz.pdp.cellcompany.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.cellcompany.entity.SimCardPocket;

import java.util.Optional;

public interface SimCardPocketRepository extends JpaRepository<SimCardPocket, Long> {
    SimCardPocket findBySimCardId(Long simCard_id);
}
