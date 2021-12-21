package be.technifutur.util;

import be.technifutur.menu.MenuController;

import java.util.concurrent.Callable;

public abstract class Util {
    public static void callAction(MenuController controller) {
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
}
