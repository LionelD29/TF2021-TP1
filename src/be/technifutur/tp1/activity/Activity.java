package be.technifutur.tp1.activity;

import static be.technifutur.tp1.serializableComparator.GetSerializableFunction.makeSerializable;

import be.technifutur.tp1.activityType.ActivityType;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;

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

    public LocalDateTime getEnd() {
        return this.end;
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
                " -- Type : " + type.getName() + "\n" +
                " ".repeat(("* " + name).length()) +
                " -- Inscription : " + (type.isRegistrationRequired() ? "oui" : "non");
    }

    // Version avec les expressions lambdas
    public static Comparator<Activity> getComparator() {

        return Comparator
                .comparing(makeSerializable(Activity::getStart))
                .thenComparing(makeSerializable(Activity::getEnd))
                .thenComparing(makeSerializable(Activity::getName));
    }
    /*
            .thenComparing(makeSerializable(Activity::getType),
                           Comparator.comparing(makeSerializable(ActivityType::getName)));
        // ActivityType n'étant pas Comparable, il faut donner en plus un Comparator qui explique
        // comment les comparer. Ici, leur comparaison se fait uniquement sur leur nom.
    */


    // Version avec la classe interne anonyme qui étend la classe abstraite GetSerializableComparator
    // GetSerializableComparator implémente une interface qui est Serializable car les Activity sont Serializable
    // Si le Comparator n'est pas Serializable, ça génère une erreur.
    /*public static Comparator<Activity> getComparator() {
        return new GetSerializableComparator<Activity>() {
            @Override
            public int compare(Activity a1, Activity a2) {
                int i = a1.getStart().compareTo(a2.getStart());
                if (i == 0) {
                    i = a1.getEnd().compareTo(a2.getEnd());
                    if (i == 0) {
                        i = a1.getName().compareTo(a2.getName());
                    }
                }
                return i;
            }
        };
    }*/


    // Version avec la classe interne qui étend la classe abstraite GetSerializableComparator
    /*public static Comparator<Activity> getComparator() {
        class GetComparator extends GetSerializableComparator<Activity> {
            @Override
            public int compare(Activity a1, Activity a2) {
                int i = a1.getStart().compareTo(a2.getStart());
                if (i == 0) {
                    i = a1.getEnd().compareTo(a2.getEnd());
                    if (i == 0) {
                        i = a1.getName().compareTo(a2.getName());
                    }
                }
                return i;
            }
        }
        return new GetComparator();
    }*/
}
