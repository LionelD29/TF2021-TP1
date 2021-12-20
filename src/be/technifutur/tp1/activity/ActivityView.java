package be.technifutur.tp1.activity;

import java.util.Scanner;

public class ActivityView {

    private final Scanner scan = new Scanner(System.in);

    public String inputActivityName() {
        String userInput = "";

        System.out.println("Creation d'une nouvelle activite");
        System.out.println();

        System.out.print("Nom du type d'activite : ");
        userInput = scan.nextLine();
        System.out.println();

        return userInput;
    }

    public String inputActivityRegistration() {
        String userInput = "";

        System.out.print("Cette activite necessite-t-elle une inscription ? (o/n) : ");
        userInput = scan.nextLine();

        return userInput;
    }

    public void invalidRegistrationInput(String input) {
        System.out.printf("*** %s n'est pas un choix valide d'inscription ***%n", input);
    }

    public void alreadyExistingActivity(String name, ListActivityType model) {
        System.out.printf("*** L'activite %s existe deja dans la liste ***%n", name);
        System.out.println(model.get(name));
    }

    public void showNewActivity(ActivityType activity) {
        System.out.println("*** Nouvelle activite ajoutee ***");
        System.out.println(activity);
    }
}
