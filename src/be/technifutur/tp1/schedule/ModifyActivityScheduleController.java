package be.technifutur.tp1.schedule;

import be.technifutur.tp1.activity.Activity;
import be.technifutur.tp1.activityType.ActivityType;
import be.technifutur.tp1.activityType.ListActivityType;

import java.time.LocalDateTime;
import java.util.concurrent.Callable;

public class ModifyActivityScheduleController implements Callable<Activity> {
    private Schedule modelSchedule;
    private ScheduleView scheduleView;

    public void setModelSchedule(Schedule modelSchedule) {
        this.modelSchedule = modelSchedule;
    }

    public void setScheduleView(ScheduleView scheduleView) {
        this.scheduleView = scheduleView;
    }

    @Override
    public Activity call() throws Exception {
        LocalDateTime time;
        String activityName = scheduleView.selectActivity();
        Activity activity = modelSchedule.getActivity(activityName);
        if (activity != null) {
            time = scheduleView.chooseActivityTime("debut");
            while (time.compareTo(LocalDateTime.now()) <= 0) {
                scheduleView.printMessage("L'activite ne peut pas debuter dans le passe");
                time = scheduleView.chooseActivityTime("debut");
            }
            activity.start = time;
            time = scheduleView.chooseActivityTime("fin");
            while (time.compareTo(activity.start) <= 0) {
                scheduleView.printMessage("La fin de l'activite doit etre posterieure a son debut");
                time = scheduleView.chooseActivityTime("fin");
            }
            activity.end = time;
        } else {
            scheduleView.noSuchActivityType(activityName);
        }
        return null;
    }
}
