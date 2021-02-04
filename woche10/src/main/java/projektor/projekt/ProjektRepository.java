package projektor.projekt;

import static java.time.LocalDate.of;


import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import org.springframework.stereotype.Component;

// Diese Klasse muss ersetzt werden!
// Sie dient nur dazu, die Funktionalität zu demonstrieren
// Aufrufe müssen auch angepasst werden!

@Deprecated
@Component
public class ProjektRepository {

  public List<Projekt> blah() {
    Projekt a = new Projekt(1L, "Projekt A");
    a.setZeitraum(of(2021, 1, 1),
        of(2021, 6, 30));
    a.setBeschreibung("A umsetzen, so dass wir alle reich werden!");
    a.setBeteiligte(new HashSet<>());
    a.addPerson("Homer");
    a.addPerson("Marge");

    Projekt b = new Projekt(2L, "Projekt 2");
    b.setZeitraum(of(2021, 4, 1),
        of(2021, 8, 31));
    b.setBeschreibung("Das Projekt ist nicht so ganz wichtig");
    b.setBeteiligte(new HashSet<>());
    b.addPerson("Donald");
    b.addPerson("Daisy");

    return List.of(a, b);
  }


  public Projekt blub(long projektname) {
    Projekt b = new Projekt(2L, "Projekt 2");
    b.setZeitraum(of(2021, 4, 1),
        of(2021, 8, 31));
    b.setBeschreibung("Das Projekt ist nicht so ganz wichtig");
    b.setBeteiligte(new HashSet<>());
    b.addPerson("Donald");
    b.addPerson("Daisy");
    return b;
  }

  public void plonk(Projekt blub) {
    System.out.println("Plonk "+blub);
  }


  public List<Projekt> blahPerson(String person) {
    Projekt b = new Projekt(2L, "Projekt 2");
    b.setZeitraum(of(2021, 4, 1),
        of(2021, 8, 31));
    b.setBeschreibung("Das Projekt ist nicht so ganz wichtig");
    b.setBeteiligte(new HashSet<>());
    b.addPerson("Donald");
    b.addPerson("Daisy");
    return List.of(b);
  }


  public List<Projekt> blahZeit(LocalDate von, LocalDate bis) {
    Projekt a = new Projekt(1L, "Projekt A");
    a.setZeitraum(of(2021, 1, 1),
        of(2021, 6, 30));
    a.setBeschreibung("A umsetzen, so dass wir alle reich werden!");
    a.setBeteiligte(new HashSet<>());
    a.addPerson("Homer");
    a.addPerson("Marge");
    return List.of(a);
  }
}
