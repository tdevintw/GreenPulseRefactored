package Services;

import Domain.Enum.ConsumptionType;
import Domain.User;

import java.time.LocalDate;

public interface ConsumptionService {
    boolean add(float carbonQuantity, ConsumptionType consumptionType, LocalDate startDate, LocalDate endDate, User user);

    float calculateAverageOfConsumptionWithinARange(User user, LocalDate startDate, LocalDate endDate);

}
