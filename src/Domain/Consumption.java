package Domain;

import Domain.Enum.AllTypesOfConsumption;
import Domain.Enum.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

public abstract class Consumption {
    protected final int id;
    protected float carbonQuantity;
    protected ConsumptionType consumptionType;
    protected AllTypesOfConsumption consumptionImpactType;
    protected final LocalDate start_date;
    protected final LocalDate end_date;
    protected User user;
    protected final LocalDateTime created_date;
    protected float impact;

    public Consumption( int id , float carbonQuantity, LocalDate start_date, LocalDate end_date, User user) {
        this.id = id;
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

    public ConsumptionType getConsumptionType() {
        return consumptionType;
    }

    public void setConsumptionType(ConsumptionType consumptionType) {
        this.consumptionType = consumptionType;
    }

    public float getImpact() {
        return impact;
    }

    public void setImpact(float impact) {
        this.impact = impact;
    }

    public AllTypesOfConsumption getConsumptionImpactType() {
        return consumptionImpactType;
    }

    public void setConsumptionImpactType(AllTypesOfConsumption consumptionImpactType) {
        this.consumptionImpactType = consumptionImpactType;
    }
}
