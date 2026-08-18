package Activities;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.dimandgameproject.R;

import Api.Apiclient;
import Api.Userapiservice;
import Model.Signupmodel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Tag;

public class Signupactivity extends AppCompatActivity {

    private LinearLayout card;
    private Button btnSignup;
    private TextView tvLogin;

    private EditText etFirstName ;
    private EditText etEmail;
    private EditText etPassword;
    private EditText etLastName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_signupactivity);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top,
                    systemBars.right, systemBars.bottom);
            return insets;
        });

        initViews();
        setupAnimation();
        setupListeners();
    }

    private void initViews() {

        card = findViewById(R.id.card);

        btnSignup = findViewById(R.id.btnSignup);

        tvLogin = findViewById(R.id.tvLogin);

        etFirstName = findViewById(R.id.etFName);
        etLastName = findViewById(R.id.etLName);

        etEmail = findViewById(R.id.etEmail);

        etPassword = findViewById(R.id.etPassword);



    }

    private void setupAnimation() {

        Animation animation =
                AnimationUtils.loadAnimation(this, R.anim.fade_up);

        card.startAnimation(animation);

    }

    private void setupListeners() {

        tvLogin.setOnClickListener(v -> {

            startActivity(
                    new Intent(
                            Signupactivity.this,
                            Loginactivity.class
                    )
            );

        });


        btnSignup.setOnTouchListener((v, event) -> {

            switch (event.getAction()) {

                case MotionEvent.ACTION_DOWN:

                    v.animate()
                            .scaleX(0.95f)
                            .scaleY(0.95f)
                            .setDuration(100)
                            .start();

                    break;

                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_CANCEL:

                    v.animate()
                            .scaleX(1f)
                            .scaleY(1f)
                            .setDuration(100)
                            .start();

                    break;
            }

            return false;

        });


        btnSignup.setOnClickListener(v -> registerUser());

    }

    private void registerUser() {

        String fname = etFirstName.getText().toString().trim();

        String email = etEmail.getText().toString().trim();

        String password = etPassword.getText().toString().trim();

        String lname = etLastName.getText().toString().trim();


        // Validation

        if (fname.isEmpty()) {
            etFirstName.setError("Enter Name");
            return;
        }

        if (email.isEmpty()) {
            etEmail.setError("Enter Email");
            return;
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            etEmail.setError("Enter Valid Email");
            return;
        }

//        if (etLastName.isEmpty()) {
//            etLastName.setError("Enter Valid Mobile Number");
//            return;
//        }

        if (password.length() < 6) {
            etPassword.setError("Password must be at least 6 characters");
            return;
        }


        Signupmodel model = new Signupmodel();

        model.setFirstName(fname);
        model.setEmail(email);
        model.setPassword(password);
        model.setLastName(lname);
        model.setCredit(1000);


        btnSignup.setEnabled(false);


        Userapiservice api =
                Apiclient.getRetrofit()
                        .create(Userapiservice.class);


        api.signup(model).enqueue(new Callback<Object>() {

            @Override
            public void onResponse(Call<Object> call,
                                   Response<Object> response) {

                btnSignup.setEnabled(true);

                if (response.isSuccessful()) {

                    showWelcomeDialog(fname);

                } else {
                    System.out.println(response);
                    Toast.makeText(
                            Signupactivity.this,
                            "Signup Failed (" + response.code() + ")",
                            Toast.LENGTH_LONG
                    ).show();

                }

            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {

                Log.e("SIGNUP_API", "Request Failed", t);

                Log.e("SIGNUP_API", "Message: " + t.getMessage());

                t.printStackTrace();

                Toast.makeText(
                        Signupactivity.this,
                        t.getMessage(),
                        Toast.LENGTH_LONG
                ).show();
            }
        });

    }


    private void showWelcomeDialog(String username) {

        new AlertDialog.Builder(this)

                .setTitle("🎉 Welcome " + username)

                .setMessage(
                        "Your account has been created successfully.\n\nReady to play Diamond Game?"
                )

                .setCancelable(false)

                .setPositiveButton("PLAY", (dialog, which) -> {

                    Intent intent =
                            new Intent(
                                    Signupactivity.this,
                                    Gameactivity.class
                            );

                    intent.putExtra("username", username);

                    startActivity(intent);

                    finish();

                })

                .show();

    }

}