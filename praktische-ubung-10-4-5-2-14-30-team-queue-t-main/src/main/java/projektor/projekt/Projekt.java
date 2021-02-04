package projektor.projekt;

import static java.util.stream.Collectors.toList;


import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import projektor.stereotypes.AggregateRoot;

@AggregateRoot
public class Projekt {

  private final Long id;

  private final String name;
  private ProjektBeschreibung beschreibung;

  private Zeitraum zeitraum;

  private Set<Person> beteiligte;

  public Projekt(Long id, String name) {
    this.id = id;
    this.name = name;
  }

  public String getBeschreibung() {
    return beschreibung.getBeschreibung();
  }

  public void setBeschreibung(String beschreibung) {
    ProjektBeschreibung x  = new ProjektBeschreibung(beschreibung);
    this.beschreibung = x;
  }

  public LocalDate getStartZeitpunkt() {
    return zeitraum.getVon();
  }

  public LocalDate getEndZeitpunkt() {
    return zeitraum.getBis();
  }

  public void setZeitraum(LocalDate von, LocalDate bis) {
    this.zeitraum = new Zeitraum(von, bis);
  }

  public String getName() {
    return name;
  }

  public List<String> getBeteiligte() {
    return beteiligte.stream().map(Person::getName).collect(toList());
  }

  void setBeteiligte(Set<Person> beteiligte) {
    this.beteiligte = beteiligte;
  }

  public void addPerson(String name) {
    beteiligte.add(new Person(name));
  }

  void setBeschreibung(ProjektBeschreibung beschreibung) {
    this.beschreibung = beschreibung;
  }

  void setZeitraum(Zeitraum zeitraum) {
    this.zeitraum = zeitraum;
  }

  public Long getId() {
    return id;
  }

  @Override
  public String toString() {
    return "Projekt{" +
        "name='" + name + '\'' +
        ", beschreibung=" + beschreibung +
        ", zeitraum=" + zeitraum +
        ", beteiligte=" + beteiligte +
        '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Projekt projekt = (Projekt) o;
    return id.equals(projekt.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
