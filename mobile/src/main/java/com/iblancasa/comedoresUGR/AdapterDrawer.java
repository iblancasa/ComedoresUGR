package com.iblancasa.comedoresUGR;


import android.content.Context;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;




/**
 * Adapter para el Drawer de la izquierda
 * */
public class AdapterDrawer extends RecyclerView.Adapter<AdapterDrawer.ViewHolder> {

    private static final int TYPE_HEADER = 0; //Conocer la vista

    private static final int TYPE_ITEM = 1;

    private String mNavTitles[]; //Títulos
    private int mIcons[];      //Iconos


    Context context;
    static int pulsado=0;

    DrawerLayout drawerL;


    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        int Holderid;


        TextView textView;
        ImageView imageView;
        ImageView profile;
        TextView Name;
        TextView email;
        Context contxt;
        DrawerLayout drawerL;



        public ViewHolder(View itemView,int ViewType,Context c, DrawerLayout d) {
            super(itemView);
            contxt = c;
            itemView.setClickable(true);
            itemView.setOnClickListener(this);
            drawerL=d;


            if(ViewType == TYPE_ITEM) {
                textView = (TextView) itemView.findViewById(R.id.rowText);
                imageView = (ImageView) itemView.findViewById(R.id.rowIcon);
                Holderid = 1;
            }
            else{

                Holderid = 0;
            }
        }

        @Override
        public void onClick(View v) {
            drawerL.closeDrawers();
            pulsado = getPosition();
        }
    }



    AdapterDrawer(String Titles[],int Icons[],Context passedContext, DrawerLayout d){
        mNavTitles = Titles;
        mIcons = Icons;
        this.context = passedContext;
        drawerL=d;
    }




    @Override
    public AdapterDrawer.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == TYPE_ITEM) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row,parent,false);

            ViewHolder vhItem = new ViewHolder(v,viewType,context,drawerL);

            return vhItem;

        } else if (viewType == TYPE_HEADER) {

            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.header,parent,false);

            ViewHolder vhHeader = new ViewHolder(v,viewType,context,drawerL);

            return vhHeader;
        }
        return null;

    }


    @Override
    public void onBindViewHolder(AdapterDrawer.ViewHolder holder, int position) {
        if(holder.Holderid ==1) {
            holder.textView.setText(mNavTitles[position - 1]);
            holder.imageView.setImageResource(mIcons[position -1]);
        }
    }


    @Override
    public int getItemCount() {
        return mNavTitles.length+1;
    }



    @Override
    public int getItemViewType(int position) {
        if (isPositionHeader(position))
            return TYPE_HEADER;

        return TYPE_ITEM;
    }

    private boolean isPositionHeader(int position) {
        return position == 0;
    }

}