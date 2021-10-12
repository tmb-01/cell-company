package uz.pdp.cellcompany.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.cellcompany.entity.Role;

public interface RoleRepository extends JpaRepository<Role,Long> {
}
