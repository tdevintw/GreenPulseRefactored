package Domain;

import Domain.Enum.AllTypesOfConsumption;
import Domain.Enum.ConsumptionType;

import java.time.LocalDate;

public class Transport extends Consumption {

    public Transport(int id , User user , float carbonQuantity , float impact , AllTypesOfConsumption consumptionImpactType , LocalDate startDate , LocalDate endDate) {
        super(id ,user , carbonQuantity ,impact , ConsumptionType.TRANSPORT, consumptionImpactType ,  startDate ,  endDate);
    }

    @Override
    public float calculateImpact(int distance, AllTypesOfConsumption typeOfVehicle) {
        float impact = 0;
        if (typeOfVehicle.equals(AllTypesOfConsumption.TRAIN)) {
            impact = 0.1f;
        } else if (typeOfVehicle.equals(AllTypesOfConsumption.CAR)) {
            impact = 0.5f;
        } else {
            return 0;
        }
        return distance * impact * carbonQuantity;
    }


}
