package com.chowdhury.ashik.connectx;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView winnerMessage;
    LinearLayout layout;
    GridLayout gridLayout;
    boolean gameIsActive = true;
    // 0 Equals Yeallow
    int activePlayer = 0;
    // 2 means unplayed
    int[] gameState = {2,2,2,2,2,2,2,2,2};

    int[][] winningPosition = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        winnerMessage = findViewById(R.id.winnerMessage);
        layout = findViewById(R.id.playAgainLayout);
        gridLayout = findViewById(R.id.gridLayout);
    }

    public void dropIn(View view) {
        ImageView counter = (ImageView) view;

        int tabCounter = Integer.parseInt(counter.getTag().toString());

        if (gameState[tabCounter] == 2 && gameIsActive){
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

                    //Someone has win!!
                    gameIsActive = false;
                    String winner = "Red";
                    if (gameState[winningPosition[0]] == 0){
                      winner = "Yellow";
                    }
                    winnerMessage.setText(winner+" Has Won!");
                    layout.setVisibility(View.VISIBLE);

                }else{
                    boolean gameIsOver = true;
                    for (int counterState : gameState){
                        if (counterState == 2) gameIsOver = false;
                    }
                    if (gameIsOver){
                        winnerMessage.setText("It's A Draw!");
                        layout.setVisibility(View.VISIBLE);
                    }
                }
            }
        }
    }

    public void playAgain(View view) {
       layout.setVisibility(View.INVISIBLE);
       activePlayer = 0;
       gameIsActive = true;
       for (int i = 0; i<gameState.length; i++){
           gameState[i] = 2;
       }
       for (int i = 0; i<gridLayout.getChildCount(); i++){
           ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);
       }
    }
}
