package be.technifutur.menu;

import be.technifutur.tp1.activityType.*;
import be.technifutur.tp1.datastore.DataStore;
import be.technifutur.tp1.datastore.DataType;
import be.technifutur.tp1.registration.RegistrationManagementController;
import be.technifutur.tp1.schedule.*;

import java.util.concurrent.Callable;

public class MenuFactory {
    private final DataStore<DataType> myDataStore = new DataStore<>(
            "ressources/datastore.txt",
            DataType::new
    );
    private final DataType dataType = myDataStore.getData();
    private final ListActivityType modelActivityType = dataType.getActivityTypeModel();
    private final Schedule modelSchedule = dataType.getScheduleModel();

    // Sauvegarde dans le fichier ressources/datastore.txt
    public void saveDataStore() {
        myDataStore.save();
    }

    // Methodes essentielles à la création d'items et de menus
    private MenuNode createItem(String name, Callable<? extends Object> action) {
        Item item = new Item();
        item.setName(name);
        item.setAction(action);
        return item;
    }

    private MenuController createMenu(MenuModel model) {
        MenuController menu = new MenuController();
        menu.setModel(model);
        menu.setView(new MenuView());
        return menu;
    }

    // Retour pour les sous-menus
    private MenuNode getItemReturn() {
        return createItem("Retour", null);
    }

    // Menus
    public MenuController getMenuPrincipal() {
        MenuModel model = new MenuModel("Menu principal");
        model.addNode(createItem("Quitter le programme", null));
        model.addNode(createItem("Gestion des activites", new ActivityTypeManagementController(getMenuActivityManagement(), modelActivityType)));
        model.addNode(createItem("Etablir l'horaire du stage (En cours)", new ScheduleManagementController(getMenuScheduleManagement(), modelSchedule)));
        model.addNode(createItem("Gestion des inscriptions (bientot disponible)", new RegistrationManagementController(getMenuRegistrationManagement())));
        return createMenu(model);
    }

    public MenuController getMenuActivityManagement() {
        MenuModel model = new MenuModel("Gestion des activites");
        model.addNode(getItemReturn());
        model.addNode(createItem("Creer un type d'activite", getCreateActivityTypeController()));
        model.addNode(createItem("Supprimer un type d'activite", getDeleteActivityTypeController()));
        model.addNode(createItem("Modifier un type d'activite", getModifyActivityTypeController()));
        return createMenu(model);
    }

    public MenuController getMenuScheduleManagement() {
        MenuModel model = new MenuModel("Etablir l'horaire du stage (En cours)");
        model.addNode(getItemReturn());
        model.addNode(createItem("Ajouter une activite a l'horaire (en cours)", getAddActivityController()));
        model.addNode(createItem("Modifier l'horaire d'une activite (en cours)", getModifyActivityScheduleController()));
        model.addNode(createItem("Supprimer une activite de l'horaire (en cours)", getDeleteActivityController()));
        return createMenu(model);
    }

    public MenuController getMenuRegistrationManagement() {
        MenuModel model = new MenuModel("Gestion des inscriptions (Bientot disponible)");
        model.addNode(getItemReturn());
        return createMenu(model);
    }



    // Controlleurs pour la gestion de types d'activités
    private CreateActivityTypeController getCreateActivityTypeController() {
        CreateActivityTypeController controller = new CreateActivityTypeController();
        controller.setModel(modelActivityType);
        controller.setActivityView(new ActivityView());
        return controller;
    }

    private DeleteActivityTypeController getDeleteActivityTypeController() {
        DeleteActivityTypeController controller = new DeleteActivityTypeController();
        controller.setModel(modelActivityType);
        controller.setSchedule(modelSchedule);
        controller.setActivityView(new ActivityView());
        return controller;
    }

    private ModifyActivityTypeController getModifyActivityTypeController() {
        ModifyActivityTypeController controller = new ModifyActivityTypeController();
        controller.setModel(modelActivityType);
        controller.setActivityView(new ActivityView());
        return controller;
    }

    // Controlleurs pour l'établissement de l'horaire du stage
    private AddActivityController getAddActivityController() {
        AddActivityController controller = new AddActivityController();
        controller.setModelActivityType(modelActivityType);
        controller.setModelSchedule(modelSchedule);
        controller.setScheduleView(new ScheduleView());
        return controller;
    }

    private ModifyActivityScheduleController getModifyActivityScheduleController() {
        ModifyActivityScheduleController controller = new ModifyActivityScheduleController();
        controller.setModelSchedule(modelSchedule);
        controller.setScheduleView(new ScheduleView());
        return controller;
    }

    private DeleteActivityController getDeleteActivityController() {
        DeleteActivityController controller = new DeleteActivityController();
        controller.setModelSchedule(modelSchedule);
        controller.setScheduleView(new ScheduleView());
        return controller;
    }
}
