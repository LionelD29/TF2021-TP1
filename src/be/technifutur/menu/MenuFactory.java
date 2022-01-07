package be.technifutur.menu;

import be.technifutur.tp1.activityType.*;
import be.technifutur.tp1.datastore.DataStore;
import be.technifutur.tp1.datastore.DataType;
import be.technifutur.tp1.registration.*;
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
    private final Registrations registrations = new Registrations();

   public DataStore<DataType> getDataStore() {
       return this.myDataStore;
   }

    // Methodes essentielles à la création d'items et de menus
    private MenuNode createItem(String name, Callable<?> action) {
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
        model.addNode(createItem("Gestion des types d'activites", new ActivityTypeManagementController(getMenuActivityManagement(), modelActivityType)));
        model.addNode(createItem("Etablir l'horaire du stage", new ScheduleManagementController(getMenuScheduleManagement(), modelSchedule)));
        model.addNode(createItem("Gestion des inscriptions (en cours)", new RegistrationManagementController(getMenuRegistrationManagement(), registrations)));
        return createMenu(model);
    }

    public MenuController getMenuActivityManagement() {
        MenuModel model = new MenuModel("Gestion des types d'activites");
        model.addNode(getItemReturn());
        model.addNode(createItem("Creer un type d'activite", getCreateActivityTypeController()));
        model.addNode(createItem("Supprimer un type d'activite", getDeleteActivityTypeController()));
        model.addNode(createItem("Modifier un type d'activite", getModifyActivityTypeController()));
        return createMenu(model);
    }

    public MenuController getMenuScheduleManagement() {
        MenuModel model = new MenuModel("Etablir l'horaire du stage");
        model.addNode(getItemReturn());
        model.addNode(createItem("Ajouter une activite a l'horaire", getAddActivityController()));
        model.addNode(createItem("Modifier l'horaire d'une activite", getModifyActivityScheduleController()));
        model.addNode(createItem("Supprimer une activite de l'horaire", getDeleteActivityController()));
        model.addNode(createItem("Afficher la liste des types d'activites", getReadActivityTypeController()));
        return createMenu(model);
    }

    public MenuController getMenuRegistrationManagement() {
        MenuModel model = new MenuModel("Gestion des inscriptions (en cours)");
        model.addNode(getItemReturn());
        model.addNode(createItem("S'inscrire à une activite", getRegisteredToActivityController()));
        model.addNode(createItem("Se desinscrire d'une activite", getUnregisteredFromActivityController()));
        model.addNode(createItem("Afficher la liste des inscriptions a une activite", getReadActivityRegistrationsController()));
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
        controller.setSchedule(modelSchedule);
        controller.setActivityView(new ActivityView());
        return controller;
    }

    private ReadActivityTypeController getReadActivityTypeController() {
        ReadActivityTypeController controller = new ReadActivityTypeController();
        controller.setModel(modelActivityType);
        controller.setActivityView(new ActivityView());
        return controller;
    }

    // Controlleurs pour l'établissement de l'horaire du stage
    private AddActivityController getAddActivityController() {
        AddActivityController controller = new AddActivityController();
        controller.setModelActivityType(modelActivityType);
        controller.setModelSchedule(modelSchedule);
        controller.setCreateActivityTypeController(getCreateActivityTypeController());
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


    // Controlleurs pour la gestion des inscriptions
    private RegisterToActivityController getRegisteredToActivityController() {
        RegisterToActivityController controller = new RegisterToActivityController();
        controller.setRegistrations(registrations);
        controller.setRegistrationView(new RegistrationView());
        return controller;
    }

    private UnregisterToActivityController getUnregisteredFromActivityController() {
        UnregisterToActivityController controller = new UnregisterToActivityController();
        controller.setRegistrations(registrations);
        controller.setRegistrationView(new RegistrationView());
        return controller;
    }

    private ReadActivityRegistrationsController getReadActivityRegistrationsController() {
        ReadActivityRegistrationsController controller = new ReadActivityRegistrationsController();
        controller.setRegistrations(registrations);
        controller.setRegistrationView(new RegistrationView());
        return controller;
    }
}
