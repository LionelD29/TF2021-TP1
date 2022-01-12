package be.technifutur.tp1.activityType;

import be.technifutur.tp1.activity.Activity;
import be.technifutur.tp1.schedule.Schedule;

import java.util.concurrent.Callable;

public class ModifyActivityTypeController implements Callable<ActivityType> {
    private ListActivityType model;
    private ActivityView activityView;
    private Schedule schedule;

    public void setModel(ListActivityType model) {
        this.model = model;
    }

    public void setActivityView(ActivityView view) {
        activityView = view;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    @Override
    public ActivityType call() throws Exception {
        /*
            Cette méthode permet de modifier un type d'activite existant
         */
        String oldTypeName;
        String newTypeName;
        String newRegistration;
        ActivityType activityType;

        activityView.printMessage("Modification d'un type d'activite");

        oldTypeName = activityView.inputActivityName();
        /*
           On récupère le type d'activite en supprimant l'entrée correspondante dans la map.
           On modifie si nécessaire cette activité ensuite
           Puis on la réinsère dans la map
        */
        activityType = model.remove(oldTypeName);
        if (activityType != null) {
            activityView.printActivity(activityType);

            // Nouveau nom
            // Si "", on ne modifie pas le nom précédent
            newTypeName = activityView.newActivityName();
            if(!newTypeName.equals("")) {
                activityType.setName(newTypeName);
            }

            activityView.printActivity(activityType);

            // Nouvelle inscription
            newRegistration = activityView.newActivityRegistration();
            // Tant que pas o, n ou "", on recommence la saisie
            while (!newRegistration.matches("|[onON]")) {
                activityView.invalidChoice(newRegistration);
                newRegistration = activityView.newActivityRegistration();
            }
            if (newRegistration.equalsIgnoreCase("o")) {
                activityType.setRegistrationRequired(true);
            } else if (newRegistration.equalsIgnoreCase("n")){
                activityType.setRegistrationRequired(false);
            }

            activityView.printActivity(activityType);

            ActivityType newActivityType = model.addActivityType(activityType.getName(), activityType.isRegistrationRequired());

            for (Activity a : schedule.getListActivity()) {
                if (a.getType().getName().equals(oldTypeName)) {
                    a.setType(newActivityType);
                }
            }
        } else {
            activityView.noSuchActivity(oldTypeName);
        }
        return null;
    }
}
