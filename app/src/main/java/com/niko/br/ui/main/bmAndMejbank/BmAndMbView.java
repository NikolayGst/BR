package com.niko.br.ui.main.bmAndMejbank;

import com.arellomobile.mvp.viewstate.strategy.SingleStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.niko.br.ui.common.BaseMvpView;
import java.util.Map;

@StateStrategyType(SingleStateStrategy.class)
public interface BmAndMbView extends BaseMvpView {

  void onSuccessLoadBmANdMb(Map<String, Object> list);

}
