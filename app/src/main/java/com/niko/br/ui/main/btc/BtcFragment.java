package com.niko.br.ui.main.btc;


import static com.niko.br.Utils.BTC_KEY;
import static com.niko.br.Utils.SAVE_FRAGMENT;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.niko.br.R;
import com.niko.br.databinding.FragmentBtcBinding;
import com.niko.br.di.AppComponent;
import com.niko.br.models.gson.BTC.BTC;
import com.niko.br.models.gson.BTC.EUR;
import com.niko.br.models.gson.BTC.RUB;
import com.niko.br.models.gson.BTC.UAH;
import com.niko.br.models.gson.BTC.USD;
import com.niko.br.ui.common.BaseFragment;
import com.niko.br.ui.common.SimpleTextWatcher;
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

    return binding.getRoot();
  }

  private void initEditListener() {
    binding.editBTC.addTextChangedListener(new SimpleTextWatcher() {
      @Override
      public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        if (charSequence.length() == 0) {
          binding.editBTC.setText("0");
        } else {
          convertBTC(Float.parseFloat(charSequence.toString()));
        }
      }
    });
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
    showToast(getString(R.string.error_internet));
    binding.lrBTC.setVisibility(View.GONE);
    binding.contentError.lrError.setVisibility(View.VISIBLE);
    binding.contentError.refresh.setOnClickListener(view -> {
      binding.contentError.lrError.setVisibility(View.GONE);
      btcPresenter.execute();
    });
  }

  @Override
  public void onBtcLoadSuccess(BTC btc) {
    this.btc = btc;

    initEditListener();

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
