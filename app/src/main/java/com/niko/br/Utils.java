package com.niko.br;

import android.app.Activity;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.niko.br.models.Bank;
import com.niko.br.models.gson.BTC.BTC;
import com.niko.br.models.gson.BTC.EUR;
import com.niko.br.models.gson.BTC.RUB;
import com.niko.br.models.gson.BTC.ResponseBTC;
import com.niko.br.models.gson.BTC.UAH;
import com.niko.br.models.gson.BTC.USD;
import com.niko.br.models.gson.CashexRate;
import com.niko.br.models.gson.NBU;
import com.niko.br.ui.main.banks.adapter.BankTitle;
import com.vstechlab.easyfonts.EasyFonts;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import retrofit2.Response;


public class Utils {

  public static final String NBU_URL = "https://bank.gov.ua/NBUStatService/v1/statdirectory/exchange?json";
  public static final String MEJBANK = "http://api.cashex.com.ua/api/v1/exchange/mejbank?locale=ua";
  public static final String BLACK_MARKET = "http://api.cashex.com.ua/api/v1/exchange/black-market?locale=ua";
  public static final String BTC_RATE = "http://api.coindesk.com/v1/bpi/currentprice.json";
  public static final String BTC_UAH = "http://api.coindesk.com/v1/bpi/currentprice/UAH.json";
  public static final String BTC_RUB = "http://api.coindesk.com/v1/bpi/currentprice/RUB.json";

  public static final String BANK_AVAL = "http://api.cashex.com.ua/api/v1/exchange/bank/aval";
  public static final String BANK_OTP = "http://api.cashex.com.ua/api/v1/exchange/bank/otp";
  public static final String BANK_UKRSIBBBANK = "http://api.cashex.com.ua/api/v1/exchange/bank/ukrsibbank";
  public static final String BANK_ALPHABANK = "http://api.cashex.com.ua/api/v1/exchange/bank/alpha-bank";
  public static final String BANK_PRIVATBANK = "http://api.cashex.com.ua/api/v1/exchange/bank/privatbank";
  public static final String BANK_SBERBANK = "http://api.cashex.com.ua/api/v1/exchange/bank/sbrf";

  public static final String BM_AND_MB_KEY = "bmAndMb";
  public static final String BANKS_KEY = "banks";
  public static final String NBU_KEY = "nbu";
  public static final String BTC_KEY = "btc";
  public static final String AUTHOR_KEY = "author";
  public static final String SAVE_FRAGMENT = "save";
  public static final String BM_LIST_MAP = "bmList";
  public static final String MEJ_BANKS_MAP = "mejBanks";
  public static final String FULL_MAP = "full";

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

  public static Map<String, Object> formatBmAndMb(List<CashexRate> bmList,
      List<CashexRate> mejBanks) {
    Map<String, Object> map = new LinkedHashMap<>();

    map.put(BM_LIST_MAP, bmList);
    map.put(MEJ_BANKS_MAP, mejBanks);

    List<Object> list = new ArrayList<>();

    list.add("Чорний ринок");
    for (CashexRate b : bmList) {
      b.setBM(true);
      list.add(b);
    }
    list.add("Міжбанк");
    for (CashexRate mb : mejBanks) {
      list.add(mb);
    }
    map.put(FULL_MAP, list);
    return map;
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
      if (nbu.getCc().equals("USD")) {
        list.add(nbu);
      }
      if (nbu.getCc().equals("EUR")) {
        list.add(nbu);
      }
      if (nbu.getCc().equals("RUB")) {
        list.add(nbu);
      }
    }
    return list;
  }

  public static List<NBU> formatFullNBUList(List<NBU> nbuList) {
    List<NBU> list = new ArrayList<>();
    for (NBU nbu : nbuList) {
      if (nbu.getCc().equals("USD")) {
        list.add(0, nbu);
      }
      if (nbu.getCc().equals("EUR")) {
        list.add(1, nbu);
      }
      if (nbu.getCc().equals("RUB")) {
        list.add(2, nbu);
      }
      if (!nbu.getCc().equals("USD") && !nbu.getCc().equals("EUR") && !nbu.getCc().equals("RUB")) {
        list.add(nbu);
      }
    }
    return list;
  }

  public static float convert(float value, float from, float to) {
    return value * from * (1 / to);
  }


  public static List<Bank> getBanks() {
    List<Bank> bankList = new ArrayList<>();
    bankList.add(new Bank("aval", "Райффайзен Банк Аваль"));
    bankList.add(new Bank("ukrsibbank", "УкрСиббанк"));
    bankList.add(new Bank("alpha-bank", "Альфа-Банк"));
    bankList.add(new Bank("privatbank", "ПриватБанк"));
    bankList.add(new Bank("sbrf", "Сбербанк Росії"));
    return bankList;
  }

  public static int getImageBank(int id) {
    switch (id){
      case 0:
        return R.drawable.aval;
      case 1:
        return R.drawable.ukrsibbank;
      case 2:
        return R.drawable.alfa_bank;
      case 3:
        return R.drawable.privatbank;
      case 4:
        return R.drawable.sberbank;
    }
    return -1;
  }

  public static int getFlag(String currency) {
    switch (currency) {
      case "USD":
        return R.drawable.usa;
      case "EUR":
        return R.drawable.eur;
      case "RUB":
        return R.drawable.ru;
    }
    return 0;
  }

  public static List<BankTitle> formatBanks(List<CashexRate> t1, List<CashexRate> t2,
      List<CashexRate> t3, List<CashexRate> t4, List<CashexRate> t5) {
    List<BankTitle> list = new ArrayList<>();
    t1.remove(t1.size() - 1);
    t1.remove(t1.size() - 1);
    list.add(new BankTitle("Райффайзен Банк Аваль", t1));
    t2.remove(t2.size() - 1);
    t2.remove(t2.size() - 1);
    list.add(new BankTitle("УкрСиббанк", t2));
    list.add(new BankTitle("Альфа-Банк", t3));
    list.add(new BankTitle("ПриватБанк", t4));
    t5.remove(t5.size() - 1);
    list.add(new BankTitle("Сбербанк Росії", t5));
    return list;

  }

}
