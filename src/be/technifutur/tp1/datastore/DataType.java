package be.technifutur.tp1.datastore;

import be.technifutur.tp1.activityType.ListActivityType;
import be.technifutur.tp1.schedule.Schedule;

import java.io.Serializable;

public class DataType implements Serializable {
    private final ListActivityType activityTypeModel = new ListActivityType();
    private final Schedule scheduleModel = new Schedule();

    public ListActivityType getActivityTypeModel() {
        return this.activityTypeModel;
    }

    public Schedule getScheduleModel() {
        return this.scheduleModel;
    }
}
