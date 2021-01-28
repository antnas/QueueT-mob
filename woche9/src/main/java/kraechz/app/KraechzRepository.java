package kraechz.app;

import static java.util.stream.Collectors.toList;
import static javax.swing.plaf.synth.Region.TABLE;


import java.util.ArrayList;
import java.util.List;
import kraechz.app.Kraechz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
public class KraechzRepository {

  @Autowired
  private JdbcTemplate db;


  public KraechzRepository() {
    db.execute("CREATE TABLE IF NOT EXISTS 'kraechz' (handle VARCHAR(255), text VARCHAR(140));");
  }

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
