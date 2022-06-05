package ro.ubb.countapp.domain;

import java.nio.file.Path;


public class Detection {

    public Detection(String noDetectedApples, String imageBytes) {
        this.noDetectedApples = noDetectedApples;
        this.imageBytes = imageBytes;
    }

    private String noDetectedApples;
    private String imageBytes;
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
