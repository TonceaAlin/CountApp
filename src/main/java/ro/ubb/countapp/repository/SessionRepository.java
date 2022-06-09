package ro.ubb.countapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.ubb.countapp.domain.Session;

import java.util.List;

public interface SessionRepository extends JpaRepository<Session, Long> {
    List<Session> findAllByUserId(Long Id);
}
