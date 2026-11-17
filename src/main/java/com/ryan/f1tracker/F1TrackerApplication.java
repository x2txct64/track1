package  com.ryan.f1tracker;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class F1TrackerApplication {
    public static void main(String[] args) {
        SpringApplication.run(F1TrackerApplication.class, args);
    }

    @Bean
    CommandLineRunner test(JolpicaClient client) {
        return args -> {
            Standings s = client.getDriverStandings(2025);
            System.out.println("Season " + s.getSeason() + ", after round " + s.getRound());
            for (Driver d : s.getSortedStandings()) {
                System.out.println(d.getPosition() + ". " + d.getName() + " (" + d.getTeam() + ") "
                + d.getCurrentPoints() + " pts, " + d.getWins() + " wins");
            }
        }; 
    }
}