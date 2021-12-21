package be.technifutur.tp1.activity;

import java.util.concurrent.Callable;

public class CreateActivityController implements Callable<ActivityType> {
    private ActivityView activityView;
    private ListActivityType model;

    public void setActivityView(ActivityView activityView) {
        this.activityView = activityView;
    }

    public void setModel(ListActivityType model) {
        this.model = model;
    }

    @Override
    public ActivityType call() throws Exception {
        /*
            Cette méthode sert a controller l'opération de création d'une nouvelle activité
         */
        ActivityType newActivity = null;
        String name = "";
        String registrationString = "";
        boolean registration = false;

        activityView.printActivityList(model);

        System.out.println("Creation d'une nouvelle activite");
        System.out.println();

        name = activityView.inputActivityName();
        // Si une activité dans le modèle possède déjà le nom entré par l'utilisateur, on affiche un message d'erreur, ainsi
        // que l'activité en question.
        ActivityType oldActivity = model.get(name);
        if (oldActivity == null) {
            registrationString = activityView.inputActivityRegistration();
            if (registrationString.matches("[on]")) {
                if(registrationString.equals("o")) registration = true;

                newActivity = model.addActivityType(name, registration);
                activityView.showNewActivity(newActivity);
            } else {
                activityView.invalidYesNoInput(registrationString);
            }
        } else {
            activityView.alreadyExistingActivity(oldActivity);
        }
        return null;
    }
}
