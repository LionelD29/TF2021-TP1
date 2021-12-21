package be.technifutur.tp1;

import be.technifutur.menu.MenuController;
import be.technifutur.menu.MenuFactory;
import be.technifutur.util.Util;

public class Main {
    public static void main(String[] args) {
        // Créé et fait tourner le menu principal, puis sauvegarde les données à la fin.
        MenuFactory factory = new MenuFactory();
        MenuController controller = factory.getMenuPrincipal();
        Util.callAction(controller);

        factory.saveDataStore();
        System.out.println("Sauvegarde des donnees terminees. Fin du programme");
    }
}
