package Domain;

import Domain.Enum.AllTypesOfConsumption;

import java.time.LocalDate;

public class Accommodation extends Consumption {

    public Accommodation(float carbonQuantity, LocalDate start_date, LocalDate end_date, User user) {
        super(carbonQuantity, start_date, end_date, user);
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
        return quantity * impact;
    }

}
