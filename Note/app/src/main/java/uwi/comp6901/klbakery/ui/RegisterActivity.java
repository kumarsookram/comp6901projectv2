package uwi.comp6901.klbakery.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import uwi.comp6901.klbakery.R;

public class RegisterActivity extends AppCompatActivity {

    public static final String EXTRA_EMAIL = "uwi.comp6901.klbakery.ui.EXTRA_EMAIL";
    public static final String EXTRA_PASSWORD = "uwi.comp6901.klbakery.ui.EXTRA_PASSWORD";
    public static final String EXTRA_USER_NAME = "uwi.comp6901.klbakery.ui.EXTRA_USER_NAME";
    public static final String EXTRA_ADDRESS = "uwi.comp6901.klbakery.ui.EXTRA_ADDRESS";


    private EditText mEmail;
    private EditText mPassword;
    private EditText mPasswordRetype;
    private EditText mUserName;
    private EditText mAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mEmail = findViewById(R.id.et_user_reg_email);
        mPassword = findViewById(R.id.et_user_reg_password_1);
        mPasswordRetype = findViewById(R.id.et_user_reg_password_2);
        mUserName = findViewById(R.id.et_user_reg_name);
        mAddress = findViewById(R.id.et_user_reg_address);


        Button btnRegister = findViewById(R.id.btn_register);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveUser();
                //Toast.makeText(RegisterActivity.this, "User Added", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_note_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_note:
                saveUser();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void saveUser(){
        String email = mEmail.getText().toString();
        String password_1 = mPassword.getText().toString();
        String password_2 = mPasswordRetype.getText().toString();
        String username = mUserName.getText().toString();
        String address = mAddress.getText().toString();


        //verify there is an email address

        if (email.trim().isEmpty()){
            Toast.makeText(this, "Email Address is required", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!password_1.trim().equals(password_2.trim())){
            Toast.makeText(this, "Passwords does not match", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent data = new Intent();
        data.putExtra(EXTRA_EMAIL, email);
        data.putExtra(EXTRA_PASSWORD, password_1);
        data.putExtra(EXTRA_USER_NAME, username);
        data.putExtra(EXTRA_ADDRESS, address);


        setResult(RESULT_OK,data);
        finish();
    }


}
