package ro.ubb.countapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="detections")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Detection implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    public Detection(String noDetectedApples, byte[] imageBytes) {
        this.noDetectedApples = noDetectedApples;
        this.imageBytes = imageBytes;
    }

    @ManyToOne
    @JoinColumn(name="session_id")
    private Session session;
    private String noDetectedApples;
    private byte[] imageBytes;

    public void setNoDetectedApples(String noDetectedApples) {
        this.noDetectedApples = noDetectedApples;
    }

    public void setImageBytes(byte[] pathToImage) {
        this.imageBytes = pathToImage;
    }

    public String getNoDetectedApples() {
        return noDetectedApples;
    }

    public byte[] getImageBytes() {
        return imageBytes;
    }
}
