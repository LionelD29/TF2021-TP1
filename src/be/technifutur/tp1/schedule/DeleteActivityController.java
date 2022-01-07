package be.technifutur.tp1.schedule;

import be.technifutur.tp1.activity.Activity;

import java.util.List;
import java.util.concurrent.Callable;

public class DeleteActivityController implements Callable<Activity> {
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
        String confirmation;

        scheduleView.printMessage("Suppression d'une activite de l'horaire");

        String activityName = scheduleView.selectActivity();

        List<Activity> matchingActivities = modelSchedule.getActivitiesByName(activityName);

        if (!matchingActivities.isEmpty()) {
            // Selection de la bonne activité
            Activity activity = null;
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

            confirmation = scheduleView.confirmDelete(activity);
            while (!confirmation.matches("[onON]")) {
                scheduleView.invalidChoice(confirmation);
                confirmation = scheduleView.confirmDelete(activity);
            }
            if (confirmation.equalsIgnoreCase("o")) {
                modelSchedule.getListActivity().remove(activity);
                scheduleView.printMessage("L'activite " + activityName + " a bien ete supprimee");
            } else {
                scheduleView.printMessage("Annulation de la suppression de l'activite " + activityName);
            }
        } else {
            scheduleView.noSuchActivity(activityName);
        }
        return null;
    }
}
