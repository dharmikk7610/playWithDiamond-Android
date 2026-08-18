package Activities;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dimandgameproject.R;

import java.util.ArrayList;
import java.util.Collections;


public class PlayGameActivity extends AppCompatActivity {


    GridLayout gridLayout;
    TextView tvMoney;

    ArrayList<String> gameBoxes = new ArrayList<>();

    int diamondsFound = 0;
    int coins = 0;

    boolean gameOver = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_play_game);


        gridLayout = findViewById(R.id.gridLayout);
        tvMoney = findViewById(R.id.tvMoney);


        createGameData();

        createBoxGrid();

    }



    // Create hidden diamonds and bomb

    private void createGameData(){


        gameBoxes.clear();


        // 21 Empty boxes

        for(int i = 0; i < 21; i++){

            gameBoxes.add("EMPTY");

        }


        // 3 Diamond boxes

        for(int i = 0; i < 3; i++){

            gameBoxes.add("DIAMOND");

        }


        // 1 Bomb box

        gameBoxes.add("BOMB");



        Collections.shuffle(gameBoxes);

    }




    // Create 5x5 UI boxes

    private void createBoxGrid(){


        int screenWidth =
                getResources()
                        .getDisplayMetrics()
                        .widthPixels;



        int boxSize =
                (screenWidth - 120) / 5;



        for(int i = 0; i < 25; i++){


            Button box = new Button(this);


            box.setText("?");

            box.setTextSize(18);

            box.setTextColor(Color.WHITE);


            box.setAllCaps(false);


            box.setPadding(0,0,0,0);


            box.setBackgroundResource(
                    R.drawable.box_bg
            );



            GridLayout.LayoutParams params =
                    new GridLayout.LayoutParams();


            params.width = boxSize;

            params.height = boxSize;


            params.setMargins(
                    5,
                    5,
                    5,
                    5
            );


            params.setGravity(
                    Gravity.CENTER
            );


            box.setLayoutParams(params);



            int position = i;



            box.setOnClickListener(v -> {


                if(!gameOver){

                    openBox(
                            box,
                            position
                    );

                }


            });



            gridLayout.addView(box);


        }


    }





    private void openBox(Button box,int position){



        String result =
                gameBoxes.get(position);



        box.setEnabled(false);



        if(result.equals("DIAMOND")){


            box.setText("💎");


            diamondsFound++;


            coins += 50;



            tvMoney.setText(
                    "Coins : " + coins
            );



            Toast.makeText(
                    this,
                    "Diamond Found 💎",
                    Toast.LENGTH_SHORT
            ).show();




            if(diamondsFound == 3){


                gameOver=true;


                Toast.makeText(
                        this,
                        "🎉 YOU WIN\nCoins : "+coins,
                        Toast.LENGTH_LONG
                ).show();


                disableAllBoxes();


            }



        }



        else if(result.equals("BOMB")){


            box.setText("💣");


            coins = 0;


            tvMoney.setText(
                    "Coins : 0"
            );


            gameOver=true;



            Toast.makeText(
                    this,
                    "💣 BOOM! YOU LOSE",
                    Toast.LENGTH_LONG
            ).show();



            disableAllBoxes();


        }



        else{


            box.setText("❌");


        }


    }





    private void disableAllBoxes(){


        for(int i=0;i<gridLayout.getChildCount();i++){


            gridLayout
                    .getChildAt(i)
                    .setEnabled(false);


        }


    }


}