package com.niko.br.ui.common;


import com.arellomobile.mvp.MvpView;

public interface BaseMvpView extends MvpView {

  void showProgressBar();

  void hideProgressBar();

  void onFailure(Throwable throwable);

}
