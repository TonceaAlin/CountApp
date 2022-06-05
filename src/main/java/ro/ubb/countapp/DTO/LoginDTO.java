package ro.ubb.countapp.DTO;

import lombok.*;

@AllArgsConstructor
@Data
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class LoginDTO {
    private String username;
    private String password;
}
