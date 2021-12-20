package be.technifutur.menu;

import be.technifutur.tp1.activity.ActivityView;
import be.technifutur.tp1.activity.CreateActivityController;
import be.technifutur.tp1.activity.ListActivityType;

import java.util.concurrent.Callable;

public class MenuFactory {
    private ListActivityType modelActivityType = new ListActivityType();

    public MenuController getMenu() {
        return createMenu(getModelPrincipal());
    }

    private MenuNode getItemExit() {
        return createItem("Quitter le programme", null);
    }

    private MenuNode getItemReturn() {
        return createItem("Retour", null);
    }

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

    // Définition des menus et sous-menus
    private MenuModel getModelPrincipal() {
        MenuModel model = new MenuModel("Menu principal");
        model.addNode(getItemExit());
        model.addNode(getActivityManagement());
        model.addNode(getScheduleManagement());
        model.addNode(getRegistrationManagement());
        return model;
    }

    private MenuNode getActivityManagement() {
        MenuModel model = new MenuModel("Gestion des activites");
        model.addNode(getItemReturn());
        model.addNode(getItemCreateActivity());
        model.addNode(getItemOthers());
        return createMenu(model);
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
