package ro.ubb.countapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ro.ubb.countapp.domain.Detection;
import ro.ubb.countapp.services.FileService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/file")
@CrossOrigin("http://localhost:4200/")
public class CountController {

    @Autowired
    FileService fileService;

    @PostMapping(value = "/count-apples")
    @ResponseBody
    public Detection[] getNumberOfApples(@RequestParam(name="files[]") MultipartFile[] files) throws IOException{
        List<String> fileNames = new ArrayList<>();
        fileService.initFolder();
        Arrays.stream(files).forEach(file -> {
            System.out.println(file.getOriginalFilename());
            fileService.saveFileToFolder(file);
            fileNames.add(file.getOriginalFilename());
        });
        return fileService.sendPathToModel(fileNames);
    }
}
