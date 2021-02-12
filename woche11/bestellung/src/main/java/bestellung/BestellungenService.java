package bestellung;

import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeoutException;
import javax.sound.midi.Receiver;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;


@Component
public class BestellungenService {


  @Value("${lieferung.endpoint}")
  private String endpoint;

  private static final String bestellungenLiefern = "bestellungenLiefern";

  private static final String geliefert = "geliefert";

  private final BestellungRepository repository;

  public BestellungenService(BestellungRepository repository) {
    this.repository = repository;
  }

  public Collection<Bestellung> alleBestellungen() {
    return repository.findAll();
  }

  public void neueBestellungFuer(String kunde) {
    Bestellung bestellung = new Bestellung(kunde);
    repository.save(bestellung);

    //queue.add(bestellung);
    //sendeBestellung(bestellung.getId());

    ConnectionFactory factory = new ConnectionFactory();
    factory.setPassword("iamgroot");
    factory.setUsername("groot");
    factory.setHost("localhost");

    try(Connection connection = factory.newConnection();
        Channel channel = connection.createChannel()) {
      channel.queueDeclare(bestellungenLiefern, false, false, false, null);
      String message = String.valueOf(bestellung.getId());
      channel.basicPublish("", bestellungenLiefern, null, message.getBytes(StandardCharsets.UTF_8));
      System.out.println("Bestellung #" + message + " in the queue.");
    } catch (TimeoutException | IOException e) {
      e.printStackTrace();
    }

    // Hier muss das andere SCS asynchron informiert werden
    // Achten Sie darauf, dass die Nachricht an das andere System niemals verloren geht
    
    // Probieren Sie aus, das andere System vorher zu deaktivieren. 
    // Wenn es reaktiviert wird, mussen die fehlenden Nachrichten übertragen werden.

  }

  /*
  @Async
  public void sendeBestellung(Long id) {
    //var res2 = Unirest.get("http://localhost:8081/count");
    var res = Unirest.post(endpoint+"/{id}").routeParam("id", String.valueOf(id));
    try {
      System.out.println(res.asString().getBody());
    } catch (UnirestException e) {
      e.printStackTrace();
    }
  }
   */


  /*
  @Scheduled(fixedDelay = 2000)
  public void sendeBestellung(Long id) {
    try {
      var res = Unirest.post(endpoint+"/{id}").
          routeParam("id", String.valueOf(queue.peek().getId()))
          .asString().getBody();
      queue.remove();
    } catch (UnirestException e) {
        e.printStackTrace();
    }
  }
  */



}
