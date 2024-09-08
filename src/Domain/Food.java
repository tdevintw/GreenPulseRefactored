package Domain;

import Domain.Enum.AllTypesOfConsumption;
import Domain.Enum.ConsumptionType;

import java.time.LocalDate;

public class Food extends Consumption {
    private int weight ;

    public Food(int id , User user , float carbonQuantity , int weight ,  AllTypesOfConsumption consumptionImpactType , LocalDate startDate , LocalDate endDate) {
        super(id ,user , carbonQuantity ,ConsumptionType.TRANSPORT, consumptionImpactType ,  startDate ,  endDate);
        this.impact = calculateImpact();
        this.weight =weight;
    }

    public Food(int id , User user , float carbonQuantity , float impact , AllTypesOfConsumption consumptionImpactType , LocalDate startDate , LocalDate endDate) {
        super(id, user, carbonQuantity,ConsumptionType.FOOD, consumptionImpactType, startDate, endDate);
        this.impact = impact;
    }


        @Override
    public float calculateImpact() {
        float impact = 0;
        if (consumptionImpactType.equals(AllTypesOfConsumption.MEAT)) {
            impact = 0.5f;
        } else if (consumptionImpactType.equals(AllTypesOfConsumption.VEGETABLE)) {
            impact = 5f;
        } else {
            return 0;
        }
        return weight * impact * carbonQuantity;
    }

    public int getDistance() {
        return weight;
    }

    public void setDistance(int weight) {
        this.weight = weight;
    }

}
