package com.sisso.fourinarowapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by Tal, Avi & Tal on 22/06/2018.
 */

public class FourInRowGameActivity extends Activity {

    private final int GRAY = 0;
    private final int RED = 1;
    private final int GREEN = 2;
    private int mTurn = RED;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.four_in_row_game_layout);

    }

    public void play(View view){
        if((view.getTag().equals(String.valueOf(GRAY)))){
            if(mTurn == RED){
                ((ImageView)view).setImageDrawable(getResources().getDrawable(R.drawable.circle_red_shape_view));
                view.setTag(RED);
                mTurn = GREEN;
            } else{
                ((ImageView)view).setImageDrawable(getResources().getDrawable(R.drawable.circle_green_shape_view));
                view.setTag(GREEN);
                mTurn = RED;
            }
        }
    }

}
