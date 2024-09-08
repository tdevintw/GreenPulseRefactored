package Services;

import Domain.Enum.AllTypesOfConsumption;
import Domain.Enum.ConsumptionType;
import Domain.User;

import java.time.LocalDate;

public interface ConsumptionService {
    boolean add(ConsumptionType consumptionType , int id , User user , float carbonQuantity , int intParam ,  AllTypesOfConsumption consumptionImpactType , LocalDate startDate , LocalDate endDate);

    float calculateAverageOfConsumptionWithinARange(User user, LocalDate startDate, LocalDate endDate);

}
