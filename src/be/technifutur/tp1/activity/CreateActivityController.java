package be.technifutur.tp1.activity;

public class CreateActivityController {
    private CreateActivityView createActivityView;
    private ListActivityType model;

    public void setCreateActivityView(CreateActivityView createActivityView) {
        this.createActivityView = createActivityView;
    }

    public void setModel(ListActivityType model) {
        this.model = model;
    }

    public ActivityType createActivity() {
        ActivityType newActivity = null;
        String name = "";
        String registrationString = "";
        boolean registration = false;

        name = createActivityView.inputActivityName();
        // Si une activité dans le modèle possède déjà ce nom, on affiche un message d'erreur, ainsi
        // que l'activité en question.
        if (model.get(name) == null) {
            registrationString = createActivityView.inputActivityRegistration();
            if (registrationString.equals("o") || registrationString.equals("n")) {
                if(registrationString.equals("o")) registration = true;

                newActivity = model.addActivityType(name, registration);
                createActivityView.showNewActivity(newActivity);
            } else {
                createActivityView.invalidRegistrationInput(registrationString);
            }

        } else {
            createActivityView.alreadyExistingActivity(name, model);
        }
        return newActivity;
    }
}
