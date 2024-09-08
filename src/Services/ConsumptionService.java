package Services;

import Domain.Consumption;
import Domain.Enum.AllTypesOfConsumption;
import Domain.Enum.ConsumptionType;
import Domain.User;

import java.time.LocalDate;
import java.util.List;

public interface ConsumptionService {
    boolean add(ConsumptionType consumptionType ,User user , float carbonQuantity , int intParam ,  AllTypesOfConsumption consumptionImpactType , LocalDate startDate , LocalDate endDate);

    double calculateAverageOfConsumptionWithinARange(User user, LocalDate startDate, LocalDate endDate);

    List<Consumption> getUserConsumptions(User user);
}
