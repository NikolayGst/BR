package com.niko.br.models.convert;

import com.niko.br.models.gson.BMandMB;

public class BMandMBConverter implements IConverter {

    private BMandMB usd;
    private BMandMB eur;
    private BMandMB rub;

    public BMandMBConverter(BMandMB usd, BMandMB rub, BMandMB eur) {
        this.usd = usd;
        this.rub = rub;
        this.eur = eur;
    }

    @Override
    public float convertUsdIn(float count, String in) {
        switch (in) {
            case "USD":
                return count;
            case "EUR":
                return count * usd.getSale() / eur.getBuy();
            case "UAH":
                return count * usd.getSale();
            case "RUB":
                return count * usd.getSale() / rub.getBuy();
        }
        return Float.NaN;
    }

    @Override
    public float convertEurIn(float count, String in) {
        switch (in) {
            case "USD":
                return count * eur.getSale() / usd.getBuy();
            case "EUR":
                return count;
            case "UAH":
                return count * eur.getSale();
            case "RUB":
                return count * eur.getSale() / rub.getBuy();
        }
        return Float.NaN;
    }

    @Override
    public float convertUahIn(float count, String in) {
        switch (in) {
            case "USD":
                return count / usd.getBuy();
            case "EUR":
                return count / eur.getBuy();
            case "UAH":
                return count;
            case "RUB":
                return count / rub.getBuy();
        }
        return Float.NaN;
    }

    @Override
    public float convertRubIn(float count, String in) {
        switch (in) {
            case "USD":
                return count * rub.getSale() / usd.getBuy();
            case "EUR":
                return count * rub.getSale() / eur.getBuy();
            case "UAH":
                return count * rub.getSale();
            case "RUB":
                return count;
        }
        return Float.NaN;
    }
}
