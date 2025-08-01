package org.example.matchers;

import org.example.utils.DataUtils;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DiaDaSemanaEsperadoMatcher extends TypeSafeMatcher<Date> {

    private Integer diaDaSemana;

    public DiaDaSemanaEsperadoMatcher(Integer diaDaSemana) {
        this.diaDaSemana = diaDaSemana;
    }

    @Override
    protected boolean matchesSafely(Date data) {
        return DataUtils.verificarDiaSemana(data, diaDaSemana);
    }

    @Override
    public void describeTo(Description description) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_WEEK, diaDaSemana);

        DateFormat dateFormat = new SimpleDateFormat("EEEE", new Locale("pt", "BR"));
        String nomeDia = dateFormat.format(calendar.getTime());

        description.appendText("uma data que caia em " + nomeDia);
    }
}
