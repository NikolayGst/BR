package com.niko.br.ui.main.banks.adapter;

import com.bignerdranch.expandablerecyclerview.model.Parent;
import com.niko.br.models.gson.CashexRate;
import java.util.List;

public class BankTitle implements Parent<CashexRate> {

  private String title;
  private List<CashexRate> list;
  private int resId;

  public BankTitle(String title, List<CashexRate> list, int resId) {
    this.title = title;
    this.list = list;
    this.resId = resId;
  }

  @Override
  public List<CashexRate> getChildList() {
    return list;
  }

  @Override
  public boolean isInitiallyExpanded() {
    return false;
  }

  public String getTitle() {
    return title;
  }

  public int getResId() {
    return resId;
  }
}
