package be.technifutur.menu;

import java.util.concurrent.Callable;

public class MenuFactory {
    public MenuController getMenu() {
        return createMenu(getModelPrincipal());
    }

    private MenuNode getItemExit() {
        return createItem("Quitter le programme", null);
    }

    private MenuNode getItemReturn() {
        return createItem("Retour", null);
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
