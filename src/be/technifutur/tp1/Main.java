package be.technifutur.tp1;

import be.technifutur.menu.MenuController;
import be.technifutur.menu.MenuFactory;

import java.util.concurrent.Callable;

public class Main {
    public static void main(String[] args) throws Exception {
        MenuFactory factory = new MenuFactory();
        MenuController controller = factory.getMenu();
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
