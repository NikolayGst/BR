package com.niko.br.ui.main;

import static com.niko.br.Utils.AUTHOR_KEY;
import static com.niko.br.Utils.BANKS_KEY;
import static com.niko.br.Utils.BM_AND_MB_KEY;
import static com.niko.br.Utils.BTC_KEY;
import static com.niko.br.Utils.NBU_KEY;
import static com.niko.br.Utils.SAVE_FRAGMENT;

import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.Window;
import android.view.WindowManager;
import com.niko.br.R;
import com.niko.br.databinding.ActivityMainBinding;
import com.niko.br.di.AppComponent;
import com.niko.br.models.Fragments;
import com.niko.br.ui.common.BaseActivity;
import com.niko.br.ui.common.BaseFragment;
import com.niko.br.ui.main.author.AuthorFragment;
import com.niko.br.ui.main.banks.BanksFragment;
import com.niko.br.ui.main.bmAndMejbank.BmAndMbFragment;
import com.niko.br.ui.main.btc.BtcFragment;
import com.niko.br.ui.main.nbu.NbuFragment;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.inject.Inject;

public class MainActivity extends BaseActivity {

  @Inject
  SharedPreferences sharedPreferences;

  private ActivityMainBinding binding;
  private NbuFragment nbuFragment;
  private BmAndMbFragment bmAndMbFragment;
  private BanksFragment banksFragment;
  private BtcFragment btcFragment;
  private AuthorFragment authorFragment;
  private Map<Integer, Fragments> fragmentsById = new LinkedHashMap<>();
  private Map<String, Fragments> fragmentsByKey = new LinkedHashMap<>();


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    initFragments();

    binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

    initFirstFragment(savedInstanceState);

    initToolbar("Курси валют України");

    binding.contentMain.navigation.setOnNavigationItemSelectedListener(
        item -> {
          Fragments fragment = fragmentsById.get(item.getItemId());
          int color = fragment.getColor();
          int colorDark = fragment.getColorDark();
          showFragment(fragment.getFragment());
          changeNavigationAndToolbarColor(color, colorDark);
          return true;
        });

  }

  @Override
  public void injectActivity(AppComponent component) {
    component.inject(this);
  }

  private void initFirstFragment(Bundle savedInstanceState) {
    //Вариант по умолчанию
    Fragments saveFragment;
    BaseFragment showedFragment = nbuFragment;
    int color = R.color.nbu;
    int colorDark = R.color.nbuDark;
    int idBottomMenu = R.id.nbu;

    String savedFragment = sharedPreferences.getString(SAVE_FRAGMENT, null);
    if (savedFragment != null) {
      saveFragment = fragmentsByKey.get(savedFragment);
      color = saveFragment.getColor();
      colorDark = saveFragment.getColorDark();
      showedFragment = saveFragment.getFragment();
      idBottomMenu = saveFragment.getIdBottomMenu();
    }

    binding.contentMain.navigation.setSelectedItemId(idBottomMenu);
    changeNavigationAndToolbarColor(color, colorDark);

    if (savedInstanceState == null) {
      getSupportFragmentManager().beginTransaction()
          .replace(binding.contentMain.content.getId(), showedFragment)
          .commit();
    }
  }

  private void changeNavigationAndToolbarColor(int color, int colorDark) {
    binding.contentMain.navigation.setBackgroundColor(ContextCompat.getColor(this, color));
    binding.toolbar.setBackgroundColor(ContextCompat.getColor(this, color));
    if (Build.VERSION.SDK_INT >= 21) {
      Window window = getWindow();
      window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
      window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
      window.setStatusBarColor(ContextCompat.getColor(this, colorDark));
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
    fragmentsById.put(R.id.nbu, new Fragments(R.color.nbu, R.color.nbuDark, nbuFragment));
    fragmentsByKey.put(NBU_KEY, new Fragments(R.color.nbu, R.color.nbuDark, R.id.nbu, nbuFragment));
    bmAndMbFragment = new BmAndMbFragment();
    fragmentsById.put(R.id.BmAndMb, new Fragments(R.color.mb, R.color.mbDark, bmAndMbFragment));
    fragmentsByKey.put(BM_AND_MB_KEY,
        new Fragments(R.color.mb, R.color.mbDark, R.id.BmAndMb, bmAndMbFragment));
    banksFragment = new BanksFragment();
    fragmentsById.put(R.id.banks, new Fragments(R.color.banks, R.color.banksDark, banksFragment));
    fragmentsByKey
        .put(BANKS_KEY, new Fragments(R.color.banks, R.color.banksDark, R.id.banks, banksFragment));
    btcFragment = new BtcFragment();
    fragmentsById.put(R.id.btc, new Fragments(R.color.btc, R.color.btcDark, btcFragment));
    fragmentsByKey.put(BTC_KEY, new Fragments(R.color.btc, R.color.btcDark, R.id.btc, btcFragment));
    authorFragment = new AuthorFragment();
    fragmentsById
        .put(R.id.author, new Fragments(R.color.author, R.color.authorDark, authorFragment));
    fragmentsByKey
        .put(AUTHOR_KEY,
            new Fragments(R.color.author, R.color.authorDark, R.id.author, authorFragment));
  }

}
