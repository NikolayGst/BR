package com.niko.br.di;

import com.niko.br.ui.common.BaseFragment;
import com.niko.br.ui.main.nbu.NbuFragment;
import dagger.Component;
import javax.inject.Singleton;

@Singleton
@Component(modules = {NetworkModule.class, AppModule.class})
public interface AppComponent {

  void inject(BaseFragment baseFragment);

  void inject(NbuFragment nbuFragment);
}
