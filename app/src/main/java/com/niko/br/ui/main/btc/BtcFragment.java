package com.niko.br.ui.main.btc;


import static com.niko.br.Utils.BTC_KEY;
import static com.niko.br.Utils.SAVE_FRAGMENT;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.niko.br.R;
import com.niko.br.di.AppComponent;
import com.niko.br.ui.common.BaseFragment;
import javax.inject.Inject;

/**
 * A simple {@link Fragment} subclass.
 */
public class BtcFragment extends BaseFragment implements BtcView {

  @Inject
  SharedPreferences sharedPreferences;

  @InjectPresenter
  BtcPresenter btcPresenter;

  public BtcFragment() {
    // Required empty public constructor
  }


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_btc, container, false);
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
  public void onBtcLoadSuccess() {

  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    sharedPreferences.edit().putString(SAVE_FRAGMENT, BTC_KEY).apply();
  }
}
