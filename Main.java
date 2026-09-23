import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Create drivers
        Driver verstappen = new Driver("Verstappen", "Red Bull");
        Driver hamilton = new Driver("Hamilton", "Ferrari");
        Driver leclerc = new Driver("Leclerc", "Ferrari");
        Driver norris = new Driver("Norris", "McLaren");

        // Give them different point totals via race results
        verstappen.addRaceResult(1, 25);
        verstappen.addRaceResult(2, 18);   // total: 43

        hamilton.addRaceResult(3, 15);
        hamilton.addRaceResult(1, 25);     // total: 40

        leclerc.addRaceResult(5, 10);      // total: 10

        norris.addRaceResult(2, 18);
        norris.addRaceResult(2, 18);       // total: 36

        // Build standings
        Standings standings = new Standings();
        standings.addDriver(verstappen);
        standings.addDriver(hamilton);
        standings.addDriver(leclerc);
        standings.addDriver(norris);

        // Get sorted standings and print
        List<Driver> ranked = standings.getSortedStandings();
        int position = 1;
        for (Driver d : ranked) {
            System.out.println(position + ". " + d.getName() + " - " + d.getCurrentPoints() + " pts");
            position++;
        }
    }
}