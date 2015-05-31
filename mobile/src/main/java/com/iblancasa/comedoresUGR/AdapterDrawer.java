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





public class AdapterDrawer extends RecyclerView.Adapter<AdapterDrawer.ViewHolder> {

    private static final int TYPE_HEADER = 0; //Conocer la vista

    private static final int TYPE_ITEM = 1;

    private String mNavTitles[]; //Títulos
    private int mIcons[];      //Iconos

    private String name;        //n
    private int profile;        //Image
    private String email;       //Email
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
                Name = (TextView) itemView.findViewById(R.id.name);
                email = (TextView) itemView.findViewById(R.id.email);
                profile = (ImageView) itemView.findViewById(R.id.circleView);
                Holderid = 0;
            }



        }


        @Override
        public void onClick(View v) {
            drawerL.closeDrawers();
            pulsado = getPosition();
        }
    }



    //Adapter with titles, icons...
    AdapterDrawer(String Titles[],int Icons[],String Name,String Email, int Profile,Context passedContext, DrawerLayout d){

        mNavTitles = Titles;
        mIcons = Icons;
        name = Name;
        email = Email;
        profile = Profile;
        this.context = passedContext;
        drawerL=d;

    }



    //When the ViewHolder inflated
    @Override
    public AdapterDrawer.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == TYPE_ITEM) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row,parent,false); //Inflating the layout

            ViewHolder vhItem = new ViewHolder(v,viewType,context,drawerL); //Creating ViewHolder and passing the object of type view

            return vhItem;

        } else if (viewType == TYPE_HEADER) {

            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.header,parent,false); //Inflating the layout

            ViewHolder vhHeader = new ViewHolder(v,viewType,context,drawerL);

            return vhHeader;
        }
        return null;

    }

    //Show item in a row. It's necessary say position and type
    @Override
    public void onBindViewHolder(AdapterDrawer.ViewHolder holder, int position) {
        if(holder.Holderid ==1) {//Nor Text and image?
            holder.textView.setText(mNavTitles[position - 1]); // Setting the Text with the array of our Titles
            holder.imageView.setImageResource(mIcons[position -1]);// Settimg the image with array of our icons
        }
        else{
            holder.profile.setImageResource(profile);
            holder.Name.setText(name);
            holder.email.setText(email);
        }
    }


    @Override
    public int getItemCount() {
        return mNavTitles.length+1; // Elements + header
    }


    // With the following method we check what type of view is being passed
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