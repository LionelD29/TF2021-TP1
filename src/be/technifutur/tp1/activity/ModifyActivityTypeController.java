package be.technifutur.tp1.activity;

import java.util.Map;
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
        String userInput = "";
        ActivityType activity;
        String activityName = "";

        System.out.println("Modification d'une activite");
        System.out.println();

        userInput = activityView.inputActivityName();
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
            while (!userInput.matches("|[on]")) {
                activityView.invalidYesNoInput(userInput);
                userInput = activityView.newActivityRegistration();
            }
            if (userInput.equals("o")) {
                activity.setRegistrationRequired(true);
            } else if (userInput.equals("n")){
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
