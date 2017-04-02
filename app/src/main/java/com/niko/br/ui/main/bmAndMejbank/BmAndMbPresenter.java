package com.niko.br.ui.main.bmAndMejbank;

import static com.niko.br.Utils.BLACK_MARKET;
import static com.niko.br.Utils.MEJBANK;

import com.arellomobile.mvp.InjectViewState;
import com.niko.br.Utils;
import com.niko.br.di.AppComponent;
import com.niko.br.models.network.API;
import com.niko.br.ui.common.BasePresenter;
import javax.inject.Inject;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

@InjectViewState
public class BmAndMbPresenter extends BasePresenter<BmAndMbView> {

  @Inject
  API api;

  BmAndMbPresenter() {
    execute();
  }

  @Override
  public void injectPresenter(AppComponent component) {
    component.inject(this);
  }

  @Override
  public void execute() {
    getViewState().showProgressBar();

    unsubscribeOnDestroy(
        Observable.combineLatest(api.getBM(BLACK_MARKET), api.getMejBank(MEJBANK),
            (BmResponse, MbResponse) -> {
              if (BmResponse.isSuccessful() && MbResponse.isSuccessful()) {
                return Utils.formatBmAndMb(BmResponse.body(), MbResponse.body());
              }
              return null;
            }).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribe(o -> {
          getViewState().onSuccessLoadBmANdMb(o);
          getViewState().hideProgressBar();
        }, error -> {
          getViewState().onFailure(error);
          getViewState().hideProgressBar();
        }));

  }
}
