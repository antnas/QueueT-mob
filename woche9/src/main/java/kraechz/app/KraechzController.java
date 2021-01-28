package kraechz.app;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class KraechzController {

  @Autowired
  KraechzRepository service;


  @GetMapping("/")
  public String index(Model model) {
    List<Kraechz> kraechze = service.alle();
    model.addAttribute("kraechze",kraechze);
    return "index";
  }

  @GetMapping("/filter/{name}")
  public String filter(Model model, @PathVariable String name) {
    List<Kraechz> kraechze = service.filtereNachHandle(name);
    model.addAttribute("kraechze",kraechze);
    model.addAttribute("filter", true);
    return "index";
  }

  @PostMapping("/")
  public String add(Kraechz kraechz) {
    service.kraechze(kraechz);
    return "redirect:/";
  }


}
