package projektor.projekt;

class ProjektBeschreibung {
  private String beschreibung;

  public ProjektBeschreibung(String beschreibung) {
    this.beschreibung = beschreibung;
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
