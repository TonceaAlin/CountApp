package ro.ubb.countapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ro.ubb.countapp.domain.Detection;
import ro.ubb.countapp.services.FileService;

import java.io.IOException;

@RestController
@RequestMapping("/api/file")
@CrossOrigin("http://localhost:4200/")
public class CountController {

    @Autowired
    FileService fileService;

    @PostMapping(value = "/count-apples")
    @ResponseBody
    public Detection getNumberOfApples(@RequestParam(name="applesImage") MultipartFile file) throws IOException {
        Detection detection = fileService.sendFileToModel(file);
        System.out.println("here");
        System.out.println(file.getName());
        System.out.println(file.getContentType());
        System.out.println(file.getBytes());
        return detection;
    }

}
