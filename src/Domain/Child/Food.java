package Domain.Child;

import Domain.Consumption;
import Domain.Enum.AllTypesOfConsumption;
import Domain.User;

import java.time.LocalDate;

public class Food extends Consumption {

    public Food(float carbonQuantity, LocalDate start_date, LocalDate end_date, User user) {
        super(carbonQuantity, start_date, end_date, user);
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
        return weight * impact;
    }

}
