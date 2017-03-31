package com.niko.br.ui.main;

import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.Menu;
import android.view.Window;
import android.view.WindowManager;
import com.niko.br.R;
import com.niko.br.databinding.ActivityMainBinding;
import com.niko.br.ui.common.BaseActivity;
import com.niko.br.ui.common.BaseFragment;
import com.niko.br.ui.main.banks.BanksFragment;
import com.niko.br.ui.main.bmAndMejbank.BmAndMbFragment;
import com.niko.br.ui.main.btc.BtcFragment;
import com.niko.br.ui.main.nbu.NbuFragment;

public class MainActivity extends BaseActivity {

  private ActivityMainBinding binding;
  private NbuFragment nbuFragment;
  private BmAndMbFragment bmAndMbFragment;
  private BanksFragment banksFragment;
  private BtcFragment btcFragment;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    initFragments();

    binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

    initFirstFragment(savedInstanceState);

    initToolbar("Курси валют України");

    binding.contentMain.navigation.setOnNavigationItemSelectedListener(
        item -> {
          int color = 0;
          int colorDark = 0;
          switch (item.getItemId()) {
            case R.id.MbAndBM:
              showFragment(bmAndMbFragment);
              color = R.color.mb;
              colorDark = R.color.mbDark;
              break;
            case R.id.banks:
              showFragment(banksFragment);
              color = R.color.banks;
              colorDark = R.color.banksDark;
              break;
            case R.id.nbu:
              showFragment(nbuFragment);
              color = R.color.nbu;
              colorDark = R.color.nbuDark;
              break;
            case R.id.btc:
              showFragment(btcFragment);
              color = R.color.btc;
              colorDark = R.color.btcDark;
              break;
            case R.id.author:
              //showFragment(btcFragment);
              color = R.color.author;
              colorDark = R.color.authorDark;
              break;
          }
          changeNavigationAndToolbarColor(color,colorDark);
          return true;
        });

  }

  private void initFirstFragment(Bundle savedInstanceState) {
    binding.contentMain.navigation.setSelectedItemId(R.id.nbu);
    changeNavigationAndToolbarColor(R.color.nbu,R.color.nbuDark);

    if (savedInstanceState == null) {
      getSupportFragmentManager().beginTransaction()
          .replace(binding.contentMain.content.getId(), nbuFragment)
          .commit();
    }
  }

  private void changeNavigationAndToolbarColor(int color, int colorDark) {
    binding.contentMain.navigation.setBackgroundColor(ContextCompat.getColor(this,color));
    binding.toolbar.setBackgroundColor(ContextCompat.getColor(this,color));
    if (Build.VERSION.SDK_INT >= 21) {
      Window window = getWindow();
      window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
      window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
      window.setStatusBarColor(ContextCompat.getColor(this,colorDark));
    }
  }

  public void showFragment(BaseFragment fragment) {
    if (fragment != null && !fragment.isFragmentUIActive()) {
      getSupportFragmentManager().beginTransaction()
          .replace(binding.contentMain.content.getId(), fragment)
          .commit();
    }
  }

  private void initFragments() {
    nbuFragment = new NbuFragment();
    bmAndMbFragment = new BmAndMbFragment();
    banksFragment = new BanksFragment();
    btcFragment = new BtcFragment();
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.main, menu);
    return true;
  }
}
