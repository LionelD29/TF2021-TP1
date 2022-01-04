package be.technifutur.tp1.activityType;

import be.technifutur.menu.MenuController;

import java.util.concurrent.Callable;

public class ActivityTypeManagementController implements Callable<ActivityType> {
    private final MenuController controller;
    private final ListActivityType model;
    private final ActivityView activityView = new ActivityView();

    public ActivityTypeManagementController(MenuController activityManagementController,
                                            ListActivityType model) {
        this.controller = activityManagementController;
        this.model = model;
    }

    @Override
    public ActivityType call() throws Exception {

        activityView.printActivityList(model);
        Callable<?> action = controller.getAction();

        while (action != null) {
            try {
                action.call();
            } catch (Exception e) {
                System.out.println("Une erreur est survenue");
            }
            activityView.printActivityList(model);
            action = controller.getAction();
        }

        System.out.println("Retour au menu principal");
        return null;
    }
}
