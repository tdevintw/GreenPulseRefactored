package Domain;
import java.util.ArrayList;
import java.util.List;

public class User {
    private final int id;
    private String name ;
    private String password ;
    private int age ;
    private List<Consumption> consumptions ;

    public User(int id, String name, String password, int age) {
        this.id = (int) (Math.random()*1000000000);
        this.name = name;
        this.password = password;
        this.age = age;
        this.consumptions = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Consumption> getConsumptions() {
        return consumptions;
    }

    public void setConsumption(Consumption consumption) {
        this.consumptions.add(consumption);
    }
}
