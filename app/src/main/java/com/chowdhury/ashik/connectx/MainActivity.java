package com.chowdhury.ashik.connectx;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    // 0 Equals Yeallow
    int activePlayer = 0;
    // 2 means unplayed
    int[] gameState = {2,2,2,2,2,2,2,2,2};

    int[][] winningPosition = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void dropIn(View view) {
        ImageView counter = (ImageView) view;

        int tabCounter = Integer.parseInt(counter.getTag().toString());

        if (gameState[tabCounter] == 2){
            gameState[tabCounter] = activePlayer;
            counter.setTranslationY(-1000f);
            if (activePlayer == 0){
                counter.setImageResource(R.drawable.yellow);
                activePlayer = 1;
            }else{
                counter.setImageResource(R.drawable.red);
                activePlayer = 0;
            }
            counter.animate().translationYBy(1000f).rotation(360).setDuration(300);
            for (int [] winningPosition : winningPosition){
                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] &&
                        gameState[winningPosition[1]] == gameState[winningPosition[2]] &&
                        gameState[winningPosition[0]] != 2 ){
                    System.out.println(gameState[winningPosition[0]]);

                }
            }
        }
    }

    public void playAgain(View view) {
    }
}
