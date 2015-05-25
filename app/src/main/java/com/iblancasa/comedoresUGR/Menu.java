package com.iblancasa.comedoresUGR;

import java.util.ArrayList;
import java.util.Calendar;


public class Menu {
    ParserComedor parse;
    ArrayList<Dia> platos;

    public Menu(){
        parse = new ParserComedor();
        parse.execute(new String[]{"http://comedoresugr.tcomunica.org/"});
        platos = parse.getSemana();
    }


    public Dia getHoy(){
        Calendar rightNow = Calendar.getInstance();
        String dia;

        switch (rightNow.get(Calendar.DAY_OF_WEEK)){
            case Calendar.MONDAY:
                dia="Lunes";
                break;
            case Calendar.TUESDAY:
                dia="Martes";
                break;
            case Calendar.WEDNESDAY:
                dia="Miércoles";
                break;
            case Calendar.THURSDAY:
                dia="Jueves";
                break;
            case Calendar.FRIDAY:
                dia="Viernes";
                break;
            case Calendar.SATURDAY:
                dia="Sábado";
                break;
            default:
                return null;
        }

        for(int i=0;i<platos.size();i++){
            if(platos.get(i).getDia().contains(dia)){
                return platos.get(i);
            }
        }

        return null;
    }
}
