package Services.Implementations;

import Domain.*;
import Domain.Enum.ConsumptionType;
import Services.ConsumptionService;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class ConsumptionServiceImpl implements ConsumptionService {


    public boolean add(float carbonQuantity, ConsumptionType consumptionType, LocalDate startDate, LocalDate endDate, User user) {
        Consumption consumption = switch (consumptionType) {
            case TRANSPORT -> new Transport(carbonQuantity, startDate, endDate, user);
            case FOOD -> new Food(carbonQuantity, startDate, endDate, user);
            case ACCOMMODATION -> new Accommodation(carbonQuantity, startDate, endDate, user);
        };
        try {
            user.setConsumption(consumption);
            //here we add consumption to databse
            return true;
        } catch (Exception e) {
            System.err.println("An Error was found : " + e.getMessage());
            return false;
        }
    }

    public float calculateAverageOfConsumptionWithinARange(User user, LocalDate startDate, LocalDate endDate) {
        return user.getConsumptions().stream()
                .filter(consumption -> consumption.getStart_date().isAfter(startDate) || consumption.getStart_date().equals(startDate))
                .filter(consumption -> consumption.getEnd_date().isBefore(endDate) || consumption.getEnd_date().equals(endDate))
                .map(Consumption::getImpact)
                .reduce(Float::sum).get();
    }
}
