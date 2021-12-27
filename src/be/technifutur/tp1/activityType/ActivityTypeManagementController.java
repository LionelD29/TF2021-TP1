package be.technifutur.tp1.activityType;

import be.technifutur.menu.MenuController;
import be.technifutur.util.Util;

import java.util.concurrent.Callable;

public class ActivityTypeManagementController implements Callable<ActivityType> {
    private MenuController controller;
    private ListActivityType model;
    private ActivityView activityView = new ActivityView();

    public ActivityTypeManagementController(MenuController activityManagementController,
                                            ListActivityType model) {
        this.controller = activityManagementController;
        this.model = model;
    }

    @Override
    public ActivityType call() throws Exception {
        // Fait tourner le menu de gestion des activites
        Util.callAction(controller, model, activityView);
        System.out.println("Retour au menu principal");
        return null;
    }
}
