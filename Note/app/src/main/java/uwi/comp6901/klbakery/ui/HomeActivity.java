package uwi.comp6901.klbakery.ui;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import uwi.comp6901.klbakery.R;
import uwi.comp6901.klbakery.db.entity.User;
import uwi.comp6901.klbakery.ui.adapters.UserAdapter;
import uwi.comp6901.klbakery.ui.drivers.DriverActivity;
import uwi.comp6901.klbakery.viewModel.UserViewModel;

public class HomeActivity extends AppCompatActivity {
    public static final int REGISTER_REQUEST = 1;

    public static final String EXTRA_USER_ID = "uwi.comp6901.klbakery.ui.EXTRA_USER_ID";


    UserViewModel userViewModel;
    EditText mEmail;
    EditText mPassword;
    //User user;
    //List<User> allUsers


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

       mEmail = (EditText) findViewById(R.id.text_view_home_email);
       mPassword = (EditText) findViewById(R.id.text_view_home_password);


        //open Registration form

        FloatingActionButton buttonRegister = findViewById(R.id.btn_home_register);
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, RegisterActivity.class);
                startActivityForResult(intent, REGISTER_REQUEST);
            }
        });


        //create and observe recycler view

        //Recyclerview code
        /*
        RecyclerView recyclerView = findViewById(R.id.recycler_view_user);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final UserAdapter adapter = new UserAdapter();
        recyclerView.setAdapter(adapter);
        */
        //test getting just a user



        //code used for the recyclerview
        /*
        userViewModel.getAllUsers().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(@Nullable List<User> notes) {
                //update RecyclerView
                //Toast.makeText(MainActivity.this, "onChanged", Toast.LENGTH_SHORT).show();
                adapter.setUsers(notes);
            }
        });*/





        userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);


        //open Login Form
        FloatingActionButton buttonLogin = findViewById(R.id.btn_home_login);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userViewModel.getUser(mEmail.getText().toString().trim()).observe(HomeActivity.this, new Observer<User>() {
                    @Override
                    public void onChanged(@Nullable User user) {
                        if (user != null) {
                            //verify password and open relevant activity screen
                            if (user.getUser_password().equals(mPassword.getText().toString().trim())) {
                                loadProfile(user);
                                Toast.makeText(HomeActivity.this, "Successfully logged on", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(HomeActivity.this, "Incorrect password", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(HomeActivity.this, "Invalid email", Toast.LENGTH_SHORT).show();
                        }
                    }

                });

                //checkUser();
            }
        });



    }

    private void loadProfile(User user){
        String customerType = getResources().getString(R.string.customer_type);
        String driverType = getResources().getString(R.string.driver_type);
        String storeRepType = getResources().getString(R.string.store_rep_type);

        Intent intent;


        String userType = user.getUser_type();

        if (userType.equals(customerType)){
            Toast.makeText(this, "TO DO implementation for Customer type", Toast.LENGTH_SHORT).show();
        }else if (userType.equals(driverType)){
            intent = new Intent(this, DriverActivity.class);
            intent.putExtra(EXTRA_USER_ID, user.getId());
            startActivity(intent);

        }else if (userType.equals(storeRepType)){
            Toast.makeText(this, "TO DO implementation for Store Rep type", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "System Error, User has an invalid type", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REGISTER_REQUEST && resultCode == RESULT_OK){


            String email = data.getStringExtra(RegisterActivity.EXTRA_EMAIL);
            String password = data.getStringExtra(RegisterActivity.EXTRA_PASSWORD);
            String username = data.getStringExtra(RegisterActivity.EXTRA_USER_NAME);
            String address = data.getStringExtra(RegisterActivity.EXTRA_ADDRESS);

            //test if user email already exists
            User temp = userViewModel.getUser(email).getValue();
            if (temp != null){
                Toast.makeText(this, "Account exist for email " + email, Toast.LENGTH_SHORT).show();
                return;
            }

            User user = new User("Customer",username, email, password,address );



            userViewModel.insert(user);
            Toast.makeText(this, "User Added ", Toast.LENGTH_SHORT).show();

        }else{
            Toast.makeText(this, "User not saved", Toast.LENGTH_SHORT).show();
        }

    }


}
