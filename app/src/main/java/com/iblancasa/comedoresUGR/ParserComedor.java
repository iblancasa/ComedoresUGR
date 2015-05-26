package com.iblancasa.comedoresUGR;


import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

public class ParserComedor extends AsyncTask<String, Void, String> {

    private ArrayList<Dia> semana = new ArrayList<>();

    public ArrayList<Dia> getSemana(){
        return semana;
    }

    private TextView pl1,pl2,pl3;
    ArrayList<String> platosMenu;

    public ParserComedor(TextView p1, TextView p2, TextView p3){
        super();
        pl1=p1;
        pl2=p2;
        pl3=p3;

    }


    @Override
    protected String doInBackground(String... strings) {


        Connection.Response response = null;
        try {
            response = Jsoup.connect(strings[0])
                    .userAgent("Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/535.21 (KHTML, like Gecko) Chrome/19.0.1042.0 Safari/535.21")
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

                    String diaplato = metaElem.getElementById("diaplato").getElementById("fechaplato").html();
                    diaplato = diaplato.replace("<div class=\"numero\">", "");
                    diaplato = diaplato.replace("</div>", "");

                    Element menuDia = metaElem.getElementById("platos");
                    Elements platos = menuDia.children();

                    for (Element plato : platos) {
                        platosMenu.add(plato.html());
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
        ArrayList<String> platosHoy = d.getComida();

        if(platosHoy.size()==3) {
            pl1.setText(platosHoy.get(0));
            pl2.setText(platosHoy.get(1));
            pl3.setText(platosHoy.get(2));
        }
        else{
            pl1.setText("Algo falló (compruebe la conexión a Internet)");
            pl2.setText("");
            pl3.setText("");
        }

    }

    public Dia getHoy(){

        if(semana.size()!=1) {

            Calendar rightNow = Calendar.getInstance();
            String dia;

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
                default:
                    return null;
            }

            for (int i = 0; i < semana.size(); i++) {

                if (semana.get(i).getDia().contains(dia)) {
                    return semana.get(i);
                }
            }
        }
        else if(semana.size()==1) {
            return semana.get(0);
        }

        return null;

    }


}



