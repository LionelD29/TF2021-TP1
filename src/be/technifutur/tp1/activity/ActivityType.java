package be.technifutur.tp1.activity;

import java.io.Serializable;

public class ActivityType implements Serializable {
    private String name;
    private boolean registration;

    public ActivityType(String name, boolean inscription) {
        this.name = name;
        registration = inscription;
    }

    public String getName() {
        return name;
    }

    public boolean isRegistrationRequired() {
        return registration;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRegistrationRequired(boolean flag) {
        registration = flag;
    }

    @Override
    public String toString() {
        return "ActivityType{" +
                "name='" + name + '\'' +
                ", registration=" + registration +
                '}';
    }
}