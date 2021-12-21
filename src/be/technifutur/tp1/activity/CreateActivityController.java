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
        ActivityType newActivity = null;
        String name = "";
        String registrationString = "";
        boolean registration = false;

        activityView.printActivityList(model);

        System.out.println("Creation d'une nouvelle activite");
        System.out.println();

        name = activityView.inputActivityName();
        // Si une activité dans le modèle possède déjà ce nom, on affiche un message d'erreur, ainsi
        // que l'activité en question.
        if (model.get(name) == null) {
            registrationString = activityView.inputActivityRegistration();
            if (registrationString.matches("[on]")) {
                if(registrationString.equals("o")) registration = true;

                newActivity = model.addActivityType(name, registration);
                activityView.showNewActivity(newActivity);
            } else {
                activityView.invalidYesNoInput(registrationString);
            }

        } else {
            activityView.alreadyExistingActivity(name, model);
        }
        return null;
    }
}
