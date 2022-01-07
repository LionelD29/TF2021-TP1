package be.technifutur.tp1.schedule;

import be.technifutur.tp1.activity.Activity;
import be.technifutur.tp1.activityType.ActivityType;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Schedule implements Serializable {
    private final Set<Activity> listActivity = new TreeSet<>(Activity.getComparator());

    public Set<Activity> getListActivity() {
        return this.listActivity;
    }

    public List<Activity> getActivitiesByName(String activityName) {
        List<Activity> l = new ArrayList<>();
        for (Activity a : listActivity) {
            if (a.getName().equals(activityName)) {
                l.add(a);
            }
        }
        return l;
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

    public Activity addActivity(LocalDateTime start, LocalDateTime end,
                                String name, ActivityType type) {

        Activity activity = new Activity(start, end, name, type);
        // add(activity) renvoit false si le set contient déjà un exemplaire de cette activité, au sens
        // du comparateur donné au constructeur du TreeSet ci-dessus.
        if (!listActivity.add(activity)) {
            activity = null;
        }
        return activity;
    }

    public boolean removeActivity(Activity activity) {
        return listActivity.remove(activity);
    }
}
