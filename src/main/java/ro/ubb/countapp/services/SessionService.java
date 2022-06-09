package ro.ubb.countapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Optionals;
import org.springframework.stereotype.Service;
import ro.ubb.countapp.domain.Session;
import ro.ubb.countapp.domain.User;
import ro.ubb.countapp.repository.SessionRepository;
import ro.ubb.countapp.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SessionService {

    @Autowired
    SessionRepository sessionRepository;

    @Autowired
    UserRepository userRepository;

    public Session addSession(String name, String time, String userId){
        Session session = new Session();
        session.setName(name);
        Optional<User> user = userRepository.findById(Long.parseLong(userId));
        session.setUser(user.orElse(null));
        session.setTimeCreated(time);
//        user.get().getSessions().add(session);
        return this.sessionRepository.save(session);
    }

    public Session getSessionById(Long id){
        return this.sessionRepository.findById(id).get();
    }

    public List<Session> getSessions(){
        return this.sessionRepository.findAll();
    }

    public Session[] getAllSessionsByUserId(Long userId){
        var sessions = this.sessionRepository.findAllByUserId(userId);
        return sessions.toArray(new Session[sessions.size()]);
    }
}
