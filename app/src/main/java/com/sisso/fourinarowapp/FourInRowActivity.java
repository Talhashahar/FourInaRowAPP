package com.sisso.fourinarowapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.ImageView;

import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by Tal, Avi & Tal on 22/06/2018.
 */

public class FourInRowActivity extends Activity {

    private final int MAX_COL = 6;
    private final int MIN_COL = 1;
    private final int MAX_ROW = 6;
    private final int MIN_ROW = 1;
    private final int RED = 11;
    private final int GREEN = 5;
    private final int FIRST_COLUMN = 0;
    private final int SECOND_COLUMN = 1;
    private final int THIRD_COLUMN = 2;
    private final int FOURTH_COLUMN = 3;
    private final int FIFTH_COLUMN = 4;
    private final int SIXTH_COLUMN = 5;
    private final int SEVENTH_COLUMN = 6;
    private int mTurn = RED;

    private int[][] mGameState =
                    {{0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0}};
    private HashMap<String, ImageView> mBoardView = new HashMap<String, ImageView>();
    private HashSet<String> mBoardPlaceTaken = new HashSet<>();

    private GridLayout mGridLayout;

    private ImageView mFirstColumn;
    private ImageView mSecondColumn;
    private ImageView mThirdColumn;
    private ImageView mFourthColumn;
    private ImageView mFifthColumn;
    private ImageView mSixthColumn;
    private ImageView mSeventhColumn;
    private FrameLayout mBoardLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.four_in_a_row_layout);

        mFirstColumn = (ImageView) findViewById(R.id.first_column);
        mSecondColumn = (ImageView) findViewById(R.id.second_column);
        mThirdColumn = (ImageView) findViewById(R.id.third_column);
        mFourthColumn = (ImageView) findViewById(R.id.fourth_column);
        mFifthColumn = (ImageView) findViewById(R.id.fifth_column);
        mSixthColumn = (ImageView) findViewById(R.id.sixth_column);
        mSeventhColumn = (ImageView) findViewById(R.id.seventh_column);
        mBoardLayout = (FrameLayout) findViewById(R.id.board_bg);
        mGridLayout = (GridLayout) findViewById(R.id.gridlayout);


        setBoard();
        setListeners();
    }

    private void setBoard(){
        for(int i = 0; i < mGridLayout.getChildCount(); i++){
            mBoardView.put((String)mGridLayout.getChildAt(i).getTag(),
                    (ImageView)mGridLayout.getChildAt(i));
        }
    }

    private void setListeners() {

        mFirstColumn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playTurn(FIRST_COLUMN, view);
            }
        });

        mSecondColumn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playTurn(SECOND_COLUMN, view);
            }
        });

        mThirdColumn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playTurn(THIRD_COLUMN, view);
            }
        });

        mFourthColumn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playTurn(FOURTH_COLUMN, view);
            }
        });

        mFifthColumn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playTurn(FIFTH_COLUMN, view);
            }
        });

        mSixthColumn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playTurn(SIXTH_COLUMN, view);
            }
        });

        mSeventhColumn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playTurn(SEVENTH_COLUMN, view);
            }
        });
    }


    private void playTurn(int columnNumber, View view){
        if(isFreeSpaceInColumn(columnNumber)){
            ImageView imageView = new ImageView(FourInRowActivity.this);
            ViewGroup.LayoutParams lp = mBoardView.get((String)view.getTag()).getLayoutParams();
            imageView.setLayoutParams(lp);
            imageView.setX(mBoardView.get((String)view.getTag()).getX() + 60);
            imageView.setY(mBoardView.get((String)view.getTag()).getY());

            mBoardLayout.addView(imageView, 0);
            mBoardLayout.invalidate();
            play(mTurn, columnNumber, imageView);
        }
    }
    private void play(int type, int column, ImageView v){

        switch (type){
            case RED:{
                for(int i = mGridLayout.getRowCount()-1; i >= 0; i--){
                    if(!mBoardPlaceTaken.contains(String.valueOf(i) + String.valueOf(column))){
                        mGameState[i-1][column] = RED;
                        mBoardPlaceTaken.add(String.valueOf(i) + String.valueOf(column));
                        ImageView imageView = mBoardView.get(String.valueOf(i) + String.valueOf(column));
                        v.setImageDrawable(getResources().getDrawable(R.drawable.circle_red_shape_view));
                        v.animate().translationX(imageView.getX() + 60)
                                .translationY(imageView.getY())
                                .setDuration(1000).start();
                        mTurn = GREEN;
                        if(isAnyoneWon(i, column, RED)){
                            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                                    this);
                            alertDialogBuilder.setTitle("game over");

                            alertDialogBuilder
                                    .setMessage("Click yes to exit!")
                                    .setCancelable(false)
                                    .setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog,int id) {
                                            // if this button is clicked, close
                                            // current activity
                                            dialog.cancel();
                                        }
                                    })
                                    .setNegativeButton("No",new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog,int id) {
                                            // if this button is clicked, just close
                                            // the dialog box and do nothing
                                            dialog.cancel();
                                        }
                                    });

                            // create alert dialog
                            AlertDialog alertDialog = alertDialogBuilder.create();

                            // show it
                            alertDialog.show();
                        }
                        break;
                    }
                }

                break;
            }case GREEN:{
                for(int i = mGridLayout.getRowCount()-1; i >= 0 ; i--){
                    if(!mBoardPlaceTaken.contains(String.valueOf(i) + String.valueOf(column))){
                        mGameState[i-1][column] = GREEN;
                        mBoardPlaceTaken.add(String.valueOf(i) + String.valueOf(column));
                        ImageView imageView = mBoardView.get(String.valueOf(i) + String.valueOf(column));
                        v.setImageDrawable(getResources().getDrawable(R.drawable.circle_green_shape_view));
                        v.animate().translationX(imageView.getX() + 60)
                                .translationY(imageView.getY())
                                .setDuration(1000).start();
                        mTurn = RED;
                        if(isAnyoneWon(i, column, GREEN)){
                            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                                    this);
                            alertDialogBuilder.setTitle("game over");

                            alertDialogBuilder
                                    .setMessage("Click yes to exit!")
                                    .setCancelable(false)
                                    .setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog,int id) {
                                            // if this button is clicked, close
                                            // current activity
                                            dialog.cancel();
                                        }
                                    })
                                    .setNegativeButton("No",new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog,int id) {
                                            // if this button is clicked, just close
                                            // the dialog box and do nothing
                                            dialog.cancel();
                                        }
                                    });

                            // create alert dialog
                            AlertDialog alertDialog = alertDialogBuilder.create();

                            // show it
                            alertDialog.show();
                        }
                        break;
                    }
                }
            }
        }
    }

    private boolean isFreeSpaceInColumn(int columnNumber){
        return !mBoardPlaceTaken.contains("1" + String.valueOf(columnNumber));
    }

    private boolean isAnyoneWon(int row, int col, int turn){
        if(row < MAX_ROW){
            if(mGameState[row-1][col] / turn > 0){
                mGameState[row-1][col] += turn;
                mGameState[row][col] = mGameState[row-1][col];
                if(mGameState[row][col] == 44 || mGameState[row][col] == 20){
                    return true;
                }
            }

            if(mGameState[row+1][col] / turn > 0){
                mGameState[row+1][col] += turn;
                mGameState[row][col] = mGameState[row+1][col];
                if(mGameState[row][col] == 44 || mGameState[row][col] == 20){
                    return true;
                }
            }
        }
        return false;
    }
}
