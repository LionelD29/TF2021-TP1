package be.technifutur.tp1.activity;

import be.technifutur.tp1.activityType.ActivityType;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

    public LocalDateTime getStart() {
        return this.start;
    }

    public ActivityType getType() {
        return this.type;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return name + " -- Debut : " + start.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")) + "\n" +
                " ".repeat(("* " + name).length()) +
                " -- Fin : " + end.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")) + "\n" +
                " ".repeat(("* " + name).length()) +
                " -- Inscription : " + (type.isRegistrationRequired() ? "obligatoire" : "facultative");
    }
}
