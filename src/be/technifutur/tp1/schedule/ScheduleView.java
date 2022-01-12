package be.technifutur.tp1.schedule;

import be.technifutur.tp1.activity.Activity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class ScheduleView {

    private static String userInput = "";
    private static final Scanner scan = new Scanner(System.in);
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public void printSchedule(Schedule schedule) {
        System.out.println("Horaire");
        System.out.println("-".repeat("Horaire".length()));
        if (schedule.getListActivity().size() != 0) {
            for (Activity a : schedule.getListActivity()) {
                System.out.printf("* %s%n%n", a);
            }
        } else {
            System.out.println("*** Vide ***");
        }
        System.out.println();
    }

    public String selectActivityType() {
        System.out.print("Type de l'activite a ajouter : ");
        userInput = scan.nextLine();
        System.out.println();
        return userInput;
    }

    public String selectActivity() {
        System.out.print("Nom de l'activite : ");
        userInput = scan.nextLine();
        System.out.println();
        return userInput;
    }

    public String chooseActivityName() {
        System.out.print("Nom de l'activite a ajouter : ");
        userInput = scan.nextLine();
        System.out.println();
        return userInput;
    }

    public String confirmDelete(Activity activity) {
        System.out.println("L'activite ci-dessous va etre supprimee :");
        System.out.println("* " + activity);
        System.out.print("Etes-vous sur de vouloir continuer ? (o/n) : ");
        userInput = scan.nextLine();
        System.out.println();
        return userInput;
    }

    public LocalDateTime chooseActivityTime(String endOrStart) {
        boolean isValid = false;
        LocalDateTime parsedDate = null;

        if (endOrStart.equalsIgnoreCase("debut") || endOrStart.equalsIgnoreCase("fin")) {
            while (!isValid) {
                System.out.printf("Date de %s : %n", endOrStart);
                System.out.println("(Format : jj/MM/aaaa HH:mm)");
                userInput = scan.nextLine();
                try {
                    parsedDate = LocalDateTime.parse(userInput, formatter);
                    isValid = true;
                } catch(DateTimeParseException e) {
                    System.out.println("Mauvais format de date !");
                    System.out.println();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else {
            System.out.println("Mauvais parametre saisi pour la methode chooseActivityTime");
        }
        System.out.println();
        return parsedDate;
    }

    // Messages
    public void printMessage(String message) {
        System.out.printf("*** %s ***%n", message);
        System.out.println();
    }

    public void noSuchActivity(String activity) {
        System.out.printf("*** L'activite %s n'existe pas ***%n", activity);
        System.out.println();
    }

    public void invalidChoice(String choice) {
        System.out.printf("*** %s n'est pas un choix valide ***%n", choice);
        System.out.println();
    }

    public String confirmCreation() {
        System.out.println("Ce type d'activite n'existe pas encore dans la liste.");
        System.out.print("Voulez-vous le creer ? (o/n) ");
        userInput = scan.nextLine();
        System.out.println();
        return userInput;
    }

    public String selectActivityIndex(List<Activity> activities) {
        System.out.println("Plusieurs activites portent le meme nom.");
        System.out.println("Choisissez le chiffre correspondant a celle que vous voulez : ");
        for (int i = 0; i < activities.size(); i++) {
            System.out.printf("(%2d)%n", i);
            System.out.printf("* %s%n%n", activities.get(i));
        }
        System.out.print("Votre choix : ");
        userInput = scan.nextLine();
        System.out.println();
        return userInput;
    }
}
