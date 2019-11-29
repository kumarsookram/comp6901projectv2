package uwi.comp6901.klbakery.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import uwi.comp6901.klbakery.R;

public class HomeActivity extends AppCompatActivity {
    public static final int REGISTER_REQUEST = 1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //open Registration form

        FloatingActionButton buttonRegister = findViewById(R.id.btn_home_register);
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, RegisterActivity.class);
                startActivityForResult(intent, REGISTER_REQUEST);
            }
        });

        //open Login Form
        FloatingActionButton buttonLogin = findViewById(R.id.btn_home_login);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

    }
}
