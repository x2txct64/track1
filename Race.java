import java.util.ArrayList;
import java.util.List;

public class Race {
    private String raceName;
    private String trackName;
    private int round;
    private boolean isComplete;
    private List<Driver> qualifyingOrder;
    private List<Driver> actualResults;
    private List<Driver> predictedResults;

    public Race(String raceName, String trackName, int round) {
        this.raceName = raceName;
        this.trackName = trackName;
        this.round = round;
        this.isComplete = false;
        this.qualifyingOrder = new ArrayList<>();
        this.actualResults = new ArrayList<>();
        this.predictedResults = new ArrayList<>();
    }

    public String getRaceName() {
        return raceName;
    }

    public String getTrackName() {
        return trackName;
    }

    public int getRound() {
        return round;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public List<Driver> getQualifyingOrder() {
        return qualifyingOrder;
    }

    public List<Driver> getActualResults() {
        return actualResults;
    }

    public List<Driver> getPredictedResults() {
        return predictedResults;
    }

    public void setQualifyingOrder(List<Driver> order) {
        this.qualifyingOrder = order;
    }

    public void setActualResult(List<Driver> order) {
        this.actualResults = order;
        this.isComplete = true;
    }

    public void setPredictedResults(List<Driver> order) {
        this.predictedResults = order;
    }
}
