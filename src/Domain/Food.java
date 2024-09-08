package Domain;

import Domain.Enum.AllTypesOfConsumption;
import Domain.Enum.ConsumptionType;

import java.time.LocalDate;

public class Food extends Consumption {
    private int weight ;

    public Food(User user , float carbonQuantity , int weight ,  AllTypesOfConsumption consumptionImpactType , LocalDate startDate , LocalDate endDate) {
        super(user , carbonQuantity ,ConsumptionType.TRANSPORT, consumptionImpactType ,  startDate ,  endDate);
        this.weight =weight;
        this.impact = calculateImpact();

    }

    public Food(int id , User user , float carbonQuantity , float impact , AllTypesOfConsumption consumptionImpactType , LocalDate startDate , LocalDate endDate) {
        super(user, carbonQuantity,ConsumptionType.FOOD, consumptionImpactType, startDate, endDate);
        this.id = id;
        this.impact = impact;
    }


        @Override
    public float calculateImpact() {
        float impact = 0;
        if (consumptionImpactType.equals(AllTypesOfConsumption.VEGETABLE)) {
            impact = 0.5f;
        } else if (consumptionImpactType.equals(AllTypesOfConsumption.MEAT)) {
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
