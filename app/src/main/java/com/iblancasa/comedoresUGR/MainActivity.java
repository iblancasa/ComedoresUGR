package com.iblancasa.comedoresUGR;

import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

    //Títulos para el Drawer
    String TITLES[] = {"Inicio","Soporte","Ver otra","Opción","Similares"};

    //Iconos asociados
    int ICONS[] = {R.drawable.ic_1,R.drawable.ic_2,R.drawable.ic_3,R.drawable.ic_4,R.drawable.ic_5};


    //Nombre a mostrar
    String NAME = "Israel Blancas";

    //Email a mostrar
    String EMAIL = "iblancasa [@] gmail.com";

    //Imagen a mostrar
    int PROFILE = R.drawable.user;


    //Toolbar
    private Toolbar toolbar;

    //Declaración del RecyclerView
    RecyclerView RecyclerView;

    //Adapter para RecyclerView
    RecyclerView.Adapter Adapter;

    //Layout manager
    RecyclerView.LayoutManager LayoutManager;

    //Drawer Layout
    DrawerLayout Drawer;

    //Action Bar Drawer Toggle
    ActionBarDrawerToggle mDrawerToggle;


    com.iblancasa.comedoresUGR.Menu menu;


    // RecyclerView menu
    RecyclerView recyclerMenuSemana;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        TextView p1,p2,p3;
        p1 = (TextView) findViewById(R.id.primerPlato);
        p2 = (TextView) findViewById(R.id.segundoPlato);
        p3 = (TextView) findViewById(R.id.tercerPlato);


        menu = new com.iblancasa.comedoresUGR.Menu(p1,p2,p3);

        createLayout();



    }



    private void createLayout(){
        final Context contexto = this;


        //Añadido el toolbar
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);


        RecyclerView = (RecyclerView) findViewById(R.id.RecyclerView);


        RecyclerView.setHasFixedSize(true);//Tamaño fijo



        LayoutManager = new LinearLayoutManager(this);//Layout Manager (linear)

        RecyclerView.setLayoutManager(LayoutManager);//Añadiendo el layout manager



        Drawer = (DrawerLayout) findViewById(R.id.DrawerLayout);

        mDrawerToggle = new ActionBarDrawerToggle(this,Drawer,toolbar,0,0){

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);

            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);


                setContentView(R.layout.activity_semana);
                createLayout();


               recyclerMenuSemana = (RecyclerView) findViewById(R.id.recycler_plato);
                recyclerMenuSemana.setHasFixedSize(true);
                final LinearLayoutManager mLayoutManager = new LinearLayoutManager(contexto);
                recyclerMenuSemana.setLayoutManager(mLayoutManager);

                final RecyclerView.ItemDecoration itemDecoration = new Divider(contexto);
                recyclerMenuSemana.addItemDecoration(itemDecoration);

                final Adapter recyclerAdapterMenu = new Adapter(menu.parse.getSemana(),contexto);
                recyclerMenuSemana.setAdapter(recyclerAdapterMenu);

            }
        };




        Drawer.setDrawerListener(mDrawerToggle);//Añadir listener

        Adapter = new AdapterDrawer(TITLES,ICONS,NAME,EMAIL,PROFILE,this,Drawer);//New adapter

        RecyclerView.setAdapter(Adapter);//Set adapter


        mDrawerToggle.syncState();              //Sync State of drawer
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Añadiendo items
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }
}