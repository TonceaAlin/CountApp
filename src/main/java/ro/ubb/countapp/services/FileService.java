package ro.ubb.countapp.services;

import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import ro.ubb.countapp.domain.Detection;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Service
@Component
public class FileService {


    public Detection sendFileToModel(MultipartFile file) throws IOException {
        String url = "http://127.0.0.1:5000/callModel";
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.ALL));
        Map<String, Path> map = new HashMap<>();
        Path pathToImage = saveToTempFiles(file);
        map.put("image", pathToImage);
        HttpEntity<Map<String, Path>> entity = new HttpEntity<>(map, headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
        Detection detection = new Detection();
        detection.setNoDetectedApples(response.getBody());
        String resultName = "results/" + pathToImage.getFileName();
        File resultImage = new File(resultName);
        byte[] imageContent = Files.readAllBytes(resultImage.toPath());
        String encodedFile = Base64.getEncoder().encodeToString(imageContent);
        detection.setImageBytes(encodedFile);
        System.out.println(Paths.get(resultName));
        return detection;
    }

    private Path saveToTempFiles(MultipartFile file) {
        try {
            String[] contentType = Objects.requireNonNull(file.getContentType()).split("/");
            String extension = "."  +  contentType[1];
            Path tempFile = Files.createTempFile("file", extension);
            Path pathToTemp = Files.write(tempFile, file.getBytes());
            System.out.println(pathToTemp);
            return pathToTemp;
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
        return Paths.get("invalid/path");
    }
}
