package be.technifutur.tp1.schedule;

import be.technifutur.tp1.activity.Activity;

import java.io.Serializable;
import java.util.Comparator;

public class StartEndTypeComparator implements Comparator<Activity>, Serializable {
    @Override
    public int compare(Activity a1, Activity a2) {
        int i = 0;
        // Si le type d'activité des deux activités est différent, elles peuvent se recouvrir
        // mais pas si elles ont le même type d'activité. Si il y a un conflit d'horaire, on renvoit donc
        // i = 0 afin de ne pas ajouter la nouvelle activité à l'horaire. Comme l'horaire est un Set
        // i = 0 fera que la nouvelle activité sera considérée comme un doublon, et donc pas ajoutée.
        if (!a1.getType().getName().equals(a2.getType().getName())) {
            i = a1.getStart().compareTo(a2.getStart());
            if (i == 0) {
                i = a1.getEnd().compareTo(a2.getEnd());
                if (i == 0) {
                    i = a1.getType().getName().compareTo(a2.getType().getName());
                }
            }
        } else {
            if (a1.getStart().isBefore(a2.getStart()) && !a2.getStart().isBefore(a1.getEnd())) {
                i = -1;
            } else if (a1.getStart().isAfter(a2.getStart()) && !a2.getEnd().isAfter(a1.getStart())) {
                i = 1;
            }
            // On peut en effet avoir deux activités du même type dont l'une se termine
            // et l'autre commence au même moment. Mais on n'accepte pas le recouvrement d'horaire
            // si elles ont le même type d'activité.
        }

        return i;
    }
}
