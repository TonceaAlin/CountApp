package ro.ubb.countapp.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "sessions")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Session implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String timeCreated;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
//    @OneToMany(cascade = CascadeType.ALL)
//    private List<Detection> detections = new ArrayList<>();

//    public void addDetection(Detection detection){
//        detections.add(detection);
//        detection.setSession(this);
//    }
}
