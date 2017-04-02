
package com.niko.br.models.gson.BTC;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Bpi {

    @SerializedName("USD")
    @Expose
    private USD uSD;
    @SerializedName("GBP")
    @Expose
    private GBP gBP;
    @SerializedName("EUR")
    @Expose
    private EUR eUR;
    @SerializedName("UAH")
    @Expose
    private UAH uAH;
    @SerializedName("RUB")
    @Expose
    private RUB rUB;

    /**
     * 
     * @return
     *     The uSD
     */
    public USD getUSD() {
        return uSD;
    }

    /**
     * 
     * @param uSD
     *     The USD
     */
    public void setUSD(USD uSD) {
        this.uSD = uSD;
    }

    /**
     * 
     * @return
     *     The gBP
     */
    public GBP getGBP() {
        return gBP;
    }

    /**
     * 
     * @param gBP
     *     The GBP
     */
    public void setGBP(GBP gBP) {
        this.gBP = gBP;
    }

    /**
     * 
     * @return
     *     The eUR
     */
    public EUR getEUR() {
        return eUR;
    }

    /**
     * 
     * @param eUR
     *     The EUR
     */
    public void setEUR(EUR eUR) {
        this.eUR = eUR;
    }

    /**
     * 
     * @return
     *     The uAH
     */
    public UAH getUAH() {
        return uAH;
    }

    /**
     * 
     * @param uAH
     *     The UAH
     */
    public void setUAH(UAH uAH) {
        this.uAH = uAH;
    }

    /**
     * 
     * @return
     *     The rUB
     */
    public RUB getRUB() {
        return rUB;
    }

    /**
     * 
     * @param rUB
     *     The RUB
     */
    public void setRUB(RUB rUB) {
        this.rUB = rUB;
    }

}
