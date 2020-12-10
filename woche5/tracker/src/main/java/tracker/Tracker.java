package tracker;

import java.time.LocalDate;
import java.util.List;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import tracker.betriebsaerztlicherdienst.BetriebsaerztlicherDienst;
import tracker.gesundheitsamt.Gesundheitsamt;
import tracker.labor.LaborInformationsSystem;
import tracker.labor.TestErgebnis;
import tracker.mail.Mail;
import tracker.mail.MailSender;
import tracker.persistenz.Storage;
import tracker.personen.Index;
import tracker.personen.KontaktPerson;

@Component
@EnableScheduling
public class Tracker {

  private final Storage storage;
  private final MailSender sender;
  private final Gesundheitsamt gesundheitsamt;
  private final BetriebsaerztlicherDienst betriebsaerztlicherDienst;
  private final LaborInformationsSystem laborInformationsSystem;

  public Tracker(Storage storage, MailSender sender, Gesundheitsamt gesundheitsamt, BetriebsaerztlicherDienst betriebsaerztlicherDienst, LaborInformationsSystem laborInformationsSystem) {
    this.storage = storage;
    this.sender = sender;
    this.gesundheitsamt = gesundheitsamt;
    this.betriebsaerztlicherDienst = betriebsaerztlicherDienst;
    this.laborInformationsSystem = laborInformationsSystem;
  }

  void trackingStarten(Index index, String arztMail) {

    // throw new UnsupportedOperationException("not yet implemented");
    // Index abspeichern
    storage.addIndex(index);
    // Mail an die zuständige Person schicken
    sender.send(new Mail(arztMail));
  }

  public void kontaktperson(String indexName, String kontaktpersonName,
                            LocalDate expositionsZeitpunkt, boolean personal) {

    // Index aus dem Storage laden
    Index index = storage.getIndex(indexName);

    // Kontaktperson im Storage abspeichern
    storage.addKontakt(new KontaktPerson(kontaktpersonName, index, expositionsZeitpunkt, personal));
  }

  public void trackingAbschliessen(String indexName) {

    // Index aus dem Storage laden
    Index index = storage.getIndex(indexName);

    // KontaktPersonen aus dem Storage laden
    List<KontaktPerson> kontakte = storage.getKontakte(index);

    // Meldung an das Gesundheitsamt schicken
    gesundheitsamt.abschicken();

    // Meldung an BÄD
    kontakte.forEach(betriebsaerztlicherDienst::quarantaeneMeldung);
    // (Fun Fact: der Betriebsärztliche Dienst am UKD wird wirklich so abgekürzt)

    betriebsaerztlicherDienst.versenden();
  }

  // Hier muss noch Code rein, der das LIS regelmäßig abfragt
  @Scheduled(fixedDelay = 5000)
  void fetchPositiveErgebnisse() {
    List<TestErgebnis> testergebnisse = laborInformationsSystem.fetchPositiveErgebnisse();
    for(TestErgebnis t: testergebnisse) {
      trackingStarten(t.index(), t.arztMail());
    }
  }
}
