package be.technifutur.menu;

import java.util.concurrent.Callable;

public class MenuFactory {
    public MenuController getMenu() {
        return createMenu(getModelPrincipal());
    }

    private MenuNode getItemExit() {
        return createItem("Quitter le programme", null);
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
        return model;
    }
}
