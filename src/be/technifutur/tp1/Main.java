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
            action.call();
            action = controller.getAction();
        }
    }
}
