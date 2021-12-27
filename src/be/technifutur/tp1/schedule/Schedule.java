package be.technifutur.tp1.schedule;

import be.technifutur.tp1.activity.Activity;
import be.technifutur.tp1.activity.ActivityType;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Schedule implements Serializable {
    public Map<ActivityType, List<Activity>> listActivity = new HashMap<>();

    public Activity addActivity(LocalDateTime start, LocalDateTime end,
                                String name, ActivityType type) {

        List<Activity> l = listActivity.get(type);
        if (l == null) {
            l = new ArrayList<>();
            listActivity.put(type, l);
        }
        Activity activity = new Activity(start, end, name, type);
        l.add(activity);
        return activity;
    }
}
