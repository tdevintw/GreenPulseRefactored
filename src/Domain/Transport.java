package Domain;

import Domain.Enum.AllTypesOfConsumption;
import Domain.Enum.ConsumptionType;

import java.time.LocalDate;

public class Transport extends Consumption {

    private int distance ;

    public Transport(int id , User user , float carbonQuantity , int distance ,  AllTypesOfConsumption consumptionImpactType , LocalDate startDate , LocalDate endDate) {
        super(id ,user , carbonQuantity ,ConsumptionType.TRANSPORT, consumptionImpactType ,  startDate ,  endDate);
        this.impact = calculateImpact();
        this.distance =distance;
    }

    public Transport(int id , User user , float carbonQuantity , float impact , AllTypesOfConsumption consumptionImpactType , LocalDate startDate , LocalDate endDate) {
        super(id, user, carbonQuantity,ConsumptionType.TRANSPORT, consumptionImpactType, startDate, endDate);
        this.impact = impact;
    }

    @Override
    public float calculateImpact() {
        float impact = 0;
        if (consumptionImpactType.equals(AllTypesOfConsumption.TRAIN)) {
            impact = 0.1f;
        } else if (consumptionImpactType.equals(AllTypesOfConsumption.CAR)) {
            impact = 0.5f;
        } else {
            return 0;
        }
        return distance * impact * carbonQuantity;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }
}
