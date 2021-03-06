package be.technifutur.tp1.activityType;

import java.util.Map;
import java.util.Scanner;

public class ActivityView {

    private final Scanner scan = new Scanner(System.in);
    private String userInput = "";

    public void printActivity(ActivityType activity) {
        System.out.println(activity);
        System.out.println();
    }

    public void printActivityList(ListActivityType model) {
        System.out.println("Liste des types d'activites");
        System.out.println("-".repeat("Liste des types d'activites".length()));
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
        System.out.println();
        return userInput;
    }

    public String newActivityName() {
        System.out.println("Nouveau nom du type d'activite :");
        System.out.println("Tapez la touche Enter pour ne faire aucune modification");
        userInput = scan.nextLine();
        System.out.println();
        return userInput;
    }

    public String inputActivityRegistration() {
        System.out.print("Ce type d'activite necessite-t-il une inscription ? (o/n) : ");
        userInput = scan.nextLine();
        System.out.println();
        return userInput;
    }

    public String newActivityRegistration() {
        System.out.println("Inscription : ");
        System.out.println("o, n ou tapez la touche Enter pour ne pas modifier");
        userInput = scan.nextLine();
        System.out.println();
        return userInput;
    }

    public String deleteActivityValidation(ActivityType activity) {
        System.out.printf("Le type d'activite %s va etre supprimee.%n", activity);
        System.out.print("Etes-vous sur de vouloir continuer ? (o/n) : ");
        userInput = scan.nextLine();
        System.out.println();
        return userInput;
    }

    // Messages
    public void printMessage(String message) {
        System.out.printf("*** %s ***%n%n", message);
    }

    public void invalidChoice(String choice) {
        System.out.printf("*** %s n'est pas un choix valide ***%n%n", choice);
    }

    public void noSuchActivity(String activity) {
        System.out.printf("*** Le type d'activite %s n'existe pas dans la liste ***%n%n", activity);
    }

    public void usedActivity(String activity) {
        System.out.println("*** Suppression impossible ***");
        System.out.printf("*** Le type d'activite %s est actuellement utilisee dans l'horaire ***%n%n", activity);
    }
}
