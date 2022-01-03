package be.technifutur.tp1.schedule;

import be.technifutur.tp1.activity.Activity;

import java.time.LocalDateTime;
import java.util.Comparator;

public class StartTimeComparator implements Comparator<Activity> {
    @Override
    public int compare(Activity a1, Activity a2) {
        int i = a1.getStart().compareTo(a2.getStart());
        return i;
    }
}
