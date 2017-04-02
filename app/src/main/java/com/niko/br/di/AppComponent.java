package com.niko.br.di;

import com.niko.br.ui.main.MainActivity;
import com.niko.br.ui.main.author.AuthorFragment;
import com.niko.br.ui.main.banks.BanksFragment;
import com.niko.br.ui.main.bmAndMejbank.BmAndMbFragment;
import com.niko.br.ui.main.bmAndMejbank.BmAndMbPresenter;
import com.niko.br.ui.main.btc.BtcFragment;
import com.niko.br.ui.main.nbu.NbuFragment;
import dagger.Component;
import javax.inject.Singleton;

@Singleton
@Component(modules = {NetworkModule.class, AppModule.class})
public interface AppComponent {

  void inject(NbuFragment nbuFragment);

  void inject(BanksFragment banksFragment);

  void inject(BmAndMbFragment bmAndMbFragment);

  void inject(BtcFragment btcFragment);

  void inject(BmAndMbPresenter bmAndMbPresenter);

  void inject(MainActivity mainActivity);

  void inject(AuthorFragment authorFragment);

}
