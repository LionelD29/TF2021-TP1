package be.technifutur.tp1.schedule;

import be.technifutur.tp1.activity.Activity;
import be.technifutur.tp1.activityType.ActivityType;

import java.time.LocalDateTime;
import java.util.List;
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
        Activity activity = null;
        ActivityType activityType;

        scheduleView.printMessage("Modification de l'horaire d'une activite");

        activityName = scheduleView.selectActivity();

        List<Activity> matchingActivities = modelSchedule.getActivitiesByName(activityName);

        if (!matchingActivities.isEmpty()) {
            if (matchingActivities.size() > 1) {
                while (activity == null) {
                    String activityIndex = scheduleView.selectActivityIndex(matchingActivities);
                    try {
                        int index = Integer.parseInt(activityIndex);
                        activity = matchingActivities.get(index);
                    } catch (NumberFormatException | IndexOutOfBoundsException e) {
                        scheduleView.invalidChoice(activityIndex);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } else {
                activity = matchingActivities.get(0);
            }
            activityType = activity.getType();
            modelSchedule.removeActivity(activity);

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
