package be.technifutur.tp1.activity;

import java.util.concurrent.Callable;

public class CreateActivityTypeController implements Callable<ActivityType> {
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

        activityView.printMessage("Creation d'une nouvelle activite");

        name = activityView.inputActivityName();
        // Si une activité dans le modèle possède déjà le nom entré par l'utilisateur, on affiche un message d'erreur, ainsi
        // que l'activité en question.
        ActivityType oldActivity = model.get(name);
        if (oldActivity == null) {
            registrationString = activityView.inputActivityRegistration();
            if (registrationString.matches("[onON]")) {
                if(registrationString.equalsIgnoreCase("o")) registration = true;

                newActivity = model.addActivityType(name, registration);
                activityView.printMessage("Nouvelle activite ajoutee");
                activityView.printActivity(newActivity);
            } else {
                activityView.invalidChoice(registrationString);
            }
        } else {
            activityView.printMessage("L'activite " + oldActivity.getName() + " existe deja dans la liste");
            activityView.printActivity(oldActivity);
        }
        return null;
    }
}
