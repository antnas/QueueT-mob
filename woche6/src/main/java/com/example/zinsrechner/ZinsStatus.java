package com.example.zinsrechner;

import lombok.Data;

@Data
public class ZinsStatus {

  final int jahr;
  final double anfangsKapital;
  final double endKapital;
  final double zinsen;


}
