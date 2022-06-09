package ro.ubb.countapp.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import ro.ubb.countapp.domain.Detection;

import java.util.List;

@Data
@Getter
@Setter
public class SessionDetectionDTO {

    String name;
    String timeCreated;
    List<Integer> results;
}
