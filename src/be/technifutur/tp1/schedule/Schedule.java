package be.technifutur.tp1.schedule;

import be.technifutur.tp1.activity.Activity;
import be.technifutur.tp1.activityType.ActivityType;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.TreeSet;

public class Schedule implements Serializable {
    private Set<Activity> listActivity = new TreeSet<>(new StartTimeComparator());

    public Activity addActivity(LocalDateTime start, LocalDateTime end,
                                String name, ActivityType type) {

        Activity activity = new Activity(start, end, name, type);
        listActivity.add(activity);
        return activity;
    }

    public Set<Activity> getListActivity() {
        return this.listActivity;
    }

    public Activity getActivity(String name) {
        Activity activity = null;
        for (Activity a : listActivity) {
            if (a.getName().equals(name)) {
                activity = a;
            }
        }
        return activity;
    }
}
