import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Comparator;


public class Standings {
    private List<Driver> drivers;

    public Standings() {
        this.drivers = new ArrayList<>();
    }

    public void addDriver(Driver driver) {
        drivers.add(driver);
    }
    
}
