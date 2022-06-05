package ro.ubb.countapp.domain;

import javax.persistence.*;
import java.nio.file.Path;

@Entity
@Table(name="detections")
public class Detection {
    @Id
    @GeneratedValue
    private Long id;

    public Detection(String noDetectedApples, String imageBytes) {
        this.noDetectedApples = noDetectedApples;
        this.imageBytes = imageBytes;
    }

    @ManyToOne()
    private Session session;
    private String noDetectedApples;
    private String imageBytes;

    public Detection() {

    }

    public void setNoDetectedApples(String noDetectedApples) {
        this.noDetectedApples = noDetectedApples;
    }

    public void setImageBytes(String pathToImage) {
        this.imageBytes = pathToImage;
    }

    public String getNoDetectedApples() {
        return noDetectedApples;
    }

    public String getImageBytes() {
        return imageBytes;
    }
}
