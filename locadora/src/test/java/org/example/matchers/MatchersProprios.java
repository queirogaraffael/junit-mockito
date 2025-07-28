package org.example.matchers;

import java.util.Calendar;

public class MatchersProprios {

    public static ComparadorDataComDiferencaDeDias ehHoje() {
        return new ComparadorDataComDiferencaDeDias(0);
    }

    public static ComparadorDataComDiferencaDeDias ehHojeComDiferencaDias(Integer quantidadeDeDias) {
        return new ComparadorDataComDiferencaDeDias(quantidadeDeDias);
    }

    public static DiaDaSemanaEsperadoMatcher caiEm(Integer dia) {
        return new DiaDaSemanaEsperadoMatcher(dia);
    }

    public static DiaDaSemanaEsperadoMatcher caiNumaSegunda() {
        return new DiaDaSemanaEsperadoMatcher(Calendar.MONDAY);
    }

}
