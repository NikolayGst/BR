package com.niko.br.ui.main.banks;

import com.arellomobile.mvp.viewstate.strategy.SingleStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.niko.br.ui.common.BaseMvpView;
import com.niko.br.ui.main.banks.adapter.BankTitle;
import java.util.List;

@StateStrategyType(SingleStateStrategy.class)
public interface BanksView extends BaseMvpView {
  void onSuccessLoadBanksData(List<BankTitle> list);
}
