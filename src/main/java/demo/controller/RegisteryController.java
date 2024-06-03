package demo.controller;

import demo.model.Worker;
import demo.repository.WorkerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClient;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.stream.Stream;

@RestController
@RequestMapping("/workers")
public class RegisteryController {
    private String hostname;
    private Stream<Worker> workersInit;
    @Autowired
    private WorkerRepository workersRepo;

    @Transactional
    @GetMapping()
    public ResponseEntity<Object> getUsers() {
        Stream<Worker> s = workersRepo.streamAllBy();
        workersInit = s;
        return new ResponseEntity<>(s.toList(), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Worker> put(@RequestBody Worker worker) {
        Stream<Worker> s = workersRepo.streamAllBy();
        s.forEach(w ->{
            if (LocalDateTime.now().getSecond() - w.getCurrentTime().getSecond() > 12000) {
                workersRepo.delete(w);
            }
        });
        workersRepo.save(worker);
        return new ResponseEntity<>(worker, HttpStatus.OK);
    }

    /*@Scheduled(fixedRate = 120000)
    public void getWorkers(){
        Stream<Worker> s = workersRepo.streamAllBy();
        if (this.workersInit.toList().size() != s.toList().size()){
            RestClient restClient = RestClient.create();
            restClient.post()
                    .uri("http://localhost:8080/hi")
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(s.toList()).retrieve();
        }
       // return new ResponseEntity<>(null,HttpStatus.OK);
    }*/
}
