package uz.pdp.cellcompany.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BuyServiceDto {
    private Long serviceId;
    private String companyCode;
    private String phoneNumber;
}
