package be.technifutur.tp1.schedule;

import be.technifutur.menu.MenuController;

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

        scheduleView.printSchedule(modelSchedule);
        Callable<?> action = controller.getAction();

        while (action != null) {
            try {
                action.call();
            } catch (Exception e) {
                System.out.println("Une erreur est survenue");
                e.printStackTrace();
            }
            scheduleView.printSchedule(modelSchedule);
            action = controller.getAction();
        }

        System.out.println("Retour au menu principal");
        return null;
    }
}
