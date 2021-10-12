package uz.pdp.cellcompany.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.cellcompany.entity.Pocket;

public interface PocketRepository extends JpaRepository<Pocket, Long> {
}
