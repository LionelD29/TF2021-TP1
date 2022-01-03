package be.technifutur.tp1.activityType;

import java.util.concurrent.Callable;

public class ReadActivityTypeController implements Callable<ActivityType> {
    private ListActivityType modelActivityType;
    private ActivityView activityView;

    public void setModel(ListActivityType modelActivityType) {
        this.modelActivityType = modelActivityType;
    }

    public void setActivityView(ActivityView activityView) {
        this.activityView = activityView;
    }

    @Override
    public ActivityType call() throws Exception {
        activityView.printActivityList(modelActivityType);
        return null;
    }
}
