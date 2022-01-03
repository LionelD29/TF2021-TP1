package be.technifutur.util;

import be.technifutur.menu.MenuController;
import be.technifutur.tp1.activityType.ActivityView;
import be.technifutur.tp1.activityType.ListActivityType;
import be.technifutur.tp1.schedule.Schedule;
import be.technifutur.tp1.schedule.ScheduleView;

import java.util.concurrent.Callable;

public abstract class Util {

    public static void callAction(MenuController controller) {
        /*
            Cette méthode permet de faire tourner en boucle un menu donné en paramètre jusqu'à
            ce que l'utilisateur décide de le quitter (action == null)
         */
        Callable<? extends Object> action = controller.getAction();

        while (action != null) {
            try {
                action.call();
            } catch (Exception e) {
                System.out.println("Une erreur est survenue");
            }
            action = controller.getAction();
        }
    }
    public static void callAction(MenuController controller, ListActivityType model, ActivityView view) {
        /*
            Cette méthode permet de faire tourner en boucle un menu donné en paramètre jusqu'à
            ce que l'utilisateur décide de le quitter (action == null)
         */
        view.printActivityList(model);
        Callable<? extends Object> action = controller.getAction();

        while (action != null) {
            try {
                action.call();
            } catch (Exception e) {
                System.out.println("Une erreur est survenue");
            }
            view.printActivityList(model);
            action = controller.getAction();
        }
    }

    public static void callAction(MenuController controller, Schedule schedule, ScheduleView view) {
        /*
            Cette méthode permet de faire tourner en boucle un menu donné en paramètre jusqu'à
            ce que l'utilisateur décide de le quitter (action == null)
         */
        view.printSchedule(schedule);
        Callable<? extends Object> action = controller.getAction();

        while (action != null) {
            try {
                action.call();
            } catch (Exception e) {
                System.out.println("Une erreur est survenue");
            }
            view.printSchedule(schedule);
            action = controller.getAction();
        }
    }
}
