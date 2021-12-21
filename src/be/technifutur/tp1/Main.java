package be.technifutur.tp1;

import be.technifutur.menu.MenuController;
import be.technifutur.menu.MenuFactory;
import be.technifutur.util.Util;

public class Main {
    public static void main(String[] args) {
        System.out.println("+--------------------------------------------------------------+");
        System.out.println("| Programme de gestion d'inscriptions a des activites de stage |");
        System.out.println("+--------------------------------------------------------------+");

        // Créé et fait tourner le menu principal, puis sauvegarde les données à la fin.
        MenuFactory factory = new MenuFactory();
        MenuController controller = factory.getMenuPrincipal();
        Util.callAction(controller);

        factory.saveDataStore();
        System.out.println("*** Sauvegarde des donnees terminees ***");
        System.out.println("*** Fin du programme ***");
    }
}
