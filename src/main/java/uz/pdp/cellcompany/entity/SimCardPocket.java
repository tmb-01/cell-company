package uz.pdp.cellcompany.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class SimCardPocket {

    @Id
    @GeneratedValue
    private Long id;

    private int minute; // daqiqalar
    private int sms; // sms
    private int internet; // internet traffic mb buyicha yoziladi

    private Timestamp lastRefreshedDate;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Pocket pocket;

    @JsonIgnore
    @OneToOne(mappedBy = "scPocket", cascade = CascadeType.ALL)
    private SimCard simCard;

}
