package com.niko.br.ui.main.nbu;

import com.arellomobile.mvp.viewstate.strategy.SingleStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.niko.br.models.gson.NBU;
import com.niko.br.ui.common.BaseMvpView;
import java.util.List;

@StateStrategyType(SingleStateStrategy.class)
public interface NbuView extends BaseMvpView {

  void onNbuDataLoadSuccess(List<NBU> nbuList);

  void onFormatFullNBUDataSuccess(List<NBU> nbuList);

}
