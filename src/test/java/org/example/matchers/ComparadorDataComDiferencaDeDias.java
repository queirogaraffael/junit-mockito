package org.example.matchers;

import org.example.utils.DataUtils;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ComparadorDataComDiferencaDeDias extends TypeSafeMatcher<Date> {

    private Integer quantidadeDias;

    public ComparadorDataComDiferencaDeDias(Integer quantidadeDias){
        this.quantidadeDias = quantidadeDias;
    }

    @Override
    protected boolean matchesSafely(Date data) {
        return DataUtils.isMesmaData(data, DataUtils.obterDataComDiferencaDias(quantidadeDias));
    }

    @Override
    public void describeTo(Description description) {
        Date dataEsperada = DataUtils.obterDataComDiferencaDias(quantidadeDias);
        DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        description.appendText(format.format(dataEsperada));
    }
}
