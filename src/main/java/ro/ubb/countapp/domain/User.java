package ro.ubb.countapp.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {


    @Id
    @GeneratedValue

    private Long id;
    private String username;
    private String email;
    private String password;
    @OneToMany()
    private List<Session> sessions;

    public void setId(Long id) {
        this.id = id;
    }


    public Long getId() {
        return id;
    }
}
