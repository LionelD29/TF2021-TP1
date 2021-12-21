package be.technifutur.menu;

import be.technifutur.tp1.activity.ActivityManagement;
import be.technifutur.tp1.activity.ActivityView;
import be.technifutur.tp1.activity.CreateActivityController;
import be.technifutur.tp1.activity.ListActivityType;
import be.technifutur.tp1.registration.RegistrationManagement;
import be.technifutur.tp1.schedule.ScheduleManagement;

import java.util.concurrent.Callable;

public class MenuFactory {
    private ListActivityType modelActivityType = new ListActivityType();

    // Methodes essentielles à la création d'items et de menus
    private MenuNode createItem(String name, Callable<? extends Object> action) {
        Item item = new Item();
        item.setName(name);
        item.setAction(action);
        return item;
    }

    private MenuController createMenu(MenuModel model) {
        MenuController menu = new MenuController();
        menu.setModel(model);
        menu.setView(new MenuView());
        return menu;
    }

    // Menus
    public MenuController getMenuPrincipal() {
        return createMenu(getModelPrincipal());
    }

    public MenuController getMenuActivityManagement() {
        return createMenu(getModelActivityManagement());
    }

    public MenuController getMenuScheduleManagement() {
        return createMenu(getModelScheduleManagement());
    }

    public MenuController getMenuRegistrationManagement() {
        return createMenu(getModelRegistrationManagement());
    }


    // Items du menu principal
    private MenuNode getItemExit() {
        return createItem("Quitter le programme", null);
    }

    private MenuNode getItemActivityManagement() {
        return createItem("Gestion des activites", new ActivityManagement(getMenuActivityManagement()));
    }

    private MenuNode getItemScheduleManagement() {
        return createItem("Gestion des horaires (bientot disponible)", new ScheduleManagement(getMenuScheduleManagement()));
    }

    private MenuNode getItemRegistrationManagement() {
        return createItem("Gestion des inscriptions (bientot disponible)", new RegistrationManagement(getMenuRegistrationManagement()));
    }


    // Retour pour les sous-menus
    private MenuNode getItemReturn() {
        return createItem("Retour", null);
    }

    // Items pour le menu Gestion des activités
    private MenuNode getItemCreateActivity() {
        return createItem("Creer un nouveau type d'activite", getCreateActivityController());
    }

    private MenuNode getItemOthers() {
        return createItem("Autres options (bientot disponible)", null);
    }


    // controlleur pour la création d'activités
    private CreateActivityController getCreateActivityController() {
        CreateActivityController createActivityController = new CreateActivityController();
        createActivityController.setModel(modelActivityType);
        createActivityController.setActivityView(new ActivityView());
        return createActivityController;
    }

    // Définition des models
    private MenuModel getModelPrincipal() {
        MenuModel model = new MenuModel("Menu principal");
        model.addNode(getItemExit());
        model.addNode(getItemActivityManagement());
        model.addNode(getItemScheduleManagement());
        model.addNode(getItemRegistrationManagement());
        return model;
    }

    private MenuModel getModelActivityManagement() {
        MenuModel model = new MenuModel("Gestion des activites");
        model.addNode(getItemReturn());
        model.addNode(getItemCreateActivity());
        model.addNode(getItemOthers());
        return model;
    }

    private MenuModel getModelScheduleManagement() {
        MenuModel model = new MenuModel("Gestion des horaires (Bientot disponible)");
        model.addNode(getItemReturn());
        return model;
    }

    private MenuModel getModelRegistrationManagement() {
        MenuModel model = new MenuModel("Gestion des inscriptions (Bientot disponible)");
        model.addNode(getItemReturn());
        return model;
    }
}
