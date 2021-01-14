package covidtracer.controller;

import covidtracer.services.ReportGenerator;
import covidtracer.model.Index;
import covidtracer.model.KontaktListe;
import covidtracer.model.Kontaktperson;

import java.time.LocalDate;
import java.util.List;

import covidtracer.services.DBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.HttpClientErrorException;

@Controller
public class Webpage {

  @Autowired
  DBService dbService;

  @Autowired
  ReportGenerator reportGenerator;

  @GetMapping("/")
  public String index(Model model) {
    List<KontaktListe> listen = dbService.findAll();
    model.addAttribute("listen", listen);
    return "index";
  }

  @GetMapping("/liste/{id}")
  public String details(@PathVariable("id") long id, Model model) {
    KontaktListe liste = dbService.findById(id).orElseThrow(() ->
        new HttpClientErrorException(HttpStatus.NOT_FOUND,
            "Keine Liste mit id " + id + " vorhanden."));
    model.addAttribute("liste", liste);
    return "details";
  }

  @PostMapping("/")
  public String erzeugeListe(String nachname, String vorname) {
    KontaktListe liste = new KontaktListe();
    liste.setIndex(new Index(nachname, vorname, LocalDate.now()));
    dbService.save(liste);
    return "redirect:/";
  }

  @PostMapping("/liste/{id}")
  public String kontaktpersonHinzufuegen(@PathVariable("id") long id,
                                         Kontaktperson kontaktperson) {
    KontaktListe liste = dbService.findById(id).orElseThrow(() ->
        new HttpClientErrorException(HttpStatus.NOT_FOUND,
            "Keine Liste mit id " + id + " vorhanden."));
    liste.addKontakt(kontaktperson);
    dbService.save(liste);
    return "redirect:/liste/" + id;
  }

  @PostMapping("/remove/from/{id}")
  public String kontaktpersonEntfernen(@PathVariable("id") long id,
                                       Kontaktperson kontaktperson) {
    KontaktListe liste = dbService.findById(id).orElseThrow(() ->
        new HttpClientErrorException(HttpStatus.NOT_FOUND,
            "Keine Liste mit id " + id + " vorhanden."));
    liste.removeKontakt(kontaktperson);
    dbService.save(liste);
    return "redirect:/liste/" + id;
  }

  @GetMapping("/report")
  public String report(Model model) {
    List<KontaktListe> listen = dbService.findAll();
    model.addAttribute("alter", reportGenerator.generateReport(listen));
    return "report";
  }
}
