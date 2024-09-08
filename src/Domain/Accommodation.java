package Domain;

import Domain.Enum.AllTypesOfConsumption;
import Domain.Enum.ConsumptionType;

import java.time.LocalDate;

public class Accommodation extends Consumption {

    private int quantity ;

    public Accommodation( User user , float carbonQuantity , int quantity ,  AllTypesOfConsumption consumptionImpactType , LocalDate startDate , LocalDate endDate) {
        super(user , carbonQuantity ,ConsumptionType.ACCOMMODATION, consumptionImpactType ,  startDate ,  endDate);
        this.impact = calculateImpact();
        this.quantity =quantity;
    }
    public Accommodation(int id , User user , float carbonQuantity , float impact , AllTypesOfConsumption consumptionImpactType , LocalDate startDate , LocalDate endDate) {
        super(user, carbonQuantity,ConsumptionType.ACCOMMODATION, consumptionImpactType, startDate, endDate);
        this.impact = impact;
    }

    @Override
    public float calculateImpact() {
        float impact = 0;
        if (consumptionImpactType.equals(AllTypesOfConsumption.ELECTRIC)) {
            impact = 1.5f;
        } else if (consumptionImpactType.equals(AllTypesOfConsumption.GAZ)) {
            impact = 2f;
        } else {
            return 0;
        }
        return quantity * impact * carbonQuantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


}
