package com.niko.br.di;

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
  Retrofit provideRetrofit() {
    return new Retrofit.Builder()
        .baseUrl("https://your.api.url/")
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
