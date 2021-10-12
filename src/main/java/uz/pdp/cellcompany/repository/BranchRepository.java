package uz.pdp.cellcompany.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.cellcompany.entity.Branch;
import uz.pdp.cellcompany.payload.BranchStatDto;

import java.util.List;

public interface BranchRepository extends JpaRepository<Branch, Long> {

    @Query("select b.id, b.region, b.simCard.size as numberOfSoldSimCard from Branch b")
    List<BranchStatDto> getSoldSimCardStat();

}
