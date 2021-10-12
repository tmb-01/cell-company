package uz.pdp.cellcompany.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.cellcompany.entity.ServiceEntertainment;

import java.util.List;

public interface ServiceEntertainmentRepository extends JpaRepository<ServiceEntertainment, Long> {

    @Query("select se from ServiceEntertainment se ORDER BY se.simCard.size")
    List<ServiceEntertainment> findAllByMostUsed();
}
