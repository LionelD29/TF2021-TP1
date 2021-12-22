package be.technifutur.tp1.registration;

import be.technifutur.menu.MenuController;
import be.technifutur.util.Util;

import java.util.concurrent.Callable;

public class RegistrationManagementController implements Callable<Object> {
    private MenuController controller;

    public RegistrationManagementController(MenuController controller) {
        this.controller = controller;
    }

    @Override
    public Object call() throws Exception {
        // Fait tourner le menu de gestion des inscriptions
        System.out.println("Retour au menu principal");
        return null;
    }
}