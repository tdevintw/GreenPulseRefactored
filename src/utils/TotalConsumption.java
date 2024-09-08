package utils;

import Domain.Consumption;
import Domain.User;

public class TotalConsumption {
    public static double TotalConsumptionOfUser(User user){
       return  user.getConsumptions().stream().mapToDouble(Consumption::getImpact).sum();
    }
}
