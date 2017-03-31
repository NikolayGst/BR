package com.niko.br.ui.main.bmAndMejbank;


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
public class BmAndMbFragment extends BaseFragment {


  public BmAndMbFragment() {
    // Required empty public constructor
  }


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    return inflater.inflate(R.layout.fragment_bm_and_mb, container, false);
  }

  @Override
  public void injectFragment(AppComponent component) {
    component.inject(this);
  }

}
