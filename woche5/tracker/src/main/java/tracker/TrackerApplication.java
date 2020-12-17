package tracker;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import tracker.labor.LaborInformationsSystem;
import tracker.labor.TestErgebnis;
import tracker.persistenz.Storage;
import tracker.personen.Index;

@SpringBootApplication
@EnableScheduling
public class TrackerApplication {

  Tracker tracker;


  public static void main(String[] args) throws InterruptedException {
    SpringApplication.run(TrackerApplication.class, args);
  }

  // Am Anfang sucht Spring nach einer Bean für das
  // (funktionale) ApplicationRunner Interface und ruft die run Methode auf.
  @Bean
  ApplicationRunner init(LaborInformationsSystem lis, Tracker tracker) {
    return args -> {

      // Phase 1: Einige Testergebnisse in das LIS eintragen

      Index person1 = new Index("Max Mustermann", LocalDate.now());
      Index person2 = new Index("Maxine Musterfrau", LocalDate.now());
      TestErgebnis test1 = new TestErgebnis(person1, "dr.max@mustermann.com");
      TestErgebnis test2 = new TestErgebnis(person2, "dr.maxine@gmx.com");

      lis.add(test1);
      lis.add(test2);

      // Dem Tracker etwas Zeit geben, um die Ergebnisse abzuholen
      // Diese Zeit muss länger sein als das Zeit-Intervall in dem das LIS abgefragt wird
      Thread.sleep(10000);

      // Phase 2: Einige Kontaktpersonen für die positive Fälle hinzufügen

      tracker.kontaktperson("Max Mustermann", "Bob", LocalDate.now(), false);
      tracker.kontaktperson("Max Mustermann", "Caro", LocalDate.now(), true);
      tracker.kontaktperson("Max Mustermann", "Dennis", LocalDate.now(), false);

      // Phase 2 abschliessen, Phase 3 sollte dann automatisch getriggert werden
      tracker.trackingAbschliessen("Max Mustermann");


    };
  }


  // Alle 5s wird diese Methode ausgeführt.
  // Benötigt die @EnableScheduled Annotation oben an der Klasse
  /*@Scheduled(fixedDelay = 5000)
  void tick() {
    System.out.println("ping");
  }*/


}
