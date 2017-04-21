package com.niko.br.models;

public class Bank {

  private String id;
  private String name;

  public Bank(String id, String name) {
    this.id = id;
    this.name = name;
  }

  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }

}
