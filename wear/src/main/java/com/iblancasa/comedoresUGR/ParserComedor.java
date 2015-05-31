package com.iblancasa.comedoresUGR;


import android.app.FragmentManager;
import android.content.Context;
import android.os.AsyncTask;
import android.support.wearable.view.GridViewPager;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Calendar;

public class ParserComedor extends AsyncTask<String, Void, String> {

    private ArrayList<Dia> semana = new ArrayList<>();


    private Context contextoPadre;
    private GridViewPager gridViewPager;
    ArrayList<String> platosMenu;
    private FragmentManager fManager;
	

    public ParserComedor(Context ctx, GridViewPager grid, FragmentManager fm){
        super();
        contextoPadre = ctx;
	gridViewPager = grid;
        fManager = fm;
       
    }


    @Override
    protected String doInBackground(String... strings) {

        Connection.Response response = null;
        try {
            response = Jsoup.connect(strings[0])
                  //  .userAgent("Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/535.21 (KHTML, like Gecko) Chrome/19.0.1042.0 Safari/535.21")
                    .execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(response!=null) {
            int statusCode = response.statusCode();

            if (statusCode == 200) {

                Document doc = null;
                try {
                    doc = response.parse();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Elements metaElems = doc.select("#plato");
                for (Element metaElem : metaElems) {

                    platosMenu = new ArrayList<>();

                    Charset utf8charset = Charset.forName("UTF-8");
                    Charset iso88591charset = Charset.forName("ISO-8859-1");

                    String diaplato  = metaElem.getElementById("diaplato").getElementById("fechaplato").text();


                    diaplato = diaplato.replaceAll("\\<.*?>","");
                    diaplato = diaplato.replaceAll(" +", " ");



                    Element menuDia = metaElem.getElementById("platos");
                    Elements platos = menuDia.children();

                    for (Element plato : platos) {
                        platosMenu.add(plato.html().replaceAll("\\<.*?>","").replaceAll(" +", " "));
                    }

                    Dia d = new Dia(diaplato, platosMenu);
                    semana.add(d);
                }
            }
            else{
                platosMenu = new ArrayList<>();
                platosMenu.add("No hay conexión");
                Dia d = new Dia("Algo falló",platosMenu);
                semana.add(d);
            }
        }
            else{
                    platosMenu = new ArrayList<>();
                    platosMenu.add("No hay conexión");
                    Dia d = new Dia("Algo falló",platosMenu);
                    semana.add(d);
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

        Dia d = getHoy();

       gridViewPager.setAdapter(new GridPagerAdapter(contextoPadre, fManager,d.getComida(),d.getDia()));
    }

    public Dia getHoy(){

        if(semana.size()!=1) {

            Calendar rightNow = Calendar.getInstance();
            String dia=null;

            switch (rightNow.get(Calendar.DAY_OF_WEEK)) {
                case Calendar.MONDAY:
                    dia = "Lunes";
                    break;
                case Calendar.TUESDAY:
                    dia = "Martes";
                    break;
                case Calendar.WEDNESDAY:
                    dia = "Miércoles";
                    break;
                case Calendar.THURSDAY:
                    dia = "Jueves";
                    break;
                case Calendar.FRIDAY:
                    dia = "Viernes";
                    break;
                case Calendar.SATURDAY:
                    dia = "Sábado";
                    break;
                case Calendar.SUNDAY:
                    ArrayList<String> cerrado = new ArrayList<String>();
                    cerrado.add("Hoy el comedor está cerrado");
                    Dia domingo = new Dia("Domingo", cerrado);
                    return domingo;
            }

            for (int i = 0; i < semana.size(); i++) {
                if (semana.get(i).getDia().contains(dia)) {
                    return semana.get(i);
                }
            }
        }

        return semana.get(0);



    }


}



