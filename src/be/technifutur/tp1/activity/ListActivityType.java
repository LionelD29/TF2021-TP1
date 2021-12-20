package be.technifutur.tp1.activity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class ListActivityType implements Serializable {
    private Map<String, ActivityType> activityTypes = new HashMap<>();

    public ActivityType addActivityType(String name, boolean registrationRequired) {
        ActivityType newActivity = null;
        if (name != null && !name.equals("")) {
            newActivity = new ActivityType(name, registrationRequired);
        }
        return activityTypes.put(name, newActivity) == null ? newActivity : null;
    }

    public ActivityType get(String name) {
        return activityTypes.get(name);
    }

    public ActivityType remove(String name) {
        return activityTypes.remove(name);
    }
}