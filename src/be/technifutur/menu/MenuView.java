package be.technifutur.menu;

import java.util.Scanner;

public class MenuView {
    private final Scanner input = new Scanner(System.in);
    private String error = null;

    public void setError(String errorMessage) {
        this.error = errorMessage;
    }

    public String saisirMenu(MenuModel menu) {
        String choice;
        String menuName = menu.getName();

        // print the menu
        System.out.println();
        System.out.printf("%s%n", menuName);
        System.out.printf("%s%n", "-".repeat(menuName.length()));
        System.out.println("Que voulez-vous faire ?");
        for(int i = 0; i < menu.size(); i++) {
            System.out.printf("(%2d) %s%n", i, menu.getNode(i).getName());
        }

        if (this.error != null) {
            System.out.printf("*** Erreur : %s ***%n", this.error);
        }

        // user input
        System.out.print("Choix : ");
        choice = input.nextLine();
        System.out.println();

        return choice;
    }
}
