package demo.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Worker {
    @Id
    private String hostname;
    private LocalDateTime currentTime;
    public Worker() {
    }

    public LocalDateTime getCurrentTime() {
        return currentTime;
    }

    public Worker(String hostname) {
        this.hostname = hostname;
        this.currentTime = LocalDateTime.now();
    }

    public String getHostname() {
        return hostname;
    }
    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    @Override
    public String toString(){
        return this.hostname +" ";
    }
}
