package be.technifutur.util;

import be.technifutur.menu.MenuController;

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
}
