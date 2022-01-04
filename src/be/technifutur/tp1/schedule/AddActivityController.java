package be.technifutur.tp1.schedule;

import be.technifutur.tp1.activity.Activity;
import be.technifutur.tp1.activityType.ActivityType;
import be.technifutur.tp1.activityType.CreateActivityTypeController;
import be.technifutur.tp1.activityType.ListActivityType;

import java.time.LocalDateTime;
import java.util.concurrent.Callable;

public class AddActivityController implements Callable<Activity> {
    private Schedule modelSchedule;
    private ListActivityType modelActivityType;
    private ScheduleView scheduleView;
    private CreateActivityTypeController createActivityTypeController;

    public void setModelSchedule(Schedule modelSchedule) {
        this.modelSchedule = modelSchedule;
    }

    public void setModelActivityType(ListActivityType modelActivityType) {
        this.modelActivityType = modelActivityType;
    }

    public void setScheduleView(ScheduleView scheduleView) {
        this.scheduleView = scheduleView;
    }

    public void setCreateActivityTypeController(CreateActivityTypeController createActivityTypeController) {
        this.createActivityTypeController = createActivityTypeController;
    }

    @Override
    public Activity call() throws Exception {
        String activityTypeName = scheduleView.selectActivityType();
        ActivityType type = modelActivityType.get(activityTypeName);
        if (type != null) {
            addActivity(type);
        } else {
            // Si le type d'activité n'existe pas encore, on propose de le créer pour l'utiliser ensuite
            // comme type d'activité de notre nouvelle activité
            String confirmation = scheduleView.confirmCreation();
            while (!confirmation.matches("[onON]")) {
                scheduleView.invalidChoice(confirmation);
                confirmation = scheduleView.confirmCreation();
            }

            if (confirmation.equalsIgnoreCase("o")) {
                type = createActivityTypeController.call(activityTypeName);
                addActivity(type);
            } else {
                scheduleView.printMessage("Le type d'activite n'a pas ete cree");
            }
        }
        return null;
    }

    private void addActivity(ActivityType type) {
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
        Activity newActivity = modelSchedule.addActivity(start, end, activityName, type);
        if (newActivity != null) {
            scheduleView.printMessage("L'activite " + newActivity.getName() + " a bien ete ajoutee");
        } else {
            scheduleView.printMessage("L'activite " + activityName + " n'a pas ete ajoutee ***" +
                    "\n" + "*** Impossible d'ajouter deux activites du meme type sur la meme plage horaire");
        }
    }
}
