package projektor.projekt;

import java.time.LocalDate;
import org.springframework.data.annotation.Id;

class Zeitraum {


  @Id
  private Long id;
  private final LocalDate von;
  private final LocalDate bis;

  public Zeitraum(LocalDate von, LocalDate bis) {
    this.von = von;
    this.bis = bis;
  }

  public void setId(Long id) {
    this.id = id;
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
