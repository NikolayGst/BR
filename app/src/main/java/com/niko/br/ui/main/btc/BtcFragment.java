package com.niko.br.ui.main.btc;


import static com.niko.br.Utils.BTC_KEY;
import static com.niko.br.Utils.SAVE_FRAGMENT;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.niko.br.databinding.FragmentBtcBinding;
import com.niko.br.di.AppComponent;
import com.niko.br.models.gson.BTC.BTC;
import com.niko.br.models.gson.BTC.EUR;
import com.niko.br.models.gson.BTC.RUB;
import com.niko.br.models.gson.BTC.UAH;
import com.niko.br.models.gson.BTC.USD;
import com.niko.br.ui.common.BaseFragment;
import java.util.Locale;
import javax.inject.Inject;


public class BtcFragment extends BaseFragment implements BtcView {

  @Inject
  SharedPreferences sharedPreferences;

  @InjectPresenter
  BtcPresenter btcPresenter;

  private FragmentBtcBinding binding;
  private BTC btc;

  public BtcFragment() {
  }


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    binding = FragmentBtcBinding.inflate(inflater, container, false);

    binding.editBTC.addTextChangedListener(new TextWatcher() {
      @Override
      public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

      }

      @Override
      public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        if (charSequence.length() == 0) {
          binding.editBTC.setText("0");
        } else {
          convertBTC(Float.parseFloat(charSequence.toString()));
        }
      }

      @Override
      public void afterTextChanged(Editable editable) {

      }
    });

    return binding.getRoot();
  }

  private void convertBTC(float v) {

    float usd = v * btc.getUsd().getRateFloat();
    float eur = v * btc.getEur().getRateFloat();
    float uah = v * btc.getUah().getRateFloat();
    float rub = v * btc.getRub().getRateFloat();

    binding.txtUAH.setText(String.format(Locale.ENGLISH, "%.3f", uah));
    binding.txtRUB.setText(String.format(Locale.ENGLISH, "%.3f", rub));
    binding.txtUSD.setText(String.format(Locale.ENGLISH, "%.3f", usd));
    binding.txtEUR.setText(String.format(Locale.ENGLISH, "%.3f", eur));
  }

  @Override
  public void injectFragment(AppComponent component) {
    component.inject(this);
  }

  @Override
  public void showProgressBar() {
    progressBar.setVisibility(View.VISIBLE);
  }

  @Override
  public void hideProgressBar() {
    progressBar.setVisibility(View.INVISIBLE);
  }

  @Override
  public void onFailure(Throwable throwable) {
    showToast(throwable.getMessage());
  }

  @Override
  public void onBtcLoadSuccess(BTC btc) {
    this.btc = btc;

    UAH uah = btc.getUah();
    RUB rub = btc.getRub();
    USD usd = btc.getUsd();
    EUR eur = btc.getEur();

    if (uah != null && rub != null && usd != null && eur != null) {
      binding.lrBTC.setVisibility(View.VISIBLE);
      binding.txtUAH.setText(String.format(Locale.ENGLISH, "%.3f", uah.getRateFloat()));
      binding.txtRUB.setText(String.format(Locale.ENGLISH, "%.3f", rub.getRateFloat()));
      binding.txtUSD.setText(String.format(Locale.ENGLISH, "%.3f", usd.getRateFloat()));
      binding.txtEUR.setText(String.format(Locale.ENGLISH, "%.3f", eur.getRateFloat()));
    }
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    sharedPreferences.edit().putString(SAVE_FRAGMENT, BTC_KEY).apply();
  }
}
