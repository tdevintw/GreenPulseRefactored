package Domain;

public class Report {
    private final int id ;

    public Report(){
        id = (int)(Math.random()*1000000000);
    }

    public int getId() {
        return id;
    }
}
