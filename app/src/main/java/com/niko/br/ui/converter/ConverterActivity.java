package com.niko.br.ui.converter;


import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import com.niko.br.R;
import com.niko.br.Utils;
import com.niko.br.databinding.ActivityConverterBinding;
import com.niko.br.di.AppComponent;
import com.niko.br.models.gson.BaseTypeRate;
import com.niko.br.ui.common.BaseActivity;
import java.util.Locale;

public class ConverterActivity extends BaseActivity {

  ActivityConverterBinding binding;

  private BaseTypeRate usd;
  private BaseTypeRate eur;
  private BaseTypeRate rub;
  private String title;
  private Bundle extras;
  private String mainRate;
  private boolean isBuy;
  private BaseTypeRate buyRate;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    binding = DataBindingUtil.setContentView(this, R.layout.activity_converter);

    extras = getIntent().getExtras();

    changeColorToolbarAndStatusBar();

    if (extras != null) {
      title = extras.getString("title");
      isBuy = extras.getBoolean("isBuy");
      mainRate = extras.getString("mainRate");
      if (isBuy) {
        buyRate = extras.getParcelable("buyRate");
        binding.cardBuy.setVisibility(View.VISIBLE);
        binding.cardSale.setVisibility(View.GONE);
        binding.txt.setText(mainRate);
        initToolbar(title + " -> Купити -> " + mainRate);
        convertBuy(1);
      } else {
        usd = extras.getParcelable("usd");
        eur = extras.getParcelable("eur");
        rub = extras.getParcelable("rub");
        binding.txt.setText(mainRate);
        initToolbar(title + " -> Продати -> " + mainRate);
        convertSale(1);
      }
      initEditListener();
    }


  }


  @Override
  public void injectActivity(AppComponent component) {

  }

  private void changeColorToolbarAndStatusBar() {
    binding.toolbar.setBackgroundColor(ContextCompat.getColor(this, R.color.converter));
    if (Build.VERSION.SDK_INT >= 21) {
      Window window = getWindow();
      window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
      window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
      window.setStatusBarColor(ContextCompat.getColor(this, R.color.converterDark));
    }
  }

  private void initEditListener() {
    binding.editCount.addTextChangedListener(new TextWatcher() {
      @Override
      public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

      }

      @Override
      public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        if (charSequence.length() == 0) {
          binding.editCount.setText("0");
          if (isBuy) {
            convertBuy(0);
          } else {
            convertSale(0);
          }
        } else {
          if (isBuy) {
            convertBuy(Float.parseFloat(charSequence.toString()));
          } else {
            convertSale(Float.parseFloat(charSequence.toString()));
          }
        }
      }

      @Override
      public void afterTextChanged(Editable editable) {

      }
    });
  }

  private void convertBuy(float value) {
    float result = Utils.convert(value, buyRate.getSale(), 1);
    binding.txtBuy.setText(String.format(Locale.ENGLISH, "%.3f", result));
  }

  private void convertSale(float value) {
    float resultUsd = 0;
    float resultEur = 0;
    float resultUah = 0;
    float resultRub = 0;

    switch (mainRate) {
      case "USD":
        resultUsd = value;
        resultEur = Utils.convert(value, usd.getBuy(), eur.getSale());
        resultUah = Utils.convert(value, usd.getBuy(), 1);
        resultRub = Utils.convert(value, usd.getBuy(), rub.getSale());
        break;
      case "EUR":
        resultUsd = Utils.convert(value, eur.getBuy(), usd.getSale());
        resultEur = value;
        resultUah = Utils.convert(value, eur.getBuy(), 1);
        resultRub = Utils.convert(value, eur.getBuy(), rub.getSale());
        break;
      case "RUB":
        resultUsd = Utils.convert(value, rub.getBuy(), usd.getSale());
        resultEur = Utils.convert(value, rub.getBuy(), eur.getSale());
        resultUah = Utils.convert(value, rub.getBuy(), 1);
        resultRub = value;
        break;
    }

    binding.txtUAH.setText(String.format(Locale.ENGLISH, "%.3f", resultUah));
    binding.txtRUB.setText(String.format(Locale.ENGLISH, "%.3f", resultRub));
    binding.txtUSD.setText(String.format(Locale.ENGLISH, "%.3f", resultUsd));
    binding.txtEUR.setText(String.format(Locale.ENGLISH, "%.3f", resultEur));
  }
}