package com.niko.br.models.gson.BTC;


public class BTC {

  private USD usd;
  private EUR eur;
  private UAH uah;
  private RUB rub;

  public BTC(USD usd, EUR eur, UAH uah, RUB rub) {
    this.usd = usd;
    this.eur = eur;
    this.uah = uah;
    this.rub = rub;
  }

  public USD getUsd() {
    return usd;
  }

  public EUR getEur() {
    return eur;
  }

  public UAH getUah() {
    return uah;
  }

  public RUB getRub() {
    return rub;
  }
}
