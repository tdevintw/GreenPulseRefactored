package utils;

import Domain.Consumption;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class ConsumptionRange {

    public static boolean isConsumptionExist(List<Consumption> consumptions , LocalDate startDate , LocalDate endDate){
       int size =  consumptions.stream()
                .filter(consumption -> (consumption.getStartDate().isAfter(startDate) || consumption.getStartDate().isEqual(startDate)) && (consumption.getEndDate().isBefore(startDate) || consumption.getEndDate().isEqual(endDate)))
                .toList()
                .size();
        return  size > 0;
    }
}
