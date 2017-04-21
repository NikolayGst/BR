package com.niko.br.ui.main.banks.adapter;

import static com.niko.br.Utils.getFlag;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.View;
import com.bignerdranch.expandablerecyclerview.ChildViewHolder;
import com.niko.br.R;
import com.niko.br.databinding.RateBankItemBinding;
import com.niko.br.models.gson.CashexRate;
import java.util.Locale;

public class RateHolder extends ChildViewHolder {

  private final int green;
  RateBankItemBinding bind;

  public RateHolder(@NonNull View itemView) {
    super(itemView);
    bind = RateBankItemBinding.bind(itemView);
    green = ContextCompat.getColor(bind.getRoot().getContext(), R.color.green);
  }

  public void bindData(CashexRate item) {
    changeColor(item);
    bind.flag.setImageResource(getFlag(item.getCurrency()));
    bind.txtCurrency.setText(item.getCurrency());
    bind.txtBuy.setText(String.format(Locale.ENGLISH, "%.3f", item.getBuy()));
    bind.txtBuyDelta.setText(String.format(Locale.ENGLISH, "%.3f", item.getBuyDelta()));
    bind.txtSale.setText(String.format(Locale.ENGLISH, "%.3f", item.getSale()));
    bind.txtSaleDelta.setText(String.format(Locale.ENGLISH, "%.3f", item.getSaleDelta()));

  }

  private void changeColor(CashexRate item) {
    if (item.getBuyDelta() > 0 && !Float.toString(item.getBuyDelta()).equals("0.0")) {
      bind.txtBuyDelta.setTextColor(green);
    } else if (item.getBuyDelta() < 0 && !Float.toString(item.getBuyDelta()).equals("0.0")) {
      bind.txtBuyDelta.setTextColor(Color.RED);
    } else {
      bind.txtBuyDelta.setTextColor(Color.BLACK);
    }

    if (item.getSaleDelta() > 0 && !Float.toString(item.getSaleDelta()).equals("0.0")) {
      bind.txtSaleDelta.setTextColor(green);
    } else if (item.getSaleDelta() < 0 && !Float.toString(item.getSaleDelta()).equals("0.0")) {
      bind.txtSaleDelta.setTextColor(Color.RED);
    } else {
      bind.txtSaleDelta.setTextColor(Color.BLACK);
    }
  }

}
