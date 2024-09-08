package utils;

import Domain.Consumption;
import Domain.User;

import java.util.List;

public class TotalConsumption {
    public static double TotalConsumptionOfUser(User user){
       return  user.getConsumptions().stream().mapToDouble(Consumption::getImpact).sum();
    }
    public static double TotalConsumptionOfListOfConsumptions(List<Consumption> consumptionList){
        return  consumptionList.stream().mapToDouble(Consumption::getImpact).sum();
    }
}
