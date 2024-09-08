package Domain;

import Domain.Enum.AllTypesOfConsumption;
import Domain.Enum.ConsumptionType;

import java.time.LocalDate;

public class Food extends Consumption {

    public Food(int id , User user , float carbonQuantity , float impact , AllTypesOfConsumption consumptionImpactType , LocalDate startDate , LocalDate endDate) {
        super(id ,user , carbonQuantity ,impact , ConsumptionType.TRANSPORT, consumptionImpactType ,  startDate ,  endDate);
    }

    @Override
    public float calculateImpact(int weight, AllTypesOfConsumption typeOfVehicle) {
        float impact = 0;
        if (typeOfVehicle.equals(AllTypesOfConsumption.MEAT)) {
            impact = 0.5f;
        } else if (typeOfVehicle.equals(AllTypesOfConsumption.VEGETABLE)) {
            impact = 5f;
        } else {
            return 0;
        }
        return weight * impact * carbonQuantity;
    }

}
