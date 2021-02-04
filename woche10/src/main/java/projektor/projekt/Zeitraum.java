package projektor.projekt;

import java.time.LocalDate;

class Zeitraum {

  private final LocalDate von;
  private final LocalDate bis;

  public Zeitraum(LocalDate von, LocalDate bis) {
    this.von = von;
    this.bis = bis;
  }

  public LocalDate getVon() {
    return von;
  }

  public LocalDate getBis() {
    return bis;
  }

  @Override
  public String toString() {
    return "Zeitraum{" +
        "von=" + von +
        ", bis=" + bis +
        '}';
  }
}
