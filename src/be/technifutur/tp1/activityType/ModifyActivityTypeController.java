package be.technifutur.tp1.activityType;

import java.util.concurrent.Callable;

public class ModifyActivityTypeController implements Callable<ActivityType> {
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
            Cette méthode permet de modifier une activité existante
         */
        String userInput;
        ActivityType activity;

        activityView.printMessage("Modification d'une activite");

        userInput = activityView.inputActivityName();
        /*
           On récupère l'activité en supprimant l'entrée correspondante dans la map.
           On modifie si nécessaire cette activité ensuite
           Puis on la réinsère dans la map
        */
        activity = model.remove(userInput);
        if (activity != null) {
            activityView.printActivity(activity);

            // Nouveau nom
            // Si "", on ne modifie pas le nom précédent
            userInput = activityView.newActivityName();
            if(!userInput.equals("")) {
                activity.setName(userInput);
            }

            activityView.printActivity(activity);

            // Nouvelle inscription
            userInput = activityView.newActivityRegistration();
            // Tant que pas o, n ou "", on recommence la saisie
            while (!userInput.matches("|[onON]")) {
                activityView.invalidChoice(userInput);
                userInput = activityView.newActivityRegistration();
            }
            if (userInput.equalsIgnoreCase("o")) {
                activity.setRegistrationRequired(true);
            } else if (userInput.equalsIgnoreCase("n")){
                activity.setRegistrationRequired(false);
            }

            activityView.printActivity(activity);

            model.addActivityType(activity.getName(), activity.isRegistrationRequired());
        } else {
            activityView.noSuchActivity(userInput);
        }
        return null;
    }
}
