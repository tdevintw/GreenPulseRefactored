package Domain;

import Domain.Enum.AllTypesOfConsumption;
import Domain.Enum.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

public abstract class Consumption {
    protected final int id;
    protected float carbonQuantity;
    protected ConsumptionType consumptionType;
    protected final LocalDate start_date;
    protected final LocalDate end_date;
    protected User user;
    protected final LocalDateTime created_date;

    public Consumption(float carbonQuantity, LocalDate start_date, LocalDate end_date, User user) {
        this.id = (int) (Math.random() * 1000000000);
        this.carbonQuantity = carbonQuantity;
        this.start_date = start_date;
        this.end_date = end_date;
        this.user = user;
        this.created_date = LocalDateTime.now();
    }



    public int getId() {
        return id;
    }



    public float getCarbonQuantity() {
        return carbonQuantity;
    }

    public void setCarbonQuantity(float carbonQuantity) {
        this.carbonQuantity = carbonQuantity;
    }

    public LocalDate getStart_date() {
        return start_date;
    }


    public LocalDate getEnd_date() {
        return end_date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getCreated_date() {
        return created_date;
    }

    public abstract float calculateImpact(int param, AllTypesOfConsumption typeOfVehicle);

    }
