package com.niko.br.di;

import com.niko.br.di.annotations.BTC;
import com.niko.br.di.annotations.Cashex;
import com.niko.br.di.annotations.NBU;
import com.niko.br.models.network.API;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {

  @Singleton
  @Provides
  @BTC
  Retrofit provideBTCRetrofit() {
    return new Retrofit.Builder()
        .baseUrl("http://api.coindesk.com")
        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build();
  }

  @Singleton
  @Provides
  @Cashex
  Retrofit provideCashexRetrofit() {
    return new Retrofit.Builder()
        .baseUrl("http://api.cashex.com.ua")
        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build();
  }

  @Singleton
  @Provides
  @NBU
  Retrofit provideNBURetrofit() {
    return new Retrofit.Builder()
        .baseUrl("https://bank.gov.ua")
        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build();
  }

  @Singleton
  @Provides
  API provideApi(Retrofit retrofit) {
    return retrofit.create(API.class);
  }

}
