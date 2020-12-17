package com.example.zinsrechner;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.sql.SQLOutput;

@Controller
public class ZinsController {

  @GetMapping("/")
  public String zinsForm(Model m) {
    m.addAttribute("zinsrechner",new ZinsRechner(0.0,0,0.0));
    return "zinsform";
  }

  @PostMapping("/")
  public String zinsBerechnen(ZinsRechner zinsrechner, Model m, boolean showTable) {
    m.addAttribute("zinsrechner", zinsrechner);
    m.addAttribute("showTable", showTable);
    return "zinsform";
  }

}
