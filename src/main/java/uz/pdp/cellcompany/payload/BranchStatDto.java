package uz.pdp.cellcompany.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BranchStatDto {
    private Long id;
    private String region;
    private Integer numberOfSoldSimCard;
}
