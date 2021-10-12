package uz.pdp.cellcompany.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.context.event.EventListener;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import uz.pdp.cellcompany.entity.enums.PaymentType;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Payment {

    @Id
    @GeneratedValue
    private Long id;
    private PaymentType paymentType;
    private Float amount;

    @CreatedBy
    private UUID user;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
    private SimCard simCard;

    @CreationTimestamp
    private Timestamp createdAt;
}
