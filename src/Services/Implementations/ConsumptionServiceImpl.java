package Services.Implementations;

import Domain.*;
import Domain.Enum.AllTypesOfConsumption;
import Domain.Enum.ConsumptionType;
import Services.ConsumptionService;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class ConsumptionServiceImpl implements ConsumptionService {


    public boolean add(ConsumptionType consumptionType, int id, User user, float carbonQuantity, int intParam, AllTypesOfConsumption consumptionImpactType, LocalDate startDate, LocalDate endDate) {
        Consumption consumption = switch (consumptionType) {
            case TRANSPORT -> new Transport(id, user, carbonQuantity, intParam, consumptionImpactType, startDate, endDate);
            case FOOD -> new Food(id, user, carbonQuantity, intParam, consumptionImpactType, startDate, endDate);
            case ACCOMMODATION ->
                    new Accommodation(id, user, carbonQuantity, intParam, consumptionImpactType, startDate, endDate);
        };
        return true;
    }

    public float calculateAverageOfConsumptionWithinARange(User user, LocalDate startDate, LocalDate endDate) {
        return 0;
    }
}
