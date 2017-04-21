package com.niko.br.di;

import com.niko.br.ui.main.MainActivity;
import com.niko.br.ui.main.author.AuthorFragment;
import com.niko.br.ui.main.banks.BanksFragment;
import com.niko.br.ui.main.banks.BanksPresenter;
import com.niko.br.ui.main.bmAndMejbank.BmAndMbFragment;
import com.niko.br.ui.main.bmAndMejbank.BmAndMbPresenter;
import com.niko.br.ui.main.btc.BtcFragment;
import com.niko.br.ui.main.btc.BtcPresenter;
import com.niko.br.ui.main.nbu.NbuFragment;
import com.niko.br.ui.main.nbu.NbuPresenter;
import dagger.Component;
import javax.inject.Singleton;

@Singleton
@Component(modules = {NetworkModule.class, AppModule.class})
public interface AppComponent {

  void inject(MainActivity mainActivity);


  void inject(BmAndMbFragment bmAndMbFragment);

  void inject(BanksFragment banksFragment);

  void inject(NbuFragment nbuFragment);

  void inject(BtcFragment btcFragment);

  void inject(AuthorFragment authorFragment);


  void inject(BmAndMbPresenter bmAndMbPresenter);

  void inject(BtcPresenter btcPresenter);

  void inject(NbuPresenter nbuPresenter);

  void inject(BanksPresenter banksPresenter);
}
