package com.niko.br;

import android.app.Activity;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.niko.br.models.gson.BMandMB;
import com.niko.br.models.gson.BTC.ResponseBTC;
import com.niko.br.models.gson.BTC.BTC;
import com.niko.br.models.gson.BTC.EUR;
import com.niko.br.models.gson.BTC.RUB;
import com.niko.br.models.gson.BTC.UAH;
import com.niko.br.models.gson.BTC.USD;
import com.niko.br.models.gson.NBU;
import com.vstechlab.easyfonts.EasyFonts;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Response;


public class Utils {

  public static final String NBU_URL = "https://bank.gov.ua/NBUStatService/v1/statdirectory/exchange?json";
  public static final String MEJBANK = "http://api.cashex.com.ua/api/v1/exchange/mejbank?locale=ua";
  public static final String BLACK_MARKET = "http://api.cashex.com.ua/api/v1/exchange/black-market?locale=ua";
  public static final String BTC_RATE = "http://api.coindesk.com/v1/bpi/currentprice.json";
  public static final String BTC_UAH = "http://api.coindesk.com/v1/bpi/currentprice/UAH.json";
  public static final String BTC_RUB = "http://api.coindesk.com/v1/bpi/currentprice/RUB.json";

  public static final String BM_AND_MB_KEY = "bmAndMb";
  public static final String BANKS_KEY = "banks";
  public static final String NBU_KEY = "nbu";
  public static final String BTC_KEY = "btc";
  public static final String AUTHOR_KEY = "author";
  public static final String SAVE_FRAGMENT = "save";

  public static void applyFontedTab(Activity activity, ViewPager viewPager, TabLayout tabLayout) {
    for (int i = 0; i < viewPager.getAdapter().getCount(); i++) {
      TextView tv = (TextView) activity.getLayoutInflater().inflate(R.layout.tab_item, null);
      tv.setTypeface(EasyFonts.robotoRegular(activity.getApplicationContext()));
      if (i == viewPager.getCurrentItem()) {
        tv.setSelected(true);
      }
      tv.setText(viewPager.getAdapter().getPageTitle(i));
      tabLayout.getTabAt(i).setCustomView(tv);
    }
  }

  public static List<Object> formatBmAndMb(List<BMandMB> bmList, List<BMandMB> mejBanks) {
    List<Object> list = new ArrayList<>();
    list.add("Чорний ринок");
    for (BMandMB b : bmList) {
      list.add(b);
    }
    list.add("Міжбанк");
    for (BMandMB mb : mejBanks) {
      list.add(mb);
    }
    return list;
  }

  public static void setMargins(View view, int left, int top, int right, int bottom) {
    if (view.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
      ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
      p.setMargins(left, top, right, bottom);
      view.requestLayout();
    }
  }

  public static void setPadding(View view, int left, int top, int right, int bottom) {
    view.setPadding(left, top, right, bottom);
  }

  public static BTC formatBTC(Response<ResponseBTC> usdAndEurResponse,
      Response<ResponseBTC> uahResponse,
      Response<ResponseBTC> rubResponse) {

    USD usd = usdAndEurResponse.body().getBpi().getUSD();
    EUR eur = usdAndEurResponse.body().getBpi().getEUR();
    UAH uah = uahResponse.body().getBpi().getUAH();
    RUB rub = rubResponse.body().getBpi().getRUB();

    return new BTC(usd, eur, uah, rub);
  }

  public static List<NBU> formatSmallNBUList(List<NBU> nbuList) {
    List<NBU> list = new ArrayList<>();
    for (NBU nbu : nbuList) {
      if (nbu.getCc().equals("USD")) list.add(nbu);
      if (nbu.getCc().equals("EUR")) list.add(nbu);
      if (nbu.getCc().equals("RUB")) list.add(nbu);
    }
    return list;
  }

  public static List<NBU> formatFullNBUList(List<NBU> nbuList) {
    List<NBU> list = new ArrayList<>();
    for (NBU nbu : nbuList) {
      if (nbu.getCc().equals("USD")) list.add(0,nbu);
      if (nbu.getCc().equals("EUR")) list.add(1,nbu);
      if (nbu.getCc().equals("RUB")) list.add(2,nbu);
      if (!nbu.getCc().equals("USD") && !nbu.getCc().equals("EUR") && !nbu.getCc().equals("RUB")) {
        list.add(nbu);
      }
    }
    return list;
  }
}
