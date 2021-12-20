package be.technifutur.menu;

import java.util.ArrayList;

public class MenuModel {
    private final ArrayList<MenuNode> menuNodeList = new ArrayList<>();
    private final String name;

    public MenuModel(String name) {
        this.name = name;
    }

    public void addNode(MenuNode menuNode) {
        if (menuNode != null) {
            menuNodeList.add(menuNode);
        }
    }

    public MenuNode getNode(int pos) {
        return menuNodeList.get(pos);
    }

    public String getName() {
        return this.name;
    }

    public int size() {
        return menuNodeList.size();
    }
}
