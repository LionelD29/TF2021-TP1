package be.technifutur.tp1.activityType;

import java.util.concurrent.Callable;

public class DeleteActivityTypeController implements Callable<ActivityType> {
    private ListActivityType model;
    private ActivityView activityView;

    public void setModel(ListActivityType model) {
        this.model = model;
    }

    public void setActivityView(ActivityView view) {
        activityView = view;
    }

    @Override
    public ActivityType call() throws Exception {
        /*
            Cette méthode sert à controller l'opération de suppression d'une activité
         */
        String name;
        String validation;

        activityView.printMessage("Suppression d'une activite");

        name = activityView.inputActivityName();
        // Si le nom entré par l'utilisateur n'existe pas parmi les activités, on affiche un message d'erreur
        ActivityType activity = model.get(name);
        if (activity != null) {
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
            activityView.noSuchActivity(name);
        }
        return null;
    }
}
