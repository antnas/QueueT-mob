package projektor.web;

import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import projektor.projekt.Projekt;
import projektor.projekt.ProjektRepo;
import projektor.projekt.ProjektRepository;
import projektor.projekt.Zeitraum;

@Controller
public class ProjektController {

  @Autowired
  ProjektRepo repo;

  @GetMapping("/")
  public String index(Model model, String person, String startzeitpunkt, String endzeitpunkt) {
    if (person != null) {
      model.addAttribute("projekte", repo.findProjektByPerson(person));
    }
    else if(startzeitpunkt!=null && endzeitpunkt != null) {
      LocalDate von = LocalDate.parse(startzeitpunkt);
      LocalDate bis = LocalDate.parse(endzeitpunkt);
      model.addAttribute("projekte", repo.findProjektByZeitraum_VonAndZeitraum_Bis(von, bis));
    }
    else
    model.addAttribute("projekte", repo.findAll());
    return "liste";
  }

  @GetMapping("/details/{id}")
  public String details(Model model, @PathVariable long id) {
    Projekt blub = repo.findProjektById(id);
    System.out.println(blub);
    model.addAttribute("projekt", blub);
    return "details";
  }
/*
  @PostMapping("/edit/{id}")
  public String edit(@PathVariable long id, String beschreibung,
                     String startzeitpunkt, String endzeitpunkt) {
    Projekt blub = repo.blub(id);
    blub.setBeschreibung(beschreibung);
    LocalDate von = LocalDate.parse(startzeitpunkt);
    LocalDate bis = LocalDate.parse(endzeitpunkt);
    blub.setZeitraum(von, bis);
    repo.plonk(blub);
    return "redirect:/";
  }

  @PostMapping("/addperson/{id}")
  public String addPerson(@PathVariable long id, String person) {
    Projekt blub = repo.blub(id);
    blub.addPerson(person);
    repo.plonk(blub);
    return "redirect:/";
  }

*/
}
