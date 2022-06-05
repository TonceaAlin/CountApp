package ro.ubb.countapp.domain;


import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "sessions")
public class Session {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String timeCreated;
    @ManyToOne
    private User user;
    @OneToMany
    private List<Detection> detections;


    public void setId(Long id) {
        this.id = id;
    }


    public Long getId() {
        return id;
    }
}
