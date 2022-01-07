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
        Activity newActivity = null;
        Activity oldActivity = null;
        ActivityType activityType;

        scheduleView.printMessage("Modification de l'horaire d'une activite");

        activityName = scheduleView.selectActivity();

        List<Activity> matchingActivities = modelSchedule.getActivitiesByName(activityName);

        if (!matchingActivities.isEmpty()) {
            if (matchingActivities.size() > 1) {
                while (oldActivity == null) {
                    String activityIndex = scheduleView.selectActivityIndex(matchingActivities);
                    try {
                        int index = Integer.parseInt(activityIndex);
                        oldActivity = matchingActivities.get(index);
                    } catch (NumberFormatException | IndexOutOfBoundsException e) {
                        scheduleView.invalidChoice(activityIndex);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } else {
                oldActivity = matchingActivities.get(0);
            }
            activityType = oldActivity.getType();
            modelSchedule.removeActivity(oldActivity);

            start = scheduleView.chooseActivityTime("debut");
            while (start.isBefore(LocalDateTime.now())) {
                scheduleView.printMessage("L'activite ne peut pas debuter dans le passe");
                start = scheduleView.chooseActivityTime("debut");
            }

            end = scheduleView.chooseActivityTime("fin");
            while (end.isBefore(start)) {
                scheduleView.printMessage("La fin de l'activite doit etre posterieure a son debut");
                end = scheduleView.chooseActivityTime("fin");
            }

            // Si le set contient déjà cette activité modifiée, newActivity reçoit null, sinon
            // reçoit l'activité modifiée
            newActivity = modelSchedule.addActivity(start, end, activityName, activityType);
            if (newActivity != null) {
                scheduleView.printMessage("L'activite " + activityName + " a bien ete modifiee");
            } else {
                modelSchedule.addActivity(oldActivity);
                scheduleView.printMessage("Annulation de la modification ! ***\n" +
                        "*** Une activite au meme nom et meme horaire existe deja !");
            }
        } else {
            scheduleView.noSuchActivity(activityName);
        }
        return null;
    }
}
