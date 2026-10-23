import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class Predictor {
    private double qualifyingWeight;
    private double formWeight;

    public Predictor(double qualifyingWeight, double formWeight){
        this.qualifyingWeight = qualifyingWeight;
        this.formWeight = formWeight;
    }

    public double calcScore(Driver driver){
        double qualiScore = driver.getQualifyingPosition();
        double form = driver.getRecentForm();
        return (qualiScore * qualifyingWeight) + (form * formWeight);
    }

    public List<Driver> predictOrder(List<Driver> drivers){
        List<Driver> predicted = new ArrayList<>(drivers);
        predicted.sort(Comparator.comparingDouble(this :: calcScore));
        return predicted;
    }

}