package com.niko.br.ui.main.btc;

import android.os.Handler;
import com.arellomobile.mvp.InjectViewState;
import com.niko.br.ui.common.BasePresenter;

@InjectViewState
public class BtcPresenter extends BasePresenter<BtcView> {

  public BtcPresenter() {
    execute();
  }

  @Override
  public void execute() {
    getViewState().showProgressBar();
    new Handler().postDelayed(() -> {
      getViewState().onBtcLoadSuccess();
      getViewState().hideProgressBar();
    }, 4000);
  }

}
