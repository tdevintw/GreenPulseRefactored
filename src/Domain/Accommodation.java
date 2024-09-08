package Domain;

import Domain.Enum.AllTypesOfConsumption;
import Domain.Enum.ConsumptionType;

import java.time.LocalDate;

public class Accommodation extends Consumption {

    public Accommodation(int id , User user , float carbonQuantity , float impact , AllTypesOfConsumption consumptionImpactType , LocalDate startDate , LocalDate endDate) {
        super(id ,user , carbonQuantity ,impact , ConsumptionType.TRANSPORT, consumptionImpactType ,  startDate ,  endDate);
    }

    @Override
    public float calculateImpact(int quantity, AllTypesOfConsumption typeOfVehicle) {
        float impact = 0;
        if (typeOfVehicle.equals(AllTypesOfConsumption.ELECTRIC)) {
            impact = 1.5f;
        } else if (typeOfVehicle.equals(AllTypesOfConsumption.GAZ)) {
            impact = 2f;
        } else {
            return 0;
        }
        return quantity * impact * carbonQuantity;
    }

}
