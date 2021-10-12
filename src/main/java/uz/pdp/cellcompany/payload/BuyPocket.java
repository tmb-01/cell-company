package uz.pdp.cellcompany.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BuyPocket {
    private String phoneNumber;
    private String companyCode;
    private Long pocketId;
}
