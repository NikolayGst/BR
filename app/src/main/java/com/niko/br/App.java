package com.niko.br;


import android.app.Application;
import com.niko.br.di.AppComponent;
import com.niko.br.di.AppModule;
import com.niko.br.di.DaggerAppComponent;
import com.niko.br.di.NetworkModule;

public class App extends Application {

  private static AppComponent appComponent;

  public static AppComponent getAppComponent() {
    return appComponent;
  }

  @Override
  public void onCreate() {
    super.onCreate();
    appComponent = DaggerAppComponent.builder()
        .appModule(new AppModule(this))
        .networkModule(new NetworkModule())
        .build();
  }
}
