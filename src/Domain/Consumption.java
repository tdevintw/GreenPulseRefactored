package Domain;

import Domain.Enum.AllTypesOfConsumption;
import Domain.Enum.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

public abstract class Consumption {
    protected int id;
    protected float carbonQuantity;
    protected float impact;
    protected User user;
    protected ConsumptionType consumptionType;
    protected AllTypesOfConsumption consumptionImpactType;
    protected LocalDate startDate;
    protected LocalDate endDate;

    public Consumption(User user , float carbonQuantity , ConsumptionType consumptionType , AllTypesOfConsumption consumptionImpactType , LocalDate startDate , LocalDate endDate ){
        this.user = user;
        this.carbonQuantity = carbonQuantity;
        this.consumptionType = consumptionType;
        this.consumptionImpactType = consumptionImpactType;
        this.startDate = startDate;
        this.endDate = endDate;
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

    public float getImpact() {
        return impact;
    }

    public void setImpact(float impact) {
        this.impact = impact;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ConsumptionType getConsumptionType() {
        return consumptionType;
    }

    public void setConsumptionType(ConsumptionType consumptionType) {
        this.consumptionType = consumptionType;
    }

    public AllTypesOfConsumption getConsumptionImpactType() {
        return consumptionImpactType;
    }

    public void setConsumptionImpactType(AllTypesOfConsumption consumptionImpactType) {
        this.consumptionImpactType = consumptionImpactType;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public abstract float calculateImpact();

    public String toString(){
        return "consumption user : " + user.getName() + ", quantity : " + carbonQuantity + ", Consumption Type : "+consumptionType +" , "+ consumptionImpactType + " with impact "  + impact + ", between " + startDate + " and " + endDate;
    }
}
