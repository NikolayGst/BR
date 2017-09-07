package com.niko.br.ui.main.banks;

import static com.niko.br.Utils.BANK_ALPHABANK;
import static com.niko.br.Utils.BANK_AVAL;
import static com.niko.br.Utils.BANK_PRIVATBANK;
import static com.niko.br.Utils.BANK_SBERBANK;
import static com.niko.br.Utils.BANK_UKRSIBBBANK;

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
public class BanksPresenter extends BasePresenter<BanksView> {

  @Inject
  API api;

  BanksPresenter() {
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
        Observable.combineLatest(
            api.getBank(BANK_AVAL),
            api.getBank(BANK_UKRSIBBBANK),
            api.getBank(BANK_ALPHABANK),
            api.getBank(BANK_PRIVATBANK),
            api.getBank(BANK_SBERBANK),
            (t1, t2, t3, t4, t5) -> Utils
                .formatBanks(t1.body(), t2.body(), t3.body(), t4.body(), t5.body()))
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(result -> {
              getViewState().onSuccessLoadBanksData(result);
              getViewState().hideProgressBar();
            }, error -> {
              getViewState().onFailure(error);
              getViewState().hideProgressBar();
            }));
  }
}



