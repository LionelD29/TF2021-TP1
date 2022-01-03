package be.technifutur.tp1;

import be.technifutur.menu.MenuController;
import be.technifutur.menu.MenuFactory;
import be.technifutur.tp1.datastore.DataStore;
import be.technifutur.tp1.datastore.DataType;

import java.util.concurrent.Callable;

public class Main {
    public static void main(String[] args) {
        System.out.println("+--------------------------------------------------------------+");
        System.out.println("| Programme de gestion d'inscriptions a des activites de stage |");
        System.out.println("+--------------------------------------------------------------+");

        // Créé et fait tourner le menu principal, puis sauvegarde les données à la fin.
        MenuFactory factory = new MenuFactory();
        MenuController controller = factory.getMenuPrincipal();
        DataStore<DataType> myDataStore = factory.getDataStore();
        factory = null;

        Callable<? extends Object> action = controller.getAction();

        while (action != null) {
            try {
                action.call();
            } catch (Exception e) {
                System.out.println("Une erreur est survenue");
            }
            action = controller.getAction();
        }

        myDataStore.save();
        System.out.println("*** Sauvegarde des donnees terminees ***");
        System.out.println("*** Fin du programme ***");
    }
}
