package com.niko.br.models.gson;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CashexRate implements BaseTypeRate, Parcelable {

  @SerializedName("Currency")
  @Expose
  private String currency;
  @SerializedName("CurrencyName")
  @Expose
  private String currencyName;
  @SerializedName("Buy")
  @Expose
  private float buy;
  @SerializedName("Sale")
  @Expose
  private float sale;
  @SerializedName("BuyDelta")
  @Expose
  private float buyDelta;
  @SerializedName("SaleDelta")
  @Expose
  private float saleDelta;

  private boolean isBM;

  public String getCurrency() {
    return currency;
  }

  public void setCurrency(String currency) {
    this.currency = currency;
  }

  public String getCurrencyName() {
    return currencyName;
  }

  public void setCurrencyName(String currencyName) {
    this.currencyName = currencyName;
  }

  public float getBuy() {
    return buy;
  }

  public void setBuy(float buy) {
    this.buy = buy;
  }

  public float getSale() {
    return sale;
  }

  public void setSale(float sale) {
    this.sale = sale;
  }

  public float getBuyDelta() {
    return buyDelta;
  }

  public void setBuyDelta(float buyDelta) {
    this.buyDelta = buyDelta;
  }

  public float getSaleDelta() {
    return saleDelta;
  }

  public void setSaleDelta(float saleDelta) {
    this.saleDelta = saleDelta;
  }

  public boolean isBM() {
    return isBM;
  }

  public void setBM(boolean BM) {
    isBM = BM;
  }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(this.currency);
    dest.writeString(this.currencyName);
    dest.writeFloat(this.buy);
    dest.writeFloat(this.sale);
    dest.writeFloat(this.buyDelta);
    dest.writeFloat(this.saleDelta);
    dest.writeByte(this.isBM ? (byte) 1 : (byte) 0);
  }

  public CashexRate() {
  }

  protected CashexRate(Parcel in) {
    this.currency = in.readString();
    this.currencyName = in.readString();
    this.buy = in.readFloat();
    this.sale = in.readFloat();
    this.buyDelta = in.readFloat();
    this.saleDelta = in.readFloat();
    this.isBM = in.readByte() != 0;
  }

  public static final Creator<CashexRate> CREATOR = new Creator<CashexRate>() {
    @Override
    public CashexRate createFromParcel(Parcel source) {
      return new CashexRate(source);
    }

    @Override
    public CashexRate[] newArray(int size) {
      return new CashexRate[size];
    }
  };
}