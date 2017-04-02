package com.niko.br.ui.main.btc;

import com.arellomobile.mvp.viewstate.strategy.SingleStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.niko.br.models.gson.BTC.BTC;
import com.niko.br.ui.common.BaseMvpView;

@StateStrategyType(SingleStateStrategy.class)
 interface BtcView extends BaseMvpView {

  void onBtcLoadSuccess(BTC btc);

}
