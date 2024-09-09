package utils;

import Domain.Consumption;

import java.time.LocalDate;
import java.util.List;

public class TimeUtil {

    public static int calculateDaysOfConusmption(Consumption consumption){
        int totalOfDays = 0;

            LocalDate accumulator = consumption.getStartDate();
            while (!accumulator.isAfter(consumption.getEndDate())){
                totalOfDays++;
                accumulator = accumulator.plusDays(1);
            }

        return totalOfDays;
    }

}
