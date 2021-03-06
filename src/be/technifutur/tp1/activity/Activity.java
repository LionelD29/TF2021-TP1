package be.technifutur.tp1.activity;

import static be.technifutur.tp1.serializableComparator.GetSerializableFunction.makeSerializable;

import be.technifutur.tp1.activityType.ActivityType;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;

public class Activity implements Serializable {
    private final LocalDateTime start;
    private final LocalDateTime end;
    private String name;
    private ActivityType type;

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

    public void setName(String name) {
        this.name = name;
    }

    public void setType(ActivityType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        String tabulations = " ".repeat(("* " + name).length());
        return name + " -- Debut : " + start.format(formatter) + "\n"
                + tabulations + " -- Fin : " + end.format(formatter) + "\n"
                + tabulations + " -- Type : " + type.getName() + "\n"
                + tabulations + " -- Inscription : " + (type.isRegistrationRequired() ? "oui" : "non");
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
        // ActivityType n'??tant pas Comparable, il faut donner en plus un Comparator qui explique
        // comment les comparer. Ici, leur comparaison se fait uniquement sur leur nom.
    */


    // Version avec la classe interne anonyme qui ??tend la classe abstraite GetSerializableComparator
    // GetSerializableComparator impl??mente une interface qui est Serializable car les Activity sont Serializable
    // Si le Comparator n'est pas Serializable, ??a g??n??re une erreur.
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


    // Version avec la classe interne qui ??tend la classe abstraite GetSerializableComparator
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
