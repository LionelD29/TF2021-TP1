package be.technifutur.tp1.activityType;

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
            Cette méthode sert a controller l'opération de création d'un nouveau type d'activite
         */
        ActivityType newActivity;
        String name;
        String registrationString;
        boolean registration = false;

        activityView.printMessage("Creation d'un nouveau type d'activite");

        name = activityView.inputActivityName();
        // Si un type d'activité dans le modèle possède déjà le nom entré par l'utilisateur, on affiche un message d'erreur, ainsi
        // que l'activité en question.
        ActivityType oldActivity = model.get(name);
        if (oldActivity == null) {
            registrationString = activityView.inputActivityRegistration();
            if (registrationString.matches("[onON]")) {
                if(registrationString.equalsIgnoreCase("o")) registration = true;

                newActivity = model.addActivityType(name, registration);
                activityView.printMessage("Nouveau type d'activite ajoutee");
                activityView.printActivity(newActivity);
            } else {
                activityView.invalidChoice(registrationString);
            }
        } else {
            activityView.printMessage("Le type d'activite " + oldActivity.getName() + " existe deja dans la liste");
            activityView.printActivity(oldActivity);
        }
        return null;
    }
}
