package uwi.comp6901.klbakery.ui;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import uwi.comp6901.klbakery.R;
import uwi.comp6901.klbakery.db.entity.User;
import uwi.comp6901.klbakery.viewModel.UserViewModel;

public class LoginActivity extends AppCompatActivity {

    private UserViewModel userViewModel;
    private LiveData<User> user;

    private TextView userEmail;
    private TextView userPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userEmail = findViewById(R.id.et_user_email);
        userPassword = findViewById(R.id.et_user_password);


        userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        //User kumar = userViewModel.getUser("kumar@gmail.com").getValue();
        //String kumar = userViewModel.test();

        //Toast.makeText(this, "kumar " + kumar , Toast.LENGTH_SHORT).show();

        userViewModel.getUser("kumar@gmail.com").observe(this, new Observer<User>() {
            @Override
            public void onChanged(@Nullable User user) {
                //update RecyclerView
                Toast.makeText(LoginActivity.this, "onChanged", Toast.LENGTH_SHORT).show();
                //LoginActivity.this.user = user;

            }
        });
        Button btnLogin =findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                 String userEmailString = userEmail.getText().toString().trim();
                 String userPasswordString = userPassword.getText().toString().trim();

                //user = userViewModel.getUser(userEmailString)


                 user = userViewModel.getUser(userEmailString);

                if (user.getValue() == null) {
                    Toast.makeText(LoginActivity.this, "User : " + userEmailString + " Does not exist", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(userEmailString.isEmpty() && userPasswordString.isEmpty() ){
                    Toast.makeText(LoginActivity.this, "Please Enter both User email and Password to Login", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (validate(user.getValue(), userPassword.getText().toString())){
                    Toast.makeText(LoginActivity.this, "Login Success", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(LoginActivity.this, "Login Failure", Toast.LENGTH_SHORT).show();
                }
                return;
            }
        });
    }

    private boolean validate(User user, String password){
        return userViewModel.validate(user, password.trim());
    }


}
