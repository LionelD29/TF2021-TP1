package be.technifutur.tp1.schedule;

import be.technifutur.tp1.activity.Activity;
import be.technifutur.tp1.activityType.ListActivityType;

import java.util.concurrent.Callable;

public class DeleteActivityController implements Callable<Activity> {
    private Schedule modelSchedule;
    private ListActivityType modelActivityType;
    private ScheduleView scheduleView;

    public void setModelSchedule(Schedule modelSchedule) {
        this.modelSchedule = modelSchedule;
    }

    public void setModelActivityType(ListActivityType modelActivityType) {
        this.modelActivityType = modelActivityType;
    }

    public void setScheduleView(ScheduleView scheduleView) {
        this.scheduleView = scheduleView;
    }

    @Override
    public Activity call() throws Exception {
        System.out.println("Suppression d'une activite de l'horaire du stage");
        return null;
    }
}
