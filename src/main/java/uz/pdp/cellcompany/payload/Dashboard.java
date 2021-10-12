package uz.pdp.cellcompany.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.cellcompany.entity.Payment;
import uz.pdp.cellcompany.entity.Pocket;
import uz.pdp.cellcompany.entity.Tariff;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Dashboard {

    private List<BranchStatDto> soldSimCards;
    private List<Payment> dailyStat;
    private List<Payment> monthlyStat;
    private List<Payment> yearlyStat;

    private List<Tariff> tariffStat;

    private List<Pocket> pocketStat;

}
