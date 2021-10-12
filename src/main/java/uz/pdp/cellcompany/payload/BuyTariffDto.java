package uz.pdp.cellcompany.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BuyTariffDto {

    private Long tariffId;
    private String companyCode;
    private String phoneNumber;

}
