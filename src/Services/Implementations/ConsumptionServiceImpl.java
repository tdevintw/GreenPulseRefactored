package Services.Implementations;

import Domain.*;
import Domain.Enum.AllTypesOfConsumption;
import Domain.Enum.ConsumptionType;
import Repository.ConsumptionRepository;
import Services.ConsumptionService;
import utils.ConsumptionRange;
import utils.TotalConsumption;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class ConsumptionServiceImpl implements ConsumptionService {

    private static ConsumptionRepository consumptionRepository;


    public ConsumptionServiceImpl() {
        consumptionRepository = new ConsumptionRepository();
    }

    public boolean add(ConsumptionType consumptionType, User user, float carbonQuantity, int intParam, AllTypesOfConsumption consumptionImpactType, LocalDate startDate, LocalDate endDate) {
        if (ConsumptionRange.isConsumptionExist(user.getConsumptions(), startDate, endDate)) {
            System.out.println("Consumption Range already exist");
            return false;
        }
        Consumption consumption = switch (consumptionType) {
            case TRANSPORT -> new Transport(user, carbonQuantity, intParam, consumptionImpactType, startDate, endDate);
            case FOOD -> new Food(user, carbonQuantity, intParam, consumptionImpactType, startDate, endDate);
            case ACCOMMODATION ->
                    new Accommodation(user, carbonQuantity, intParam, consumptionImpactType, startDate, endDate);
        };
        user.setConsumption(consumption);
        return consumptionRepository.add(consumption, user.getId());
    }

    public double calculateAverageOfConsumptionWithinARange(User user, LocalDate startDate, LocalDate endDate) {
        List<Consumption> userConsumptionWithinRange = user.getConsumptions().stream()
                .filter(consumption -> (consumption.getStartDate().isAfter(startDate) || consumption.getStartDate().isEqual(startDate)) && (consumption.getEndDate().isBefore(endDate) || consumption.getEndDate().isEqual(endDate)))
                .toList();
        if (userConsumptionWithinRange.isEmpty()) {
            return 0;
        }
        return TotalConsumption.TotalConsumptionOfListOfConsumptions(userConsumptionWithinRange) / userConsumptionWithinRange.size();
    }

    public List<Consumption> getUserConsumptions(User user) {
        return consumptionRepository.getUserConsumptions(user.getId());
    }

    //calculate the impact per a single day of a user
    public static double averageConsumptionPerDay(List<Consumption> consumptions) {
        double totalConsumption = consumptions.stream().mapToDouble(Consumption::getImpact).sum();
        long totalDays = consumptions.stream().mapToLong(consumption -> ChronoUnit.DAYS.between(consumption.getStartDate(), consumption.getEndDate().plusDays(1))).sum();

        return totalDays > 0 ? totalConsumption / totalDays : 0;
    }
}
