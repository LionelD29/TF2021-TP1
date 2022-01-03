package be.technifutur.tp1.activityType;

import be.technifutur.tp1.activity.Activity;
import be.technifutur.tp1.schedule.Schedule;

import java.util.concurrent.Callable;

public class DeleteActivityTypeController implements Callable<ActivityType> {
    private ListActivityType model;
    private ActivityView activityView;
    private Schedule schedule;

    public void setModel(ListActivityType model) {
        this.model = model;
    }

    public void setActivityView(ActivityView view) {
        activityView = view;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    @Override
    public ActivityType call() throws Exception {
        /*
            Cette méthode sert à controller l'opération de suppression d'une activité
         */
        String name;
        String validation;
        boolean isActivityTypeUsed = false;

        activityView.printMessage("Suppression d'une activite");

        name = activityView.inputActivityName();
        // Si le nom entré par l'utilisateur n'existe pas parmi les activités, on affiche un message d'erreur
        ActivityType activity = model.get(name);

        if (activity != null) {
            // Si l'horaire contient une activité de ce type, on ne peut pas supprimer ce type d'activité.
            for (Activity a : schedule.getListActivity()) {
                if (a.getType().getName().equals(name)) {
                    isActivityTypeUsed = true;
                }
            }

            if (!isActivityTypeUsed) {
                validation = activityView.deleteActivityValidation(activity);
                if (validation.matches("[onON]")) {
                    if (validation.equalsIgnoreCase("o")) {
                        model.remove(name);
                        activityView.printMessage("Activite supprimee");
                    } else {
                        activityView.printMessage("Suppression annulee");
                    }
                } else {
                    activityView.invalidChoice(validation);
                }
            } else {
                activityView.usedActivity(activity.getName());
            }

        } else {
            activityView.noSuchActivity(name);
        }
        return null;
    }
}
