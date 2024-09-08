package Services.Implementations;

import Domain.*;
import Domain.Enum.AllTypesOfConsumption;
import Domain.Enum.ConsumptionType;
import Repository.ConsumptionRepository;
import Repository.UserRepository;
import Services.ConsumptionService;
import utils.TotalConsumption;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class ConsumptionServiceImpl implements ConsumptionService {

    private static ConsumptionRepository consumptionRepository;
    private static UserRepository userRepository;


    public  ConsumptionServiceImpl(){
        consumptionRepository = new ConsumptionRepository();
        userRepository = new UserRepository();
    }

    public boolean add(ConsumptionType consumptionType, int id, User user, float carbonQuantity, int intParam, AllTypesOfConsumption consumptionImpactType, LocalDate startDate, LocalDate endDate) {
        Consumption consumption = switch (consumptionType) {
            case TRANSPORT -> new Transport(id, user, carbonQuantity, intParam, consumptionImpactType, startDate, endDate);
            case FOOD -> new Food(id, user, carbonQuantity, intParam, consumptionImpactType, startDate, endDate);
            case ACCOMMODATION ->
                    new Accommodation(id, user, carbonQuantity, intParam, consumptionImpactType, startDate, endDate);
        };

        return consumptionRepository.add(consumption , user.getId());
    }

    public double calculateAverageOfConsumptionWithinARange(User user, LocalDate startDate, LocalDate endDate) {
        List<Consumption> userConsumptionWithinRange = user.getConsumptions().stream()
                .filter(consumption -> (consumption.getStartDate().isAfter(startDate) || consumption.getStartDate().isEqual(startDate)) && (consumption.getEndDate().isBefore(startDate) || consumption.getEndDate().isEqual(endDate)))
                .toList();
        return TotalConsumption.TotalConsumptionOfListOfConsumptions(userConsumptionWithinRange);
    }
}
