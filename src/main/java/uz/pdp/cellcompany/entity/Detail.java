package uz.pdp.cellcompany.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Detail {

    @Id
    @GeneratedValue
    private Long id;


    @ManyToOne
    private Pocket boughtPocket;

    @ManyToOne
    private ServiceEntertainment boughtServiceEntertainment;

    @ManyToOne
    private Tariff boughtTariff;

    @CreatedBy
    private UUID createdBy;

    @CreationTimestamp
    private Timestamp createdAt;
}
