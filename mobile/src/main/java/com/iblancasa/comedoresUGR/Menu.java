package com.iblancasa.comedoresUGR;

import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;


public class Menu {
    ParserComedor parse;
    ArrayList<Dia> platos;

    public Menu(TextView p1, TextView p2, TextView p3){
        parse = new ParserComedor(p1,p2,p3);
        parse.execute(new String[]{"http://comedoresUGR.tcomunica.org/"});
        platos=parse.getSemana();
    }


    public Dia getHoy(){
        return parse.getHoy();
    }

    public boolean finished(){
        return platos!=null;
    }



}
