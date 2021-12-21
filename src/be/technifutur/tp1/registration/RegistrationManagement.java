package be.technifutur.tp1.registration;

import be.technifutur.menu.MenuController;
import be.technifutur.util.Util;

import java.util.concurrent.Callable;

public class RegistrationManagement implements Callable<Object> {
    private MenuController controller;

    public RegistrationManagement(MenuController controller) {
        this.controller = controller;
    }

    @Override
    public Object call() throws Exception {
        // Fait tourner le menu de gestion des inscriptions
        Util.callAction(controller);
        System.out.println("Retour au menu principal");
        return null;
    }
}
