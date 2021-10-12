package uz.pdp.cellcompany.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.cellcompany.entity.Detail;

public interface DetailRepository extends JpaRepository<Detail, Long> {
}
