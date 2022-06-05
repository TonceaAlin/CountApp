package ro.ubb.countapp.services;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.ubb.countapp.domain.User;
import ro.ubb.countapp.repository.UserRepository;

import javax.crypto.spec.SecretKeySpec;
import javax.transaction.Transactional;
import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public boolean existsByEmail(String email){
        return this.userRepository.existsByEmail(email);
    }

    public boolean existsByUsername(String username){
        return this.userRepository.existsByUsername(username);
    }

    @Transactional
    public void save(User user){
        userRepository.save(user);
    }

    public boolean addUser(String username, String password, String email) {
        if (this.checkUserExists(email))
            throw new RuntimeException("There exist someone already with this username");
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);
        try {
            var savedUser = userRepository.save(user);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    public boolean checkUserExists(String email) {
        return userRepository.findAll().stream().anyMatch(u -> u.getEmail().equals(email));
    }

    public String generateJwtToken(User user) {
        // this is hardcoded
        String secret = "asdfSFS34wfsdfsdfSDSD32dfsddDDerQSNCK34SOWEK5354fdgdf4";

        Key hmacKey = new SecretKeySpec(Base64.getDecoder().decode(secret),
                SignatureAlgorithm.HS256.getJcaName());

        Map<String, Object> claims = new HashMap<>();
        claims.put("id", user.getId());
        claims.put("username", user.getUsername());
        claims.put("email", user.getEmail());

        Instant now = Instant.now();
        return Jwts.builder()
                .addClaims(claims)
                .setId(UUID.randomUUID().toString())
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(now.plus(60, ChronoUnit.MINUTES)))
                .signWith(SignatureAlgorithm.HS256, hmacKey)
                .compact();
    }
    public String authentication(String username, String password) {
        var found = userRepository.findAll().stream()
                .filter(u -> u.getUsername().equals(username) && u.getPassword().equals(password))
                .findAny();
        if (found.isEmpty())
            throw new RuntimeException("");
        var foundUser = found.get();

        return generateJwtToken(found.get());
    }
}
