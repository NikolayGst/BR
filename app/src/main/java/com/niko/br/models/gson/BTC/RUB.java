
package com.niko.br.models.gson.BTC;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RUB {

    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("rate")
    @Expose
    private String rate;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("rate_float")
    @Expose
    private float rateFloat;

    /**
     * 
     * @return
     *     The code
     */
    public String getCode() {
        return code;
    }

    /**
     * 
     * @param code
     *     The code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 
     * @return
     *     The rate
     */
    public String getRate() {
        return rate;
    }

    /**
     * 
     * @param rate
     *     The rate
     */
    public void setRate(String rate) {
        this.rate = rate;
    }

    /**
     * 
     * @return
     *     The description
     */
    public String getDescription() {
        return description;
    }

    /**
     * 
     * @param description
     *     The description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 
     * @return
     *     The rateFloat
     */
    public float getRateFloat() {
        return rateFloat;
    }

    /**
     * 
     * @param rateFloat
     *     The rate_float
     */
    public void setRateFloat(float rateFloat) {
        this.rateFloat = rateFloat;
    }

}
