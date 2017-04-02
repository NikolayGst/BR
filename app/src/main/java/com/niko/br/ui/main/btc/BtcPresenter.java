package com.niko.br.ui.main.btc;

import static com.niko.br.Utils.BTC_RATE;
import static com.niko.br.Utils.BTC_RUB;
import static com.niko.br.Utils.BTC_UAH;

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
public class BtcPresenter extends BasePresenter<BtcView> {

  @Inject
  API api;

  BtcPresenter() {
    execute();
  }

  @Override
  public void injectPresenter(AppComponent component) {
    component.inject(this);
  }

  @Override
  public void execute() {
    getViewState().showProgressBar();

    Observable.combineLatest(api.getBTCCurrentRate(BTC_RATE), api.getBTCUah(BTC_UAH),
        api.getBTCRub(BTC_RUB), (usdAndEurResponse, uahResponse, rubResponse) -> {
          {
            if (usdAndEurResponse.isSuccessful() && uahResponse.isSuccessful() && rubResponse
                .isSuccessful()) {
              return Utils.formatBTC(usdAndEurResponse, uahResponse, rubResponse);
            }
          }
          return null;
        })
        .subscribeOn(Schedulers.newThread())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(result -> {
          if (result != null) {
            getViewState().onBtcLoadSuccess(result);
            getViewState().hideProgressBar();
          }
        }, error -> {
          getViewState().onFailure(error);
          getViewState().hideProgressBar();
        });

  }

}
