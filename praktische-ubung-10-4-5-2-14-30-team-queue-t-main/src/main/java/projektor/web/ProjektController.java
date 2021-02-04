package projektor.web;

import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import projektor.projekt.Projekt;
import projektor.projekt.ProjektRepository;

@Controller
public class ProjektController {

  @Autowired
  ProjektRepository repo;

  @GetMapping("/")
  public String index(Model model, String person, String startzeitpunkt, String endzeitpunkt) {
    if (person != null) {
      model.addAttribute("projekte", repo.blahPerson(person));
    }
    else if(startzeitpunkt!=null && endzeitpunkt != null) {
      LocalDate von = LocalDate.parse(startzeitpunkt);
      LocalDate bis = LocalDate.parse(endzeitpunkt);
      model.addAttribute("projekte", repo.blahZeit(von, bis));
    }
    else
    model.addAttribute("projekte", repo.blah());
    return "liste";
  }

  @GetMapping("/details/{id}")
  public String details(Model model, @PathVariable long id) {
    Projekt blub = repo.blub(id);
    model.addAttribute("projekt", blub);
    return "details";
  }

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


}
