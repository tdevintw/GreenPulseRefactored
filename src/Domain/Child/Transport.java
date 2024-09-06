package Domain.Child;

import Domain.Consumption;
import Domain.Enum.AllTypesOfConsumption;
import Domain.User;

import java.time.LocalDate;

public class Transport extends Consumption {

    public Transport(float carbonQuantity, LocalDate start_date, LocalDate end_date, User user) {
        super(carbonQuantity, start_date, end_date, user);
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
        return distance * impact;
    }


}
