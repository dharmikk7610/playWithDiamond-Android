package Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.dimandgameproject.R;


public class Loginactivity extends AppCompatActivity {


    Button btnLogin;
    TextView tvSignup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);

        setContentView(R.layout.activity_loginactivity);


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {

            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());

            v.setPadding(
                    systemBars.left,
                    systemBars.top,
                    systemBars.right,
                    systemBars.bottom
            );

            return insets;
        });



        // Find Views

        btnLogin = findViewById(R.id.btnLogin);
        tvSignup = findViewById(R.id.tvSignup);



        // Card Animation

        Animation animation =
                AnimationUtils.loadAnimation(
                        this,
                        R.anim.fade_in
                );


        findViewById(R.id.loginCard)
                .startAnimation(animation);



        // Login Button Click

        btnLogin.setOnClickListener(v -> {

            // Add login authentication here

        });



        // Open Signup Page

        tvSignup.setOnClickListener(v -> {

            Intent intent =
                    new Intent(
                            Loginactivity.this,
                            Signupactivity.class
                    );

            startActivity(intent);

        });


    }
}