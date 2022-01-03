package be.technifutur.tp1.schedule;

import be.technifutur.tp1.activity.Activity;
import be.technifutur.tp1.activityType.ActivityType;
import be.technifutur.tp1.activityType.ListActivityType;

import java.time.LocalDateTime;
import java.util.concurrent.Callable;

public class AddActivityController implements Callable<Activity> {
    Schedule modelSchedule;
    ListActivityType modelActivityType;
    ScheduleView scheduleView;

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
        String activityType = scheduleView.selectActivityType();
        ActivityType type = modelActivityType.get(activityType);
        if (type != null) {
            String activityName = scheduleView.chooseActivityName();
            LocalDateTime start = scheduleView.chooseActivityTime("debut");
            while (start.compareTo(LocalDateTime.now()) <= 0) {
                scheduleView.printMessage("L'activite ne peut pas debuter dans le passe");
                start = scheduleView.chooseActivityTime("debut");
            }
            LocalDateTime end = scheduleView.chooseActivityTime("fin");
            while (end.compareTo(start) <= 0) {
                scheduleView.printMessage("La fin de l'activite doit etre posterieure a son debut");
                end = scheduleView.chooseActivityTime("fin");
            }
            modelSchedule.addActivity(start, end, activityName, type);
        } else {
            scheduleView.noSuchActivityType(activityType);
        }
        return null;
    }
}
