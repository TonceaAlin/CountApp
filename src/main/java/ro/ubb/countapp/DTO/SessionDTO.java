package ro.ubb.countapp.DTO;


import lombok.*;

@AllArgsConstructor
@Data
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class SessionDTO {
    public String name;
    public String time;
    public String userId;
}
