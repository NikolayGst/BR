package com.niko.br.models.gson;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BMandMB implements Parcelable {

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

    /**
     *
     * @return
     * The currency
     */
    public String getCurrency() {
        return currency;
    }

    /**
     *
     * @param currency
     * The Currency
     */
    public void setCurrency(String currency) {
        this.currency = currency;
    }

    /**
     *
     * @return
     * The currencyName
     */
    public String getCurrencyName() {
        return currencyName;
    }

    /**
     *
     * @param currencyName
     * The CurrencyName
     */
    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    /**
     *
     * @return
     * The buy
     */
    public float getBuy() {
        return buy;
    }

    /**
     *
     * @param buy
     * The Buy
     */
    public void setBuy(float buy) {
        this.buy = buy;
    }

    /**
     *
     * @return
     * The sale
     */
    public float getSale() {
        return sale;
    }

    /**
     *
     * @param sale
     * The Sale
     */
    public void setSale(float sale) {
        this.sale = sale;
    }

    /**
     *
     * @return
     * The buyDelta
     */
    public float getBuyDelta() {
        return buyDelta;
    }

    /**
     *
     * @param buyDelta
     * The BuyDelta
     */
    public void setBuyDelta(float buyDelta) {
        this.buyDelta = buyDelta;
    }

    /**
     *
     * @return
     * The saleDelta
     */
    public float getSaleDelta() {
        return saleDelta;
    }

    /**
     *
     * @param saleDelta
     * The SaleDelta
     */
    public void setSaleDelta(float saleDelta) {
        this.saleDelta = saleDelta;
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
    }

    public BMandMB() {
    }

    protected BMandMB(Parcel in) {
        this.currency = in.readString();
        this.currencyName = in.readString();
        this.buy = in.readFloat();
        this.sale = in.readFloat();
        this.buyDelta = in.readFloat();
        this.saleDelta = in.readFloat();
    }

    public static final Creator<BMandMB> CREATOR = new Creator<BMandMB>() {
        @Override
        public BMandMB createFromParcel(Parcel source) {
            return new BMandMB(source);
        }

        @Override
        public BMandMB[] newArray(int size) {
            return new BMandMB[size];
        }
    };
}