package projektor.projekt;

import java.time.LocalDate;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
public class Zeitraum {

  @Id
  private Long id;
  private LocalDate von;
  private LocalDate bis;

  public Zeitraum(LocalDate von, LocalDate bis) {
    this.von = von;
    this.bis = bis;
  }

  @Override
  public String toString() {
    return "Zeitraum{" +
        "von=" + von +
        ", bis=" + bis +
        '}';
  }
}
