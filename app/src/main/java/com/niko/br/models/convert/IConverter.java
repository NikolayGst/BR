package com.niko.br.models.convert;

public interface IConverter {
    float convertUsdIn(float count, String in);
    float convertEurIn(float count, String in);
    float convertUahIn(float count, String in);
    float convertRubIn(float count, String in);
}
