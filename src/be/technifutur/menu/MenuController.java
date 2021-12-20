package be.technifutur.menu;

import java.util.concurrent.Callable;

public class MenuController implements MenuNode {
    private MenuModel model;
    private MenuView view;

    public void setModel(MenuModel model) {
        this.model = model;
    }

    public void setView(MenuView view) {
        this.view = view;
    }

    @Override
    public String getName() {
        return this.model.getName();
    }

    @Override
    public Callable<? extends Object> getAction() {
        Callable<? extends Object> result = null;
        String input = "";
        MenuNode menuNode;
        int choix;
        boolean saisieOK = false;

        // Saisie d'un item par l'utilisateur
        this.view.setError(null);
        while (!saisieOK) {
            try {
                input = this.view.saisirMenu(this.model);
                choix = Integer.parseInt(input);
                if (choix >= 0 && choix < this.model.size()) {
                    saisieOK = true;
                    menuNode = this.model.getNode(choix);
                    result = menuNode.getAction();
                } else {
                    this.view.setError(input + " n'est pas un nombre valide");
                }
            } catch (NumberFormatException e) {
                this.view.setError(input + " n'est pas numérique");
            }
        }
        this.view.setError(null);
        return result;
    }
}
