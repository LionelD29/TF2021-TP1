package be.technifutur.tp1.activity;

import be.technifutur.tp1.datastore.DataStore;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class ListActivityType implements Serializable {
    private DataStore< HashMap<String, ActivityType> > myDataStore = new DataStore<>("ressources/datastore.txt", HashMap::new);
    private Map<String, ActivityType> activityTypes = myDataStore.getData();

    public ActivityType addActivityType(String name, boolean registrationRequired) {
        ActivityType newActivity = null;
        if (name != null && !name.equals("")) {
            newActivity = new ActivityType(name, registrationRequired);
        }
        return activityTypes.put(name, newActivity) == null ? newActivity : null;
    }

    public Map<String, ActivityType> getActivityList() {
        return this.activityTypes;
    }

    public ActivityType get(String name) {
        return activityTypes.get(name);
    }

    public ActivityType remove(String name) {
        return activityTypes.remove(name);
    }

    public void saveDataStore() {
        myDataStore.save();
    }
}