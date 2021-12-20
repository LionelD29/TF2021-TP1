package be.technifutur.menu;

import java.util.concurrent.Callable;

public class Item implements MenuNode {
    private String name;
    private Callable<? extends Object> action;

    public String getName() {
        return this.name;
    }

    public Callable<? extends Object> getAction() {
        return this.action;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAction(Callable<? extends Object> action) {
        this.action = action;
    }

}
