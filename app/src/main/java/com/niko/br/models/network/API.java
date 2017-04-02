package com.niko.br.models.network;

import com.niko.br.models.gson.BMandMB;
import com.niko.br.models.gson.BTC.BTC;
import com.niko.br.models.gson.NBU;
import java.util.List;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Url;
import rx.Observable;

public interface API {
  @GET
  Observable<Response<List<BMandMB>>> getMejBank(@Url String url);

  @GET
  Observable<Response<List<BMandMB>>> getBM(@Url String url);

  @GET
  Observable<Response<List<NBU>>> getNBUBank(@Url String url);

  @GET
  Observable<Response<BTC>> getBTCCurrentRate(@Url String url);

  @GET
  Observable<Response<BTC>> getBTCUah(@Url String url);

  @GET
  Observable<Response<BTC>> getBTCRub(@Url String url);
}
