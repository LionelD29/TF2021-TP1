package be.technifutur.tp1.registration;

import java.util.concurrent.Callable;

public class UnregisterToActivityController implements Callable<Object> {
    private ListPeople modelPeople;
    private RegistrationView registrationView;

    public void setModelPeople(ListPeople modelPeople) {
        this.modelPeople = modelPeople;
    }

    public void setRegistrationView(RegistrationView registrationView) {
        this.registrationView = registrationView;
    }

    @Override
    public Object call() throws Exception {
        System.out.println("Desinscription d'une activite");
        return null;
    }
}
