package ro.ubb.countapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.ubb.countapp.domain.Detection;

import java.util.List;

public interface DetectionRepository extends JpaRepository<Detection, Long> {
    Detection[] findAllBySessionId(Long sessionId);
}
