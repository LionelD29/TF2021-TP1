package be.technifutur.tp1.registration;

import java.util.concurrent.Callable;

public class RegisterToActivityController implements Callable<Object> {
    private Registrations registrations;
    private RegistrationView registrationView;

    public void setRegistrations(Registrations registrations) {
        this.registrations = registrations;
    }

    public void setRegistrationView(RegistrationView registrationView) {
        this.registrationView = registrationView;
    }

    @Override
    public Object call() throws Exception {
        System.out.println("Inscription a une activite");
        return null;
    }
}
