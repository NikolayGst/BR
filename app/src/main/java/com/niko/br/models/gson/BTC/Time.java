
package com.niko.br.models.gson.BTC;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Time {

    @SerializedName("updated")
    @Expose
    private String updated;
    @SerializedName("updatedISO")
    @Expose
    private String updatedISO;
    @SerializedName("updateduk")
    @Expose
    private String updateduk;

    /**
     * 
     * @return
     *     The updated
     */
    public String getUpdated() {
        return updated;
    }

    /**
     * 
     * @param updated
     *     The updated
     */
    public void setUpdated(String updated) {
        this.updated = updated;
    }

    /**
     * 
     * @return
     *     The updatedISO
     */
    public String getUpdatedISO() {
        return updatedISO;
    }

    /**
     * 
     * @param updatedISO
     *     The updatedISO
     */
    public void setUpdatedISO(String updatedISO) {
        this.updatedISO = updatedISO;
    }

    /**
     * 
     * @return
     *     The updateduk
     */
    public String getUpdateduk() {
        return updateduk;
    }

    /**
     * 
     * @param updateduk
     *     The updateduk
     */
    public void setUpdateduk(String updateduk) {
        this.updateduk = updateduk;
    }

}
