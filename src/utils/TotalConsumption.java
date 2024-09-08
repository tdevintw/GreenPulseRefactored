package utils;

import Domain.Consumption;
import Domain.User;

import java.time.LocalDate;
import java.util.List;

public class TotalConsumption {
    public static double TotalConsumptionOfUser(User user) {
        return user.getConsumptions().stream().mapToDouble(Consumption::getImpact).sum();
    }

    public static double TotalConsumptionOfListOfConsumptions(List<Consumption> consumptionList) {
        return consumptionList.stream().mapToDouble(Consumption::getImpact).sum();
    }

    public static double TotalConsumptionOfUserWithFilter(User user , LocalDate startDate , LocalDate endDate) {
        return user.getConsumptions().stream()
                .filter(consumption -> (consumption.getStartDate().isAfter(startDate) || consumption.getStartDate().isEqual(startDate)) && (consumption.getEndDate().isBefore(endDate) || consumption.getEndDate().isEqual(endDate)))
                .mapToDouble(Consumption::getImpact).sum();
    }
}
