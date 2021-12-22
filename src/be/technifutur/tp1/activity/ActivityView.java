package be.technifutur.tp1.activity;

import java.util.Map;
import java.util.Scanner;

public class ActivityView {

    private final Scanner scan = new Scanner(System.in);

    public void printActivityList(ListActivityType model) {
        System.out.println();
        System.out.println();
        System.out.println("Liste des activites :");
        if (model.getActivityList().isEmpty()) {
            System.out.println("*** vide ***");
        } else {
            for (Map.Entry<String, ActivityType> e : model.getActivityList().entrySet()) {
                System.out.printf("\t* %s%n", e.getValue());
            }
        }
        System.out.println();
    }

    // Demande les entrees de l'utilisateur
    public String inputActivityName() {
        String userInput = "";

        System.out.print("Nom du type d'activite : ");
        userInput = scan.nextLine();

        return userInput;
    }

    public String inputActivityRegistration() {
        String userInput = "";

        System.out.print("Cette activite necessite-t-elle une inscription ? (o/n) : ");
        userInput = scan.nextLine();

        return userInput;
    }

    public String newActivityRegistration() {
        String userInput = "";

        System.out.println("Inscription : ");
        System.out.println("o, n ou tapez la touche Enter pour ne pas modifier");
        userInput = scan.nextLine();

        return userInput;
    }

    public String deleteActivityValidation(ActivityType activity) {
        String userInput = "";
        System.out.printf("L'activite %s va etre supprimee.%n", activity);
        System.out.print("Etes-vous sur de vouloir continuer ? (o/n) : ");
        userInput = scan.nextLine();

        return userInput;
    }

    public String newActivityName() {
        String name = "";

        System.out.println("Nouveau nom de l'activite :");
        System.out.println("Tapez la touche Enter pour ne faire aucune modification");
        name = scan.nextLine();

        return name;
    }
    // Messages
    public void invalidYesNoInput(String input) {
        System.out.printf("*** %s n'est pas un choix valide ***%n", input);
    }

    public void alreadyExistingActivity(ActivityType activity) {
        System.out.printf("*** L'activite %s existe deja dans la liste ***%n", activity.getName());
        printActivity(activity);
    }

    public void noSuchActivity(String name) {
        System.out.printf("*** L'activite %s n'existe pas dans la liste ***%n", name);
    }

    public void showNewActivity(ActivityType activity) {
        System.out.println("*** Nouvelle activite ajoutee ***");
        printActivity(activity);
    }

    public void confirmDelete() {
        System.out.println("*** Activite supprimee ***");
    }

    public void cancelActivityDelete() {
        System.out.println("*** Suppression annulee ***");
    }

    public void printActivity(ActivityType activity) {
        System.out.println(activity);
    }
}
