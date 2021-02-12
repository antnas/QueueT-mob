package lieferung;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

@Service
public class LieferungService {

  private final LieferungenRepository repository;

  private static final String bestellungenLiefern = "bestellungenLiefern";

  private static final String geliefert = "geliefert";

  public LieferungService(LieferungenRepository repository) {
    this.repository = repository;
  }

// Wir simulieren die Lieferung durch eine zeitliche Verzögerung
  @Async
  public void anfrage(Long id) {
    try {
      Thread.sleep((long) (Math.random() * 5000 + 1000));
    } catch (InterruptedException e) {

    }
    repository.save(id);
    bestellungErhalten(id);
  }

  public int anzahl() {
    return repository.count();
  }

  @Async
  public void bestellungErhalten(Long id) {
    ConnectionFactory factory = new ConnectionFactory();
    factory.setHost("localhost");
    factory.setUsername("groot");
    factory.setPassword("iamgroot");

    try {
      Connection connection = factory.newConnection();
      Channel channel = connection.createChannel();
      channel.queueDeclare(bestellungenLiefern, false, false, false, null);
      System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

      DeliverCallback deliverCallback = (consumerTag, delivery) -> {
        String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
        System.out.println(" [x] Received id: '" + message + "'");
      };
      channel.basicConsume(bestellungenLiefern, true, deliverCallback, consumerTag -> { });
    } catch (IOException e) {
      e.printStackTrace();
    } catch (TimeoutException e) {
      e.printStackTrace();
    }
  }

}
