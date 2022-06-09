package ro.ubb.countapp.controllers;


import net.minidev.json.JSONValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.ubb.countapp.DTO.SessionDTO;
import ro.ubb.countapp.DTO.SessionDetectionDTO;
import ro.ubb.countapp.domain.Detection;
import ro.ubb.countapp.domain.Session;
import ro.ubb.countapp.services.FileService;
import ro.ubb.countapp.services.SessionService;
import ro.ubb.countapp.services.UserService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:4200/")
public class SessionController {

    @Autowired
    private SessionService service;

    @Autowired
    private UserService userService;

    @Autowired
    private FileService fileService;

    @PostMapping(value = "/start-session")
    public ResponseEntity<?> creatSession(@RequestBody SessionDTO session){
        try{
            var name = session.getName();
            var userId = session.getUserId();
            var time = session.getTime();
            var sessionId = this.service.addSession(name, time, userId).getId();
            return new ResponseEntity<>(sessionId, HttpStatus.CREATED);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(JSONValue.toJSONString("something bad"), HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping(value = "/sessions")
    public ResponseEntity<SessionDetectionDTO[]> getSessionsById(@RequestParam("userId") String userId){
        System.out.println("userul " + userId);
        System.out.println(userService.getUserById(Long.parseLong(userId)));
        Session[] sessions = this.service.getAllSessionsByUserId(Long.parseLong(userId));
        List<SessionDetectionDTO> results = new ArrayList<>();
        Arrays.stream(sessions).forEach((x) -> {
            var detections = fileService.getDetectionsBySessionId(x.getId());
            var sessionDetectionDTO = new SessionDetectionDTO();
            sessionDetectionDTO.setResults(Arrays.stream(detections).map((y) ->  Integer.parseInt(y.getNoDetectedApples())).collect(Collectors.toList()));
            sessionDetectionDTO.setName(x.getName());
            sessionDetectionDTO.setTimeCreated(x.getTimeCreated());
            results.add(sessionDetectionDTO);
        });
        results.forEach(System.out::println);
        return new ResponseEntity<>(results.toArray(new SessionDetectionDTO[results.size()]), HttpStatus.OK);
    }

}
