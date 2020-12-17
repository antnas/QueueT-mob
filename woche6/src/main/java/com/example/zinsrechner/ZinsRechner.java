package com.example.zinsrechner;

import java.util.ArrayList;
import java.util.List;

import lombok.AccessLevel;
import lombok.Getter;
//@Getter(AccessLevel.PUBLIC)
public class ZinsRechner {

  Double anfangskapital;
  Integer laufzeit;
  Double zinssatz;

  public ZinsRechner(Double anfangskapital, Integer laufzeit, Double zinssatz) {
    this.anfangskapital = anfangskapital;
    this.laufzeit = laufzeit;
    this.zinssatz = zinssatz;
  }

  public double getEndkapital(){
    double tempKapital = anfangskapital;
    for(int i = 1; i <= laufzeit; i++){
      tempKapital = tempKapital *(1+zinssatz);
    }
    return tempKapital;
  }

  List<ZinsStatus> getZinsStatusListe(){

    List<ZinsStatus> liste = new ArrayList();
    double tempKapital = anfangskapital;
    for(int i = 1; i <= laufzeit; i++){

      double tempAnfangskapital = tempKapital;
      double zinsen = tempKapital * zinssatz;
      tempKapital *= (1+zinssatz);

      liste.add(new ZinsStatus(i, tempAnfangskapital, tempKapital, zinsen ));
    }
    return liste;
  }

  public int getLaufzeit() {
    return laufzeit;
  }

  public double getAnfangskapital() {
    return anfangskapital;
  }

  public double getZinssatz() {
    return zinssatz;
  }
}
