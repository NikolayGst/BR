package com.niko.br.di;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

@Module
public class AppModule {

  private Context context;

  public AppModule(Context context) {
    this.context = context;
  }

  @Provides
  @Singleton
  SharedPreferences providesSharedPreferences(Context context) {
    return PreferenceManager.getDefaultSharedPreferences(context);
  }


  @Provides
  @Singleton
  Context provideContext() {
    return context;
  }

}
