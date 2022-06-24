package br.edu.ifpe.recife.tads.cheguei_102.util;

import java.util.Calendar;

public class Util {
    /**
     * @return Retorna a data atual
     */
    public static String getDataAtual() {

        String dia, mes, ano, dataAtual="00/00/0000";

        try {
            Calendar calendar = Calendar.getInstance();
            dia = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
            mes = String.valueOf(calendar.get(Calendar.MONTH) + 1);
            ano = String.valueOf(calendar.get(Calendar.YEAR));
            dia = (Calendar.DAY_OF_MONTH < 10) ? "0" + dia : dia;
            int mesAtual = (Calendar.MONTH) + 1;
            mes = (mesAtual < 10) ? "0" + mes : mes;
            dataAtual = dia + "/" + mes + "/" + ano;
            return dataAtual;

        } catch (Exception e) { }

        return dataAtual;
    }

    /**
     * @return Retorna a hora atual
     */
    public static String getHoraAtual() {

        String horaAtual = "00:00:00";
        String hora, minuto, segundo;

        try {
            Calendar calendar = Calendar.getInstance();
            int iHora = calendar.get(Calendar.HOUR_OF_DAY);
            int iMinuto = calendar.get(Calendar.MINUTE);
            int iSegundo = calendar.get(Calendar.SECOND);

            hora = (iHora <= 9) ? "0" + iHora : Integer.toString(iHora);
            minuto = (iMinuto <= 9) ? "0" + iMinuto : Integer.toString(iHora);
            segundo = (iSegundo <= 9) ? "0" + iSegundo : Integer.toString(iHora);
            horaAtual = hora + ":" + minuto + ":" + segundo;
            return horaAtual;

        } catch (Exception e) { }

        return horaAtual;
    }
    public static int gerarID(){
        int id = (int) (1 + Math.random() * 100);
        return id;
    }
}

