package be.technifutur.tp1.activity;

public class ActivityFactory {
    public CreateActivityController getCreateActivityController(ListActivityType model) {
        CreateActivityController controller = new CreateActivityController();
        controller.setModel(model);
        controller.setCreateActivityView(new CreateActivityView());
        return controller;
    }
}
