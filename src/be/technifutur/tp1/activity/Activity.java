package be.technifutur.tp1.activity;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Activity implements Serializable {
    public LocalDateTime start;
    public LocalDateTime end;
    public String name;
    public ActivityType type;

    public Activity(LocalDateTime start, LocalDateTime end,
                    String name, ActivityType type) {
        this.start = start;
        this.end = end;
        this.name = name;
        this.type = type;
    }
}
