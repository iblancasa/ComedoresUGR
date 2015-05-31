/*
 * Copyright (C) 2014 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.iblancasa.comedoresUGR;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.wearable.view.CardFragment;
import android.support.wearable.view.FragmentGridPagerAdapter;

import android.view.Gravity;

import java.util.ArrayList;



public class GridPagerAdapter extends FragmentGridPagerAdapter {

    private final Context mContext;
    private ArrayList<Page> pages;

    private static class Page {
        String titleRes;
        String textRes;
        int iconRes;
        int cardGravity = Gravity.CENTER_VERTICAL ;
        boolean expansionEnabled = true;
        float expansionFactor = 1.0f;
        int expansionDirection = CardFragment.EXPAND_DOWN;



        public Page(String title, String text, int drawable, int gravity) {
            titleRes = title;
            textRes = text;
            iconRes = drawable;
            cardGravity = gravity;
        }
    }


    public GridPagerAdapter(Context ctx, FragmentManager fm, ArrayList<String> platos, String dia) {
        super(fm);
        mContext = ctx;

        pages = new ArrayList<Page>();

	if(platos.size()>1){
        pages.add( new Page("Primer plato", platos.get(0), R.drawable.bugdroid,
                Gravity.CENTER_VERTICAL));
        pages.add(new Page("Segundo plato", platos.get(0), R.drawable.bugdroid,
                Gravity.CENTER_VERTICAL));

        pages.add(new Page("Tercer plato", platos.get(0), R.drawable.bugdroid,
                Gravity.CENTER_VERTICAL));
	}
	else if(dia=="Domingo"){
        pages.add( new Page("Domingo","El comedor está cerrado" , R.drawable.bugdroid,
                Gravity.CENTER_VERTICAL));
	}
    else{
        pages.add( new Page("ERROR","Algo salió mal" , R.drawable.bugdroid,
                Gravity.CENTER_VERTICAL));
    }


    }

    static final int[] BG_IMAGES = new int[] {
            R.drawable.debug_background_1,
            R.drawable.debug_background_2,
            R.drawable.debug_background_3,
            R.drawable.debug_background_4,
            R.drawable.debug_background_5
    };





    @Override
    public Fragment getFragment(int row, int col) {
        Page page = pages.get(row);
        String title = page.titleRes;
        String text = page.textRes;
        CardFragment fragment = CardFragment.create(title, text, page.iconRes);
        // Advanced settings
        fragment.setCardGravity(page.cardGravity);
        fragment.setExpansionEnabled(page.expansionEnabled);
        fragment.setExpansionDirection(page.expansionDirection);
        fragment.setExpansionFactor(page.expansionFactor);
        return fragment;
    }

    @Override
    public Drawable getBackgroundForRow(int row) {
        return  mContext.getResources().getDrawable(BG_IMAGES[row]);
    }

    @Override
    public int getRowCount() {
        return pages.size();
    }

    @Override
    public int getColumnCount(int rowNum) {
        return 1;
    }
}
