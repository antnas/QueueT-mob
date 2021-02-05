package projektor.projekt;

import static java.util.stream.Collectors.toList;


import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import jdk.jfr.DataAmount;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Embedded;
import projektor.stereotypes.AggregateRoot;

@AggregateRoot
@Data
@NoArgsConstructor
public class Projekt {

  @Id
  private Long id;

  private String name;
  @Embedded.Nullable
  private ProjektBeschreibung beschreibung;

  private Zeitraum zeitraum;

  private Set<Person> beteiligte;

  public Projekt(Long id, String name) {
    this.id = id;
    this.name = name;
  }

  LocalDate getStartZeitpunkt(){
    return zeitraum.getVon();
  }

  LocalDate getEndZeitpunkt(){
    return zeitraum.getBis();
  }

  public void setBeschreibung(String beschreibung) {
    ProjektBeschreibung x  = new ProjektBeschreibung(beschreibung);
    this.beschreibung = x;
  }

  public void setZeitraum(LocalDate von, LocalDate bis) {
    this.zeitraum = new Zeitraum(von, bis);
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
