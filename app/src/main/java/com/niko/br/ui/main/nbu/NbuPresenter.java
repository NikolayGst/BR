package com.niko.br.ui.main.nbu;

import static com.niko.br.Utils.NBU_URL;

import com.arellomobile.mvp.InjectViewState;
import com.niko.br.Utils;
import com.niko.br.di.AppComponent;
import com.niko.br.models.gson.NBU;
import com.niko.br.models.network.API;
import com.niko.br.ui.common.BasePresenter;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

@InjectViewState
public class NbuPresenter extends BasePresenter<NbuView> {

  @Inject
  API api;

  private List<NBU> fullNBUList = new ArrayList<>();

  NbuPresenter() {
    execute();
  }

  @Override
  public void injectPresenter(AppComponent component) {
    component.inject(this);
  }

  @Override
  public void execute() {
    getViewState().showProgressBar();
    unsubscribeOnDestroy(api.getNBUBank(NBU_URL)
        .subscribeOn(Schedulers.newThread())
        .observeOn(AndroidSchedulers.mainThread())
        .map(listResponse -> {
          if (listResponse.isSuccessful()) {
            fullNBUList.addAll(listResponse.body());
            return Utils.formatSmallNBUList(listResponse.body());
          } else {
            return null;
          }
        })
        .subscribe(list -> {
          getViewState().hideProgressBar();
          getViewState().onNbuDataLoadSuccess(list);
        }, error -> {
          getViewState().hideProgressBar();
          getViewState().onFailure(error);
        }));
  }

  void showOtherRate() {
    getViewState().onFormatFullNBUDataSuccess(Utils.formatFullNBUList(fullNBUList));
  }
}
