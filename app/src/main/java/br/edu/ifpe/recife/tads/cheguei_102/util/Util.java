package br.edu.ifpe.recife.tads.cheguei_102.util;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.TimeZone;

public class Util {
    /**
     * @return Retorna a data atual
     */

    public static String getDataAtual() {

        String dia, mes, ano, dataAtual="00/00/0000";

        try {

            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy" );
            dataAtual = simpleDateFormat.format(calendar.getTime());
            return dataAtual;

        } catch (Exception e) { }

        return dataAtual;
  }

    /**
     * @return Retorna a hora atual
     */
    public static String getHoraAtual() {

        String horaAtual = "00:00:00";

        try {

            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
            horaAtual = simpleDateFormat.format(calendar.getTime());

            return horaAtual;

        } catch (Exception e) { }

        return horaAtual;

    }

}

