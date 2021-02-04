package projektor.projekt;

import org.springframework.data.annotation.Id;

class ProjektBeschreibung {

  @Id
  private Long id;
  private String beschreibung;


  public ProjektBeschreibung(String beschreibung) {
    this.beschreibung = beschreibung;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getBeschreibung() {
    return beschreibung;
  }

  public void setBeschreibung(String beschreibung) {
    this.beschreibung = beschreibung;
  }

  @Override
  public String toString() {
    return "ProjektBeschreibung{" +
        "text='" + beschreibung + '\'' +
        '}';
  }
}
