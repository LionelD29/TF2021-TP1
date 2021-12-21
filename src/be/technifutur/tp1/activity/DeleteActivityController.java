package be.technifutur.tp1.activity;

import java.util.concurrent.Callable;

public class DeleteActivityController implements Callable<ActivityType> {
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
        String validation = "";

        activityView.printActivityList(model);

        System.out.println("Suppression d'une activite");
        System.out.println();

        name = activityView.inputActivityName();
        // Si le nom entré par l'utilisateur n'existe pas parmi les activités, on affiche un message d'erreur
        ActivityType activity = model.get(name);
        if (activity != null) {
            validation = activityView.deleteActivityValidation(activity);
            if (validation.matches("[on]")) {
                if (validation.equals("o")) {
                    model.remove(name);
                    activityView.confirmDelete();
                } else {
                    activityView.cancelActivityDelete();
                }
            } else {
                activityView.invalidYesNoInput(validation);
            }
        } else {
            activityView.noSuchActivity(name);
        }
        return null;
    }
}
