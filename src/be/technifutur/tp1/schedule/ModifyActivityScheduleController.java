package be.technifutur.tp1.schedule;

import be.technifutur.tp1.activity.Activity;
import be.technifutur.tp1.activityType.ActivityType;

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
        LocalDateTime start;
        LocalDateTime end;
        String activityName;
        Activity activity;
        ActivityType activityType;

        scheduleView.printMessage("Modification de l'horaire d'une activite");

        activityName = scheduleView.selectActivity();
        activity = modelSchedule.getActivity(activityName);
        activityType = activity.getType();

        if (modelSchedule.removeActivity(activity)) {
            start = scheduleView.chooseActivityTime("debut");
            while (start.isBefore(LocalDateTime.now())) {
                scheduleView.printMessage("L'activite ne peut pas debuter dans le passe");
                start = scheduleView.chooseActivityTime("debut");
            }

            end = scheduleView.chooseActivityTime("fin");
            while (end.isBefore(activity.start)) {
                scheduleView.printMessage("La fin de l'activite doit etre posterieure a son debut");
                end = scheduleView.chooseActivityTime("fin");
            }

            modelSchedule.addActivity(start, end, activityName, activityType);

            scheduleView.printMessage("L'activite " + activityName + " a bien ete modifiee");
        } else {
            scheduleView.noSuchActivity(activityName);
        }
        return null;
    }
}
