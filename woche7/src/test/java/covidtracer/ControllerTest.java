package covidtracer;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import covidtracer.controller.Webpage;
import covidtracer.model.Index;
import covidtracer.model.KontaktListe;
import covidtracer.model.Kontaktperson;
import covidtracer.persistence.KontaktListeRepository;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.HttpClientErrorException;

@WebMvcTest(Webpage.class)
public class ControllerTest {

  @Autowired
  MockMvc mockMvc;

  @SpyBean
  KontaktListeRepository kontaktListeRepository;

  @BeforeEach
  public void setupMocks() {
    KontaktListe kontaktListe1 = new KontaktListe();
    kontaktListe1.setIndex(new Index("Mustermann", "Max", LocalDate.now()));
    kontaktListe1.addKontakt(new Kontaktperson("Mustermann", "Max", "JOJO"));
    Optional<KontaktListe> kontaktListe = Optional.of(kontaktListe1);

    when(kontaktListeRepository.findById(1l)).thenReturn(kontaktListe);
  }

  @Test
  public void defaultRequestTest() throws Exception {
    this.mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk())
        .andExpect(content().string(containsString("Kontaktlisten")));
  }

  @Test
  public void detailsRequestTest() throws Exception {
    this.mockMvc.perform(get("/liste/1")).andDo(print()).andExpect(status().isOk());
  }
}
