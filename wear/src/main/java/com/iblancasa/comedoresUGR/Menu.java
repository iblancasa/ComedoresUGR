package com.iblancasa.comedoresugr;

import android.app.FragmentManager;
import android.content.Context;
import android.support.wearable.view.GridViewPager;



public class Menu {
    ParserComedor parse;

    public Menu(Context ctx, GridViewPager grid, FragmentManager fm){
        parse = new ParserComedor(ctx,grid,fm);
        parse.execute(new String[]{"http://comedoresugr.tcomunica.org/"});
       
    }

}
