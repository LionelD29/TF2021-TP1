package be.technifutur.tp1.schedule;

import be.technifutur.tp1.activity.Activity;

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
        String activityName = scheduleView.selectActivity();
        Activity activity = modelSchedule.getActivity(activityName);
        if (activity != null) {
            confirmation = scheduleView.confirmDelete(activityName);
            while (!confirmation.matches("[onON]")) {
                scheduleView.invalidChoice(confirmation);
                confirmation = scheduleView.confirmDelete(activityName);
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
