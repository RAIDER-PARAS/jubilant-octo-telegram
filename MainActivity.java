package com.example.connect3game;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

 public class MainActivity extends AppCompatActivity {
    // 0:yellow 1:red
    int[] gameState={2,2,2,2,2,2,2,2,2};
    int[] [] winningPositions ={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    int tie = 3;
    int activeplayer = 0;
    boolean gameActive = true;
    public void dropIn(View view){
        ImageView counter = (ImageView) view;
        int tappedCounter = Integer.parseInt(counter.getTag().toString());
        if (gameState[tappedCounter] == 2 && gameActive) {
            gameState[tappedCounter] = activeplayer;
            counter.setTranslationY(-1500);
            if (activeplayer == 0) {
                counter.setImageResource(R.drawable.yellow);
                activeplayer = 1;
            } else if (activeplayer == 1){
                counter.setImageResource(R.drawable.red);
                activeplayer = 0;
            } else {
                activeplayer = tie;
            }
            counter.animate().translationYBy(1500).rotation(3600).setDuration(300);

            for (int[] winningPosition : winningPositions) {
                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]] && gameState[winningPosition[0]] != 2) {

                    gameActive = false;

                    String Winner = " ";

                    if (activeplayer == 1) {
                        Winner = "Yellow";
                    }else if(activeplayer == 0){
                        Winner = "Red";
                    } else {
                        Winner = "tie";
                        activeplayer = tie;
                    }

                    Button playAgainButton = (Button) findViewById(R.id.playAgainButton);
                    TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);
                    winnerTextView.setText(Winner + " Victory");
                    playAgainButton.setVisibility(View.VISIBLE);
                    winnerTextView.setVisibility(View.VISIBLE);
                }
            }

        }
    }
    public void playAgain (View view){
        Button playAgainButton = (Button) findViewById(R.id.playAgainButton);
        TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);
        playAgainButton.setVisibility(View.INVISIBLE);
        winnerTextView.setVisibility(View.INVISIBLE);
        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);

        for(int i=0; i<gridLayout.getChildCount(); i++) {
            ImageView counter = (ImageView) gridLayout.getChildAt(i);
            counter.setImageDrawable(null);
        }

        for ( int i=0; i<gameState.length; i++ ) {

            gameState[i] = 2;

        }
        activeplayer = 0;
        gameActive = true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

 }
