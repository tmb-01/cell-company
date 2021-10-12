package uz.pdp.cellcompany.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RegisterNumberDto {
    private UUID userId;
    private Long phoneNumberId;
    private Long branchId;
}
