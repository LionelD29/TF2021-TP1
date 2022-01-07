package be.technifutur.tp1.registration;

import be.technifutur.menu.MenuController;

import java.util.concurrent.Callable;

public class RegistrationManagementController implements Callable<Object> {
    private final MenuController controller;
    /*private final Registrations registrations;
    private final RegistrationView registrationView = new RegistrationView();*/

    public RegistrationManagementController(MenuController controller, Registrations registrations) {
        this.controller = controller;
       // this.registrations = registrations;
    }

    @Override
    public Object call() throws Exception {
        Callable<?> action = controller.getAction();

        while (action != null) {
            try {
                action.call();
            } catch (Exception e) {
                System.out.println("Une erreur est survenue");
                e.printStackTrace();
            }
            action = controller.getAction();
        }

        System.out.println("Retour au menu principal");
        return null;
    }
}
