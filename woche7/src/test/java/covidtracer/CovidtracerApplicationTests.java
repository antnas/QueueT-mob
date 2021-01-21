package covidtracer;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CovidtracerApplicationTests {

  @Autowired
  TestRestTemplate testRestTemplate;

  @LocalServerPort
  int port;

  @Test
  void contextLoads() {
  }

  @Test
  @DisplayName("show starting page on '/'")
  void testIndexPage(){
    String html = testRestTemplate.getForObject("/", String.class);
    assertThat(html).contains("Kontaktlisten");
  }

  @Test
  @DisplayName("Add contact list.")
  void kontaktListeHinzufuegen(){
    String vorname = "Max";
    String nachname = "Mustermann";
    MultiValueMap<String, String> parts = new LinkedMultiValueMap<>();
    parts.put("vorname", List.of(vorname));
    parts.put("nachname", List.of(nachname));

    HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(parts, new HttpHeaders());
    ResponseEntity<String> responseEntity = testRestTemplate.postForEntity("/", request, String.class);

    assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.FOUND);

    ResponseEntity<String> getResultAfter = testRestTemplate.getForEntity("/", String.class);

    assertThat(getResultAfter.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(getResultAfter.getBody()).contains("Max");
    assertThat(getResultAfter.getBody()).contains("Mustermann");
  }

}
