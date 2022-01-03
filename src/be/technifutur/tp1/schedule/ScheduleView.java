package be.technifutur.tp1.schedule;

import be.technifutur.tp1.activity.Activity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class ScheduleView {

    private String userInput = "";
    private Scanner scan = new Scanner(System.in);
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public void printSchedule(Schedule schedule) {
        System.out.println("Horaire");
        System.out.println("-".repeat("Horaire".length()));
        if (schedule.listActivity.size() != 0) {
            for (Activity a : schedule.listActivity) {
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
        System.out.print("Nom de l'activite a modifier : ");
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

    public String confirmDelete(String activityName) {
        System.out.printf("L'activite %s va etre supprimee.%n", activityName);
        System.out.print("Etes-vous sur de vouloir continuer ? (o/n) : ");
        userInput = scan.nextLine();
        return userInput;
    }

    public LocalDateTime chooseActivityTime(String endOrStart) {
        boolean isValid = false;
        LocalDateTime parsedDate = null;

        if (endOrStart.equalsIgnoreCase("debut") || endOrStart.equalsIgnoreCase("fin")) {
            while (!isValid) {
                System.out.printf("Date de %s : %n", endOrStart);
                System.out.println("(Format : dd/MM/yyyy hh:mm)");
                userInput = scan.nextLine();
                try {
                    parsedDate = LocalDateTime.parse(userInput, formatter);
                    isValid = true;
                } catch(DateTimeParseException e) {
                    System.out.println("Mauvais format de date !");
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

    public void noSuchActivityType(String activityType) {
        System.out.printf("*** Le type d'activite %s n'existe pas ***%n", activityType);
        System.out.println();
    }

    public void noSuchActivity(String activity) {
        System.out.printf("*** L'activite %s n'existe pas ***%n", activity);
        System.out.println();
    }

    public void invalidChoice(String choice) {
        System.out.printf("*** %s n'est pas un choix valide ***%n", choice);
    }
}
