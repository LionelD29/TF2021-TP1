package be.technifutur.tp1.activity;

import java.util.concurrent.Callable;

public class CreateActivity implements Callable<ActivityType> {
    private ListActivityType listActivities = new ListActivityType();

    @Override
    public ActivityType call() throws Exception {
        ActivityFactory factory = new ActivityFactory();
        CreateActivityController controller = factory.getCreateActivityController(listActivities);
        controller.createActivity();
        return null;
    }
}
