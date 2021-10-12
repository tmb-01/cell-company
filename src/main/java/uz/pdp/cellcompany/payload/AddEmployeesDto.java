package uz.pdp.cellcompany.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AddEmployeesDto {

    private List<UUID> usersIds;
    private Long branchId;

}
