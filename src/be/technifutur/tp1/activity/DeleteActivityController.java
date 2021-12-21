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
        String name;
        String validation = "";

        activityView.printActivityList(model);

        System.out.println("Suppression d'une activite");
        System.out.println();

        name = activityView.inputActivityName();

        if (model.get(name) != null) {
            validation = activityView.deleteActivityValidation(model, name);
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
