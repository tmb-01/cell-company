package uz.pdp.cellcompany.payload;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.cellcompany.entity.Branch;
import uz.pdp.cellcompany.entity.Role;
import uz.pdp.cellcompany.entity.Turnstile;

import javax.persistence.*;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDto {
    private String username;

    private String password;

    private Set<Role> roles;

    private Branch branch;
}
