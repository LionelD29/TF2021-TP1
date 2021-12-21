package be.technifutur.tp1;

import be.technifutur.menu.MenuController;
import be.technifutur.menu.MenuFactory;
import be.technifutur.util.Util;

public class Main {
    public static void main(String[] args) {
        MenuFactory factory = new MenuFactory();
        MenuController controller = factory.getMenuPrincipal();
        Util.callAction(controller);
        factory.saveDataStore();
        System.out.println("Sauvegarde des donnees terminees. Fin du programme");
    }
}
