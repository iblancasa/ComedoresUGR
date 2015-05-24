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

    private ArrayList<Dia> semana = new ArrayList<>(7);;
    TextView textView;





    @Override
    protected String doInBackground(String... strings) {

        StringBuffer buffer = new StringBuffer();


        try {
            Log.d("JSwa", "Connecting to ["+strings[0]+"]");
            Document doc  = Jsoup.connect(strings[0]).get();
            Log.d("JSwa", "Connected to ["+strings[0]+"]");


            Elements metaElems = doc.select("#plato");
            for (Element metaElem : metaElems) {

                String name = metaElem.attr("id");
                buffer.append("name ["+name+"] \r\n");

                Element menuDia = metaElem.getElementById("platos");
                Elements platos = menuDia.children();

                for(Element plato : platos){
                    buffer.append("\r\rplato ["+plato.html()+"] \n");
                }

            }

        }
        catch(Throwable t) {
            t.printStackTrace();
        }



        return buffer.toString();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        textView.setText(s);
      //  respText.setText(s);
    }
}



