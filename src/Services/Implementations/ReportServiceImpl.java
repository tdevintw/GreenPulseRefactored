package Services.Implementations;

import Domain.Consumption;
import Domain.User;
import Services.ReportService;
import utils.TimeUtil;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.temporal.WeekFields;
import java.util.*;

public class ReportServiceImpl implements ReportService {

    @Override
    public void generateDailyReport(User user) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy MM dd");
        Map<LocalDate, Float> dayAverageConsumption = new HashMap<>(); //this map contain all the days of the consumptions each day is unique since we cant enter consumption if the range alreayd exists
        float TotalImpactOfConsumption;
        LocalDate accumulator;
        float average = 0;
        int daysOfConsumptions = 0;
        for (Consumption consumption : user.getConsumptions()) {
            TotalImpactOfConsumption = consumption.getImpact();
            daysOfConsumptions = TimeUtil.calculateDaysOfConusmption(consumption);
            if (daysOfConsumptions > 0) {
                average = TotalImpactOfConsumption / daysOfConsumptions;
            } else {
                average = 0;
            }
            accumulator = consumption.getStartDate();
            while (!accumulator.isAfter(consumption.getEndDate())) {
                dayAverageConsumption.put(accumulator, average);
                accumulator = accumulator.plusDays(1);
            }
            System.out.println();
            System.out.println();
        }
        Map<LocalDate, Float> sortedMap = new TreeMap<>(dayAverageConsumption);
        sortedMap.forEach((key, value) -> System.out.println("consumption in : " + key.format(formatter) + " is : " + value + " KG"));
    }


    @Override
    public void generateWeeklyReport(User user) {
        double impactAveragePerDay = ConsumptionServiceImpl.averageConsumptionPerDay(user.getConsumptions());
        WeekFields weekFields = WeekFields.of(Locale.getDefault());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy MM");
        Map<String, Double> monthAverageConsumption = new HashMap<>();
        for (Consumption consumption : user.getConsumptions()) {
            LocalDate accumulator = consumption.getStartDate();
            while (!accumulator.isAfter(consumption.getEndDate())) {
                if (monthAverageConsumption.containsKey(""+accumulator.getYear()+" "+accumulator.get(weekFields.weekOfWeekBasedYear()))) {
                    double oldValue = monthAverageConsumption.get(""+accumulator.getYear()+" "+accumulator.get(weekFields.weekOfWeekBasedYear()));
                    monthAverageConsumption.put(""+accumulator.getYear()+" "+accumulator.get(weekFields.weekOfWeekBasedYear()),oldValue+impactAveragePerDay);
                } else {
                    monthAverageConsumption.put(""+accumulator.getYear()+" "+accumulator.get(weekFields.weekOfWeekBasedYear()), impactAveragePerDay);
                }
                accumulator = accumulator.plusDays(1);
            }
        }
        Map<String, Double> sortedMonthAverageConsumption = new TreeMap<>(monthAverageConsumption);
        sortedMonthAverageConsumption.forEach((key, value) -> System.out.println("consumption in : " + key + " is : " + value + " KG"));
    }


    @Override
    public void generateMonthlyReport(User user) {
        double impactAveragePerDay = ConsumptionServiceImpl.averageConsumptionPerDay(user.getConsumptions());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy MM");
        Map<YearMonth, Double> monthAverageConsumption = new HashMap<>();
        for (Consumption consumption : user.getConsumptions()) {
            LocalDate accumulator = consumption.getStartDate();
            while (!accumulator.isAfter(consumption.getEndDate())) {
                if (monthAverageConsumption.containsKey(YearMonth.from(accumulator))) {
                    double oldValue = monthAverageConsumption.get(YearMonth.from(accumulator));
                    monthAverageConsumption.put(YearMonth.from(accumulator),oldValue+impactAveragePerDay);
                } else {
                    monthAverageConsumption.put(YearMonth.from(accumulator), impactAveragePerDay);
                }
                accumulator = accumulator.plusDays(1);
            }
        }
        Map<YearMonth, Double> sortedMonthAverageConsumption = new TreeMap<>(monthAverageConsumption);
        sortedMonthAverageConsumption.forEach((key, value) -> System.out.println("consumption in : " + key.format(formatter) + " is : " + value + " KG"));
    }
}
