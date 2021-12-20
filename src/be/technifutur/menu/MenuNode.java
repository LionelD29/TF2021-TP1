package be.technifutur.menu;

import java.util.concurrent.Callable;

public interface MenuNode {
    String getName();
    Callable<? extends Object> getAction();
}
