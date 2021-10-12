package uz.pdp.cellcompany.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.cellcompany.entity.enums.USSD_Const;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class USSD {
    @Id
    @GeneratedValue
    private Long id;

    @Enumerated(EnumType.STRING)
    private USSD_Const ussdConst;

    private String code;
}
