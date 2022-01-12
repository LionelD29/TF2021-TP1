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
        String name;

        activityView.printMessage("Creation d'un nouveau type d'activite");
        name = activityView.inputActivityName();

        call(name);
        return null;
    }

    public ActivityType call(String name) throws Exception {
        /*
            Cette méthode sert a controller l'opération de création d'un nouveau type d'activite
            depuis le controller AddActivityController qui sert à ajouter une activité à l'horaire
         */
        ActivityType newActivityType = null;
        String registrationString;
        boolean registration;

        // Si un type d'activité dans le modèle possède déjà le nom entré par l'utilisateur, on affiche un message d'erreur, ainsi
        // que l'activité en question.
        ActivityType oldActivity = model.get(name);
        if (oldActivity == null) {
            registrationString = activityView.inputActivityRegistration();
            while (!registrationString.matches("[onON]")) {
                activityView.invalidChoice(registrationString);
                registrationString = activityView.inputActivityRegistration();
            }
            registration = registrationString.equalsIgnoreCase("o");

            newActivityType = model.addActivityType(name, registration);
            activityView.printMessage("Nouveau type d'activite ajoutee");
            activityView.printActivity(newActivityType);
        } else {
            activityView.printMessage("Le type d'activite " + oldActivity.getName() + " existe deja dans la liste");
            activityView.printActivity(oldActivity);
        }
        // On renvoit le nouveau type d'activité afin de l'utiliser immédiatement dans
        // AddActivityController comme étant le type d'activité de l'activité que l'on souhaite
        // ajouter à l'horaire
        return newActivityType;
    }
}
