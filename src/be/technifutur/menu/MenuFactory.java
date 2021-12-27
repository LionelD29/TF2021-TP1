package be.technifutur.menu;

import be.technifutur.tp1.activityType.*;
import be.technifutur.tp1.datastore.DataStore;
import be.technifutur.tp1.registration.RegistrationManagementController;
import be.technifutur.tp1.schedule.*;

import java.util.concurrent.Callable;

public class MenuFactory {
    private final DataStore<ListActivityType> myDataStore = new DataStore<>(
            "ressources/datastore.txt",
            ListActivityType::new
    );
    private final ListActivityType modelActivityType = myDataStore.getData();

    private final Schedule modelSchedule = new Schedule();

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

    // Menus
    public MenuController getMenuPrincipal() {
        return createMenu(getModelPrincipal());
    }

    public MenuController getMenuActivityManagement() {
        return createMenu(getModelActivityManagement());
    }

    public MenuController getMenuScheduleManagement() {
        return createMenu(getModelScheduleManagement());
    }

    public MenuController getMenuRegistrationManagement() {
        return createMenu(getModelRegistrationManagement());
    }


    // Items du menu principal
    private MenuNode getItemExit() {
        return createItem("Quitter le programme", null);
    }

    private MenuNode getItemActivityManagement() {
        return createItem("Gestion des activites", new ActivityTypeManagementController(getMenuActivityManagement(), modelActivityType));
    }

    private MenuNode getItemScheduleManagement() {
        return createItem("Etablir l'horaire du stage (En cours)", new ScheduleManagementController(getMenuScheduleManagement()));
    }

    private MenuNode getItemRegistrationManagement() {
        return createItem("Gestion des inscriptions (bientot disponible)", new RegistrationManagementController(getMenuRegistrationManagement()));
    }


    // Retour pour les sous-menus
    private MenuNode getItemReturn() {
        return createItem("Retour", null);
    }

    // Items pour le menu Gestion des activités
    private MenuNode getItemCreateActivityType() {
        return createItem("Creer un type d'activite", getCreateActivityTypeController());
    }

    private MenuNode getItemDeleteActivityType() {
        return createItem("Supprimer un type d'activite", getDeleteActivityTypeController());
    }

    private MenuNode getItemModifyActivityType() {
        return createItem("Modifier un type d'activite", getModifyActivityTypeController());
    }

    // Items pour le menu Etablir l'horaire du stage
    private MenuNode getItemAddActivity() {
        return createItem("Ajouter une activite a l'horaire (en cours)", getAddActivityController());
    }

    private MenuNode getItemModifyActivitySchedule() {
        return createItem("Modifier l'horaire d'une activite (en cours)", getModifyActivityScheduleController());
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
        controller.setModelActivityType(modelActivityType);
        controller.setModelSchedule(modelSchedule);
        controller.setScheduleView(new ScheduleView());
        return controller;
    }

    // Définition des models
    private MenuModel getModelPrincipal() {
        MenuModel model = new MenuModel("Menu principal");
        model.addNode(getItemExit());
        model.addNode(getItemActivityManagement());
        model.addNode(getItemScheduleManagement());
        model.addNode(getItemRegistrationManagement());
        return model;
    }

    private MenuModel getModelActivityManagement() {
        MenuModel model = new MenuModel("Gestion des activites");
        model.addNode(getItemReturn());
        model.addNode(getItemCreateActivityType());
        model.addNode(getItemDeleteActivityType());
        model.addNode(getItemModifyActivityType());
        return model;
    }

    private MenuModel getModelScheduleManagement() {
        MenuModel model = new MenuModel("Etablir l'horaire du stage (En cours)");
        model.addNode(getItemReturn());
        model.addNode(getItemAddActivity());
        model.addNode(getItemModifyActivitySchedule());
        return model;
    }

    private MenuModel getModelRegistrationManagement() {
        MenuModel model = new MenuModel("Gestion des inscriptions (Bientot disponible)");
        model.addNode(getItemReturn());
        return model;
    }
}
