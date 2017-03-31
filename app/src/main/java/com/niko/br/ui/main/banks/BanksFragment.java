package com.niko.br.ui.main.banks;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.niko.br.R;
import com.niko.br.di.AppComponent;
import com.niko.br.ui.common.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class BanksFragment extends BaseFragment {

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

}
