package Activities;


import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dimandgameproject.R;


public class Gameactivity extends AppCompatActivity {


    TextView tvPlayer;
    Button btnStartGame;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_gameactivity);



        tvPlayer = findViewById(R.id.tvPlayer);
        btnStartGame = findViewById(R.id.btnStartGame);



        // Get player name from signup

        String name =
                getIntent().getStringExtra("username");


        if(name != null){

            tvPlayer.setText(
                    "Welcome " + name
            );

        }



        // Button animation

        Animation animation =
                AnimationUtils.loadAnimation(
                        this,
                        R.anim.fade_in
                );


        btnStartGame.startAnimation(animation);



        btnStartGame.setOnClickListener(v -> {


            Intent intent = new Intent(Gameactivity.this , PlayGameActivity.class);
            startActivity(intent);


        });


    }
}