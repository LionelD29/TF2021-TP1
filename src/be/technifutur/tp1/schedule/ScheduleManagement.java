package be.technifutur.tp1.schedule;

import be.technifutur.menu.MenuController;
import be.technifutur.util.Util;

import java.util.concurrent.Callable;

public class ScheduleManagement implements Callable<Object> {
    private MenuController controller;

    public ScheduleManagement(MenuController controller) {
        this.controller = controller;
    }

    @Override
    public Object call() throws Exception {
        // Fait tourner le menu de gestion des horaires
        Util.callAction(controller);
        System.out.println("Retour au menu principal");
        return null;
    }
}
