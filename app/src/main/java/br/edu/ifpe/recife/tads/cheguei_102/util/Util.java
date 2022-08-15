package br.edu.ifpe.recife.tads.cheguei_102.util;
import android.text.format.Time;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class Util {



    public static String getDataAtual() {

        String dataAtual="00/00/0000";

        try {

            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
            dataAtual = simpleDateFormat.format(calendar.getTime());
            return dataAtual;

        } catch (Exception e) { }

        return dataAtual;
  }


    public static String getHoraAtual() {

        String horaAtual = "00:00:00";

        try {

            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss",Locale.getDefault());
            horaAtual = simpleDateFormat.format(calendar.getTime());


            return horaAtual;

        } catch (Exception e) { }

        return horaAtual;

    }

}

