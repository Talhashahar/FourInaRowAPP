package com.sisso.fourinarowapp;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;

/**
 * Created by Tal, Avi & Tal on 22/06/2018.
 */

public class MainActivity extends Activity{

    private final int BOARD_WIDTH_SIZE = 7;
    private final int BOARD_HEIGHT_SIZE = 6;
    private final int RED = 0;
    private final int GREEN = 1;
    private final int GRAY = 2;
    private int mTurn = RED;

    private ImageView[][] mBoard = new ImageView[BOARD_HEIGHT_SIZE][BOARD_WIDTH_SIZE];

    private RelativeLayout mRootView;
    private ImageView mFirstRow;
    private ImageView mSecondRow;
    private ImageView mThirdRow;
    private ImageView mFourthRow;
    private ImageView mFifthRow;
    private ImageView mSixthRow;
    private ImageView mSeventhRow;
    private TableLayout mTableLayout;
    private TableRow mClickableRow;
    private FrameLayout mBoardRootView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity_layout);

        mRootView = (RelativeLayout) findViewById(R.id.root_view);
        mFirstRow = (ImageView) findViewById(R.id.first_column);
        mSecondRow = (ImageView) findViewById(R.id.second_column);
        mThirdRow = (ImageView) findViewById(R.id.third_column);
        mFourthRow = (ImageView) findViewById(R.id.fourth_column);
        mFifthRow = (ImageView) findViewById(R.id.fifth_column);
        mSixthRow = (ImageView) findViewById(R.id.sixth_column);
        mSeventhRow = (ImageView) findViewById(R.id.seventh_column);
        mClickableRow = (TableRow) findViewById(R.id.click_row);
        mTableLayout = (TableLayout) findViewById(R.id.game_board);
//        mBoardRootView = (FrameLayout) findViewById(R.id.board_root_view);

//
//        mBoardRootView.post(new Runnable() {
//            @Override
//            public void run() {
//
//                DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
//                float newWidth = (float)displayMetrics.widthPixels / mBoardRootView.getWidth();
//
//                ViewGroup.LayoutParams tlp = mTableLayout.getLayoutParams();
//                tlp.width = (int)(mBoardRootView.getWidth() * newWidth);
//                tlp.height = (int)(mBoardRootView.getHeight() * newWidth);
//                mTableLayout.setLayoutParams(tlp);
//                mTableLayout.invalidate();
//
//                ViewGroup.LayoutParams lp = mBoardRootView.getLayoutParams();
//                lp.width = (int)(mBoardRootView.getWidth() * newWidth);
//                lp.height = (int)(mBoardRootView.getHeight() * newWidth);
//                mBoardRootView.setLayoutParams(lp);
//                mBoardRootView.invalidate();
//            }
//        });

        setBoard();
        setListeners();
    }

    private void setListeners(){
        mFirstRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImageView imageView = new ImageView(MainActivity.this);
                ViewGroup.LayoutParams lp = mFirstRow.getLayoutParams();
                imageView.setLayoutParams(lp);
                imageView.setX(mFirstRow.getX());
                imageView.setY(((TableRow)mFirstRow.getParent()).getY());
                mClickableRow.addView(imageView);
                mClickableRow.invalidate();
                play(mTurn, 0, imageView);
            }
        });
//        mSecondRow.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                play(mTurn, 1);
//            }
//        });
//        mThirdRow.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                play(mTurn, 2);
//            }
//        });
//        mFourthRow.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                play(mTurn, 3);
//            }
//        });
//        mFifthRow.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                play(mTurn, 4);
//            }
//        });
//        mSixthRow.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                play(mTurn, 5);
//            }
//        });
//        mSeventhRow.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                play(mTurn, 6);
//            }
//        });
    }

    private void setBoard(){
        for(int i = 1; i < mTableLayout.getChildCount(); i++){
            TableRow tableRow = (TableRow)mTableLayout.getChildAt(i);
            for(int j = 0; j < tableRow.getChildCount(); j++){
                ImageView imageView = (ImageView)tableRow.getChildAt(j);
                imageView.setTag(GRAY);
                mBoard[i-1][j] = (ImageView)tableRow.getChildAt(j);
            }
        }
    }

    private void play(int type, int position, View v){

        switch (type){
            case RED:{
                for(int i = (BOARD_HEIGHT_SIZE - 1); i >= 0; i--){
                    if(((int)mBoard[i][position].getTag()) == GRAY){
                        ((ImageView)v).setImageDrawable(getDrawable(R.drawable.circle_red_shape_view));
                        v.animate().translationX(mBoard[i][position].getX())
                                .translationY(((TableRow)mBoard[i][position].getParent()).getY())
                                .start();
//                        mBoard[i][position].setImageDrawable(getDrawable(R.drawable.circle_red_shape_view));
                        mBoard[i][position].setTag(RED);
                        mTurn = GREEN;
                        break;
                    }
                }
                break;
            }case GREEN:{
                for(int i = (BOARD_HEIGHT_SIZE - 1); i >= 0; i--){
                    if(((int)mBoard[i][position].getTag()) == GRAY){
                        ((ImageView)v).setImageDrawable(getDrawable(R.drawable.circle_green_shape_view));
                        v.animate().translationX(mBoard[i][position].getX())
                                .translationY(((TableRow)mBoard[i][position].getParent()).getY())
                                .start();

//                        mBoard[i][position].setImageDrawable(getDrawable(R.drawable.circle_green_shape_view));
                        mBoard[i][position].setTag(GREEN);
                        mTurn = RED;
                        break;
                    }
                }
                break;
            }
        }
    }
}
