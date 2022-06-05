package ro.ubb.countapp.controllers;


import org.springframework.web.bind.annotation.*;
import ro.ubb.countapp.DTO.SessionDTO;

@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:4200/")
public class SessionController {

    @PostMapping(value = "/start-session")
    public void creatSession(@RequestBody SessionDTO session){
        System.out.println(session.getName());
        System.out.println(session.getUserId());
        System.out.println(session.getTime());
    }

}
