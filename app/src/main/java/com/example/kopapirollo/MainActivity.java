package com.example.kopapirollo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Button buttonRock;
    private Button buttonPaper;
    private Button buttonScissors;
    private TextView textViewResult;
    private ImageView imageViewPlayer;
    private ImageView imageViewComputer;
    private Random random;
    private int computerChoice;
    private int playerChoice;
    private int winCounter;
    private int loseCounter;
    private AlertDialog.Builder alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        buttonRock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageViewPlayer.setImageResource(R.drawable.rock);
                playerChoice = 0;
                rule();
                if (winCounter == 3) {
                    alertDialog.setTitle("Győzelem").create().show();
                } else if (loseCounter == 3) {
                    alertDialog.setTitle("Vereség").create().show();
                }
            }
        });

        buttonPaper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageViewPlayer.setImageResource(R.drawable.paper);
                playerChoice = 1;
                rule();
                if (winCounter == 3) {
                    alertDialog.setTitle("Győzelem").create().show();
                } else if (loseCounter == 3) {
                    alertDialog.setTitle("Vereség").create().show();
                }
            }
        });

        buttonScissors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageViewPlayer.setImageResource(R.drawable.scissors);
                playerChoice = 2;
                rule();
                if (winCounter == 3) {
                    alertDialog.setTitle("Győzelem").create().show();
                } else if (loseCounter == 3) {
                    alertDialog.setTitle("Vereség").create().show();
                }
            }
        });
    }

        public void init(){
            imageViewPlayer = findViewById(R.id.ImageViewPlayer);
            imageViewComputer = findViewById(R.id.ImageViewComputer);
            textViewResult = findViewById(R.id.textViewResult);
            buttonRock = findViewById(R.id.buttonRock);
            buttonPaper = findViewById(R.id.buttonPaper);
            buttonScissors = findViewById(R.id.buttonScissors);
            winCounter = 0;
            loseCounter = 0;
            computerChoice = 0;
            alertDialog = new AlertDialog.Builder(MainActivity.this)
                    .setTitle("Győzelem")
                    .setMessage("Szeretne új játékot játszani?")
                    .setNegativeButton("Nem, kilépek", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            finish();
                        }
                    })
                    .setPositiveButton("Igen", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            newGame();
                        }
                    })
                    .setCancelable(false);
        }
        public void rule(){
            random = new Random();
            computerChoice = random.nextInt(3);
            if (computerChoice == 0) {
                imageViewComputer.setImageResource(R.drawable.rock);
            } else if (computerChoice == 1) {
                imageViewComputer.setImageResource(R.drawable.paper);
            }else{
                imageViewComputer.setImageResource(R.drawable.scissors);
            }
            if((playerChoice == 0 && computerChoice == 2)||(playerChoice == 1 && computerChoice == 0) || (playerChoice == 2 && computerChoice == 1)){
                Toast.makeText(this, "Győztél", Toast.LENGTH_SHORT).show();
                winCounter++;
            } else if (playerChoice == computerChoice) {
                Toast.makeText(this, "Döntetlen", Toast.LENGTH_SHORT).show();

            }else{
                Toast.makeText(this, "Vesztettél", Toast.LENGTH_SHORT).show();
                loseCounter++;
            }
            textViewResult.setText("Eredmény: Ember: " + winCounter + "Computer: " + loseCounter);
        }
        public void newGame(){
            playerChoice = 0;
            computerChoice = 0;
            imageViewPlayer.setImageResource(R.drawable.rock);
            imageViewComputer.setImageResource(R.drawable.rock);
            winCounter = 0;
            loseCounter = 0;
            textViewResult.setText("Eredmény: Ember: 0 Computer : 0");
        }
    }
