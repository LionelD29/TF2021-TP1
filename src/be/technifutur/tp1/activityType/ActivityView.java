package be.technifutur.tp1.activityType;

import java.util.Map;
import java.util.Scanner;

public class ActivityView {

    private final Scanner scan = new Scanner(System.in);
    private String userInput = "";

    public void printActivity(ActivityType activity) {
        System.out.println(activity);
    }

    public void printActivityList(ListActivityType model) {
        System.out.println("\n\nListe des activites :");
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
        System.out.print("Nom du type d'activite : ");
        userInput = scan.nextLine();
        return userInput;
    }

    public String newActivityName() {
        System.out.println("Nouveau nom de l'activite :");
        System.out.println("Tapez la touche Enter pour ne faire aucune modification");
        userInput = scan.nextLine();
        return userInput;
    }

    public String inputActivityRegistration() {
        System.out.print("Cette activite necessite-t-elle une inscription ? (o/n) : ");
        userInput = scan.nextLine();
        return userInput;
    }

    public String newActivityRegistration() {
        System.out.println("Inscription : ");
        System.out.println("o, n ou tapez la touche Enter pour ne pas modifier");
        userInput = scan.nextLine();
        return userInput;
    }

    public String deleteActivityValidation(ActivityType activity) {
        System.out.printf("L'activite %s va etre supprimee.%n", activity);
        System.out.print("Etes-vous sur de vouloir continuer ? (o/n) : ");
        userInput = scan.nextLine();
        return userInput;
    }

    // Messages
    public void printMessage(String message) {
        System.out.printf("*** %s ***%n", message);
    }

    public void invalidChoice(String choice) {
        System.out.printf("*** %s n'est pas un choix valide ***%n", choice);
    }

    public void noSuchActivity(String activity) {
        System.out.printf("*** L'activite %s n'existe pas dans la liste ***%n", activity);
    }
}
