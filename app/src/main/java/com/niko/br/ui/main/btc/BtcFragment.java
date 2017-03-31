package com.niko.br.ui.main.btc;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.niko.br.R;
import com.niko.br.di.AppComponent;
import com.niko.br.ui.common.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class BtcFragment extends BaseFragment implements BtcView{

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
    getProgressBar().show();
  }

  @Override
  public void hideProgressBar() {
    getProgressBar().hide();
  }

  @Override
  public void showToast(String msg) {

  }

  @Override
  public void onBtcLoadSuccess() {

  }
}
