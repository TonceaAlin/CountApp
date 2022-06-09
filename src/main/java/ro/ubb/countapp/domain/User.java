package ro.ubb.countapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "users")
@ToString
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class User implements Serializable {


    @Id
    @GeneratedValue
    private Long id;
    private String username;
    private String email;
    private String password;
//
//    @OneToMany(cascade = CascadeType.ALL)
//    private List<Session> sessions = new ArrayList<>();

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

//    public void addSession(Session session){
//        sessions.add(session);
//        session.setUser(this);
//    }

}
