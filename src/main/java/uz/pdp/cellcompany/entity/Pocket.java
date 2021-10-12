package uz.pdp.cellcompany.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.cellcompany.entity.Tariff;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Pocket {

    @Id
    @GeneratedValue
    private Long id;

    private int minute; // daqiqalar
    private int sms; // sms
    private int internet; // internet traffic mb buyicha yoziladi

    private float price; // paket narxi
    private int duration; // paket amal qilish muddati kunda yoziladi

    private boolean addableToRemained;  // qolgan mb, sms, daqiqa-ga qushiladimi yoki yoqmi true - qushadi, false - mbni uchirib buni yozib quyadi

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pocket")
    private List<SimCardPocket> simCardPocketList;

    @Transient
    @OrderBy
    private Long numberOfUsers;

    public Long getNumberOfUsers() {
        return (long) simCardPocketList.size();
    }

}
