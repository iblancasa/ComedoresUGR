package com.iblancasa.comedoresugr;


import java.util.ArrayList;

public class Dia {
    private String nombre;
    private ArrayList<String> comida;

    public Dia(String n, ArrayList<String>  c){
        nombre=n;
        comida=c;
    }

    public String getDia(){
        return nombre;
    }

    public ArrayList<String>  getComida(){
        return comida;
    }
}
