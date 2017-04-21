package com.niko.br.models.network;

import com.niko.br.models.gson.CashexRate;
import com.niko.br.models.gson.BTC.ResponseBTC;
import com.niko.br.models.gson.NBU;
import java.util.List;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Url;
import rx.Observable;

public interface API {
  @GET
  Observable<Response<List<CashexRate>>> getMejBank(@Url String url);

  @GET
  Observable<Response<List<CashexRate>>> getBM(@Url String url);

  @GET
  Observable<Response<List<CashexRate>>> getBank(@Url String url);

  @GET
  Observable<Response<List<NBU>>> getNBUBank(@Url String url);

  @GET
  Observable<Response<ResponseBTC>> getBTCCurrentRate(@Url String url);

  @GET
  Observable<Response<ResponseBTC>> getBTCUah(@Url String url);

  @GET
  Observable<Response<ResponseBTC>> getBTCRub(@Url String url);
}
