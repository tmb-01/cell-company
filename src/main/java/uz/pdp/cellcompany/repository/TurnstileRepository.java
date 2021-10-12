package uz.pdp.cellcompany.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.cellcompany.entity.Turnstile;

import java.util.UUID;

public interface TurnstileRepository extends JpaRepository<Turnstile, UUID> {
}
