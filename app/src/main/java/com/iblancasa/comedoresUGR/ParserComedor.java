package com.iblancasa.comedoresUGR;


import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

public class ParserComedor extends AsyncTask<String, Void, String> {

    private ArrayList<Dia> semana = new ArrayList<>();

    public ArrayList<Dia> getSemana(){
        return semana;
    }


    @Override
    protected String doInBackground(String... strings) {


        ArrayList<String> platosMenu;

        try {
            Document doc  = Jsoup.connect(strings[0]).get();

            Elements metaElems = doc.select("#plato");
            for (Element metaElem : metaElems) {

                platosMenu = new ArrayList<>();

                String diaplato = metaElem.getElementById("diaplato").getElementById("fechaplato").html();
                diaplato = diaplato.replace("<div class=\"numero\">","");
                diaplato = diaplato.replace("</div>","");

                Element menuDia = metaElem.getElementById("platos");
                Elements platos = menuDia.children();

                for(Element plato : platos){
                    platosMenu.add(plato.html());
                }

                Dia d = new Dia(diaplato,platosMenu);
                semana.add(d);
            }

        }
        catch(Throwable t) {
            t.printStackTrace();
        }



        return "";
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }
}



