package be.technifutur.tp1.schedule;

import be.technifutur.menu.MenuController;
import be.technifutur.util.Util;

import java.util.concurrent.Callable;

public class ScheduleManagementController implements Callable<Object> {
    private final MenuController controller;
    private final Schedule modelSchedule;
    private final ScheduleView scheduleView = new ScheduleView();

    public ScheduleManagementController(MenuController controller, Schedule modelSchedule) {
        this.controller = controller;
        this.modelSchedule = modelSchedule;
    }

    @Override
    public Object call() throws Exception {
        // Fait tourner le menu de gestion des horaires
        Util.callAction(controller, modelSchedule, scheduleView);
        System.out.println("Retour au menu principal");
        return null;
    }
}
