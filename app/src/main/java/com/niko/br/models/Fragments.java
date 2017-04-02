package com.niko.br.models;

import com.niko.br.ui.common.BaseFragment;

public class Fragments {

  private int color;
  private int colorDark;
  private int idBottomMenu;
  private BaseFragment baseFragment;

  public Fragments(int color, int colorDark, BaseFragment baseFragment) {
    this.color = color;
    this.colorDark = colorDark;
    this.baseFragment = baseFragment;
  }

  public Fragments(int color, int colorDark, int idBottomMenu, BaseFragment baseFragment) {
    this.color = color;
    this.colorDark = colorDark;
    this.baseFragment = baseFragment;
    this.idBottomMenu = idBottomMenu;
  }

  public int getColor() {
    return color;
  }

  public int getColorDark() {
    return colorDark;
  }

  public BaseFragment getFragment() {
    return baseFragment;
  }

  public int getIdBottomMenu() {
    return idBottomMenu;
  }
}
