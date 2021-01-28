package kraechz.app;

import static java.util.stream.Collectors.toList;


import java.util.ArrayList;
import java.util.List;
import kraechz.app.Kraechz;
import org.springframework.stereotype.Component;

@Component
public class KraechzRepository {

  List<Kraechz> kraechze = new ArrayList<>();


  public List<Kraechz> alle() {
    // Alle Objekte aus der Datenbank holen
    return kraechze;
  }


  public List<Kraechz> filtereNachHandle(String name) {
    // Nur die Objekte holen, deren Handlename übergeben wurde
    // Muss ein passendes SQL Statement verwenden!
    // Darf nicht alle Objekte holen und dann filtern!
    return kraechze.stream()
        .filter(k -> name.equals(k.getHandle()))
        .collect(toList());
  }

  public void kraechze(Kraechz k) {
    kraechze.add(k);
  }

}
