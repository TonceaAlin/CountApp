package ro.ubb.countapp.controllers;


import net.minidev.json.JSONValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.ubb.countapp.DTO.LoginDTO;
import ro.ubb.countapp.DTO.RegisterDTO;
import ro.ubb.countapp.responses.LoginResponse;
import ro.ubb.countapp.services.UserService;

@RestController
@RequestMapping("/login")
@CrossOrigin("http://localhost:4200/")
public class LoginController {

    @Autowired
    private UserService service;

    @PostMapping("/sign-in")
    public ResponseEntity<?> login(@RequestBody LoginDTO user){
        try{
            String username = user.getUsername();
            String password = user.getPassword();
            var token = service.authentication(username, password);
            return new ResponseEntity<>(JSONValue.toJSONString(new LoginResponse(token)), HttpStatus.OK);
        }catch (RuntimeException e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(JSONValue.toJSONString("login failed!"), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/sign-up")
    public ResponseEntity<?> register(@RequestBody RegisterDTO user){
        try{
            String username = user.getUsername();
            String email = user.getEmail();
            String password = user.getPassword();
            service.addUser(username, password, email);
            return new ResponseEntity<>(JSONValue.toJSONString("Account created"), HttpStatus.CREATED);
        }catch(RuntimeException  exception){
            System.out.println(exception.getMessage());
            return new ResponseEntity<>(JSONValue.toJSONString("Account not created"), HttpStatus.NOT_FOUND);
        }
    }

}
