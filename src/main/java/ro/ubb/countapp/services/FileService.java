package ro.ubb.countapp.services;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import ro.ubb.countapp.domain.Detection;
import ro.ubb.countapp.repository.DetectionRepository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Component
public class FileService {

    @Autowired
    DetectionRepository repository;

    @Autowired
    SessionService  sessionService;
    public static final String folderPath = "incoming-files//";
    public static final Path filePath = Paths.get(folderPath);

    public void saveFileToFolder(MultipartFile file){
        try{
            byte[] bytes = file.getBytes();
            Files.write(Paths.get(folderPath + file.getOriginalFilename()), bytes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Detection[] sendPathToModel(List<String> fileNames, Long sessionId) {
        String url = "http://127.0.0.1:5000/callModel";
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.ALL));
        Map<String, Path> map = new HashMap<>();
        map.put("image", Paths.get(folderPath).toAbsolutePath());
        HttpEntity<Map<String, Path>> entity = new HttpEntity<>(map, headers);
        ResponseEntity<Object[]> response = restTemplate.exchange(url, HttpMethod.POST, entity, Object[].class);

        List<Detection> detections  = new ArrayList<>();
//        var ses_det = session.getDetections();
        List<String> results = Arrays.stream(Objects.requireNonNull(response.getBody())).map(Object::toString).collect(Collectors.toList());
        fileNames.forEach(file -> {
            try {
                var detect = new Detection(results.get(fileNames.indexOf(file)), Files.readAllBytes(Paths.get("results/" + file)));
                detections.add(detect);
                var session = sessionService.getSessionById(sessionId);
                detect.setSession(session);
                this.repository.save(detect);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        return detections.toArray(new Detection[detections.size()]);
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

    public void initFolder() throws IOException {
        FileUtils.cleanDirectory(new File(folderPath));
    }

    public Detection[] getDetectionsBySessionId(Long sessionId){
        return this.repository.findAllBySessionId(sessionId);
    }

}
