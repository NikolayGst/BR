package com.niko.br.ui.common;

import com.arellomobile.mvp.MvpPresenter;
import com.arellomobile.mvp.MvpView;
import com.niko.br.App;
import com.niko.br.di.AppComponent;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

public abstract class BasePresenter<T extends MvpView> extends MvpPresenter<T> {

  private CompositeSubscription compositeSubscription;

  protected BasePresenter() {
    injectPresenter(App.getAppComponent());
    compositeSubscription = new CompositeSubscription();
  }

  public void unsubscribeOnDestroy(Subscription subscription) {
    compositeSubscription.add(subscription);
  }

  public abstract void injectPresenter(AppComponent component);

  public abstract void execute();

  @Override
  public void onDestroy() {
    super.onDestroy();
    compositeSubscription.clear();
  }


}
