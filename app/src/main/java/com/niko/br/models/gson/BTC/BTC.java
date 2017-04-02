
package com.niko.br.models.gson.BTC;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BTC {

    @SerializedName("time")
    @Expose
    private Time time;
    @SerializedName("disclaimer")
    @Expose
    private String disclaimer;
    @SerializedName("bpi")
    @Expose
    private Bpi bpi;

    /**
     * 
     * @return
     *     The time
     */
    public Time getTime() {
        return time;
    }

    /**
     * 
     * @param time
     *     The time
     */
    public void setTime(Time time) {
        this.time = time;
    }

    /**
     * 
     * @return
     *     The disclaimer
     */
    public String getDisclaimer() {
        return disclaimer;
    }

    /**
     * 
     * @param disclaimer
     *     The disclaimer
     */
    public void setDisclaimer(String disclaimer) {
        this.disclaimer = disclaimer;
    }

    /**
     * 
     * @return
     *     The bpi
     */
    public Bpi getBpi() {
        return bpi;
    }

    /**
     * 
     * @param bpi
     *     The bpi
     */
    public void setBpi(Bpi bpi) {
        this.bpi = bpi;
    }

}
