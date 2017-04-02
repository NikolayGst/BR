package com.niko.br.models.gson;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class NBU {

    @SerializedName("r030")
    @Expose
    private int r030;
    @SerializedName("txt")
    @Expose
    private String txt;
    @SerializedName("rate")
    @Expose
    private float rate;
    @SerializedName("cc")
    @Expose
    private String cc;
    @SerializedName("exchangedate")
    @Expose
    private String exchangedate;

    /**
     *
     * @return
     * The r030
     */
    public int getR030() {
        return r030;
    }

    /**
     *
     * @param r030
     * The r030
     */
    public void setR030(int r030) {
        this.r030 = r030;
    }

    /**
     *
     * @return
     * The txt
     */
    public String getTxt() {
        return txt;
    }

    /**
     *
     * @param txt
     * The txt
     */
    public void setTxt(String txt) {
        this.txt = txt;
    }

    /**
     *
     * @return
     * The rate
     */
    public float getRate() {
        return rate;
    }

    /**
     *
     * @param rate
     * The rate
     */
    public void setRate(float rate) {
        this.rate = rate;
    }

    /**
     *
     * @return
     * The cc
     */
    public String getCc() {
        return cc;
    }

    /**
     *
     * @param cc
     * The cc
     */
    public void setCc(String cc) {
        this.cc = cc;
    }

    /**
     *
     * @return
     * The exchangedate
     */
    public String getExchangedate() {
        return exchangedate;
    }

    /**
     *
     * @param exchangedate
     * The exchangedate
     */
    public void setExchangedate(String exchangedate) {
        this.exchangedate = exchangedate;
    }

}