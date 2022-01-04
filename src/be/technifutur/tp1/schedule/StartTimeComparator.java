package be.technifutur.tp1.schedule;

import be.technifutur.tp1.activity.Activity;

import java.io.Serializable;
import java.util.Comparator;

public class StartTimeComparator implements Comparator<Activity>, Serializable {
    @Override
    public int compare(Activity a1, Activity a2) {
        int i = a1.getStart().compareTo(a2.getStart());
        if (i == 0) {
            i = a1.getEnd().compareTo(a2.getEnd());
        }
        return i;
    }
}
