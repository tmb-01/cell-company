package uz.pdp.cellcompany.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Tariff {

    @Id
    @GeneratedValue
    private Long id;

    private boolean isForPhysicalPerson;

    private String name;

    private String price;

    private int priceToSwitchToThisTariff;

    private int duration;  // amal qilish muddati nechi oy uchun ekanligini yoziladi

    private int innerMinute; // tarmoq ichida daqiqalar
    private int outerMinute; // tarmoqdan tashqari daqiqalar
    private int numberOfSms; // berilgan sms
    private int mb; // internet traffic mb buyicha yoziladi

    //    limitdan tashqari narxlar
    private float innerMinuteOverLimit;
    private float outerMinuteOverLimit;
    private float numberOfSmsOverLimit;
    private float mbOverLimit;


    @JsonIgnore
    @OneToMany(mappedBy = "tariff", cascade = CascadeType.ALL)
    private List<SimCardTariff> simCardTariffList;

    @Transient
    @OrderBy
    private Long amountOfTariffUsers;

    public Long getAmountOfTariffUsers() {
        return (long) simCardTariffList.size();
    }


}
