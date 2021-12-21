package be.technifutur.menu;

import be.technifutur.tp1.activity.ActivityManagement;
import be.technifutur.tp1.activity.ActivityView;
import be.technifutur.tp1.activity.CreateActivityController;
import be.technifutur.tp1.activity.ListActivityType;

import java.util.concurrent.Callable;

public class MenuFactory {
    private ListActivityType modelActivityType = new ListActivityType();

    public MenuController getMenuPrincipal() {
        return createMenu(getModelPrincipal());
    }

    public MenuController getMenuActivityManagement() {
        return createMenu(getModelActivityManagement());
    }

    // Items du menu principal
    private MenuNode getItemExit() {
        return createItem("Quitter le programme", null);
    }

    private MenuNode getItemActivityManagement() {
        return createItem("Gestion des activites", new ActivityManagement(getMenuActivityManagement()));
    }

    // Item de retour pour les sous-menus
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
        model.addNode(getScheduleManagement());
        model.addNode(getRegistrationManagement());
        return model;
    }

    private MenuModel getModelActivityManagement() {
        MenuModel model = new MenuModel("Gestion des activites");
        model.addNode(getItemReturn());
        model.addNode(getItemCreateActivity());
        model.addNode(getItemOthers());
        return model;
    }

    private MenuNode getScheduleManagement() {
        MenuModel model = new MenuModel("Gestion des horaires (Bientot disponible)");
        model.addNode(getItemReturn());
        return createMenu(model);
    }

    private MenuNode getRegistrationManagement() {
        MenuModel model = new MenuModel("Gestion des inscriptions (Bientot disponible)");
        model.addNode(getItemReturn());
        return createMenu(model);
    }
}
