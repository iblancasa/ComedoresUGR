package com.iblancasa.comedoresUGR;



public class Dia {
    private String nombre;
    private String comida;

    public Dia(String n, String c){
        nombre=n;
        comida=c;
    }

    public String getDia(){
        return nombre;
    }

    public String getComida(){
        return comida;
    }
}
