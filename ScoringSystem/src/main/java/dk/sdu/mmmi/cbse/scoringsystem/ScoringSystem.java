package dk.sdu.mmmi.cbse.scoringsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
@RestController
public class ScoringSystem {

    private int totalScore = 0;

    public static void main(String[] args) {
        SpringApplication.run(ScoringSystem.class, args);
    }


    @GetMapping("/score")
    public int getScore() {
        return totalScore;
    }
    @PostMapping("/score/increment")
    public int incrementScore(@RequestBody Score score) {
        totalScore++;
        return totalScore;
    }
}
