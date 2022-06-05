package ro.ubb.countapp.DTO;

import lombok.*;

@AllArgsConstructor
@Data
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class RegisterDTO {
    public String username;
    public String email;
    public String password;
}
