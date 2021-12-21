package be.technifutur.tp1.activity;

import be.technifutur.menu.MenuController;
import be.technifutur.menu.MenuFactory;
import be.technifutur.util.Util;

import java.util.concurrent.Callable;

public class ActivityManagement implements Callable<ActivityType> {
    private MenuController controller;

    public ActivityManagement(MenuController activityManagementController) {
        this.controller = activityManagementController;
    }

    @Override
    public ActivityType call() {
        Util.callAction(controller);
        System.out.println("Retour au menu principal");
        return null;
    }
}
