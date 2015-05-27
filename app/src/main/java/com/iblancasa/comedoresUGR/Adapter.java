package com.iblancasa.comedoresUGR;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;


public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {


    private List<Dia> platos;
    private Context context;

    public Adapter(ArrayList<Dia> p, Context c){
        platos=p;
        context=c;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parentViewGroup, int i) {

        View rowView = LayoutInflater.from (parentViewGroup.getContext())
                .inflate(R.layout.item_plato, parentViewGroup, false);


        return new ViewHolder (rowView);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        final Dia rowData = platos.get(position);
        ArrayList<String> comidas = rowData.getComida();

        viewHolder.diaSemana.setText(rowData.getDia());
        viewHolder.primerPlato.setText(comidas.get(0));
        viewHolder.segundoPlato.setText(comidas.get(1));
        viewHolder.tercerPlato.setText(comidas.get(2));


        viewHolder.itemView.setTag(rowData);
    }


    @Override
    public int getItemCount() {
        return platos.size();
    }




    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView diaSemana;
        private final TextView primerPlato;
        private final TextView segundoPlato;
        private final TextView tercerPlato;


        public ViewHolder(View itemView) {
            super(itemView);

            diaSemana = (TextView) itemView.findViewById(R.id.diaSemana);
            primerPlato = (TextView) itemView.findViewById(R.id.primerPlatoDia);
            segundoPlato = (TextView) itemView.findViewById(R.id.segundoPlatoDia);
            tercerPlato = (TextView) itemView.findViewById(R.id.tercerPlatoDia);

        }
    }

}