package com.niko.br.ui.main.banks;


import static com.niko.br.Utils.BANKS_KEY;
import static com.niko.br.Utils.SAVE_FRAGMENT;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.niko.br.R;
import com.niko.br.di.AppComponent;
import com.niko.br.ui.common.BaseFragment;
import javax.inject.Inject;

/**
 * A simple {@link Fragment} subclass.
 */
public class BanksFragment extends BaseFragment {

  @Inject
  SharedPreferences sharedPreferences;

  public BanksFragment() {
    // Required empty public constructor
  }


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_banks, container, false);
  }

  @Override
  public void injectFragment(AppComponent component) {
    component.inject(this);
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    sharedPreferences.edit().putString(SAVE_FRAGMENT, BANKS_KEY).apply();
  }
}
