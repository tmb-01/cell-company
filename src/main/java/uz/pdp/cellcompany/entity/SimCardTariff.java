package uz.pdp.cellcompany.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class SimCardTariff {

    @Id
    @GeneratedValue
    private Long id;


    private int innerMinute; // tarmoq ichida daqiqalar
    private int outerMinute; // tarmoqdan tashqari daqiqalar
    private int numberOfSms; // berilgan sms
    private int mb; // internet traffic mb buyicha yoziladi

    private Timestamp lastRefreshedTime;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Tariff tariff;

    @JsonIgnore
    @OneToOne(optional = false, cascade = CascadeType.ALL, mappedBy = "scTariff")
    private SimCard simCard;

}
