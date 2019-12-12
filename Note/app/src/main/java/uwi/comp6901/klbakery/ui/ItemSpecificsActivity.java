package uwi.comp6901.klbakery.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.preference.PreferenceManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import uwi.comp6901.klbakery.R;
import androidx.appcompat.app.AppCompatActivity;

public class ItemSpecificsActivity extends AppCompatActivity {
    SQLiteDatabase bakeryappdb;
    Cursor resultSet;
    SharedPreferences sharedPref;
    String item;
    TextView txtName, txtDescription, txtItemPrice, txtNotification;
    Spinner spinner;
    ImageView imgItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_specifics);
    }

    protected void onResume(){
        super.onResume();

        bakeryappdb = openOrCreateDatabase("bakery db", MODE_PRIVATE, null);
        sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        final SharedPreferences.Editor editor = sharedPref.edit();

        item = sharedPref.getString("Item", "");
        final String username = sharedPref.getString("Username", "");
        final String password = sharedPref.getString("Password", "");

        resultSet = bakeryappdb.rawQuery("SELECT * FROM Goods WHERE Name = '" + item + "'", null);
        resultSet.moveToFirst();

        txtName = (TextView)findViewById(R.id.txtName);
        txtName.setText(item);

        imgItem = (ImageView)findViewById(R.id.imgItem);

        //Set image dynamically with path based on naming convention
        Resources res = getResources();
        String mDrawableName = item.replace(" ", "");
        mDrawableName = mDrawableName.toLowerCase();
        System.out.println(mDrawableName);
        int resID = res.getIdentifier(mDrawableName , "drawable", getPackageName());
        Drawable drawable = res.getDrawable(resID);
        imgItem.setImageDrawable(drawable);

        txtDescription = (TextView) findViewById(R.id.txtDescription);
        txtDescription.setText(resultSet.getString(1));

        txtItemPrice = (TextView)findViewById(R.id.txtItemPrice);
        final Double price = Double.parseDouble(resultSet.getString(3));
        txtItemPrice.setText("$" + resultSet.getString(3));

        spinner = (Spinner)findViewById(R.id.spinner);
        String[] quantities = {"1", "2", "3", "4", "5"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.single_row, R.id.textView, quantities);
        spinner.setAdapter(adapter);

        Button btnAdd = (Button)findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int amount = Integer.parseInt(spinner.getSelectedItem().toString());
                Double total = price * amount;
                String addToShoppingCart = amount + " " + item + "s for $" + total + ".";

                //Grab current Inventory
                resultSet = bakeryappdb.rawQuery("SELECT ShoppingCart, CartTotal FROM Accounts WHERE Username = '" + username + "' AND Password = '" + password + "'", null);
                resultSet.moveToFirst();
                String shoppingCart = resultSet.getString(0);
                Double cartTotal = Double.parseDouble(resultSet.getString(1));

                shoppingCart += addToShoppingCart + System.getProperty("line.separator");
                cartTotal += total;

                bakeryappdb.execSQL("UPDATE Accounts " +
                        "SET ShoppingCart = '" + shoppingCart + "', CartTotal = '" + cartTotal + "' " +
                        "WHERE Username = '" + username + "' AND Password = '" + password + "'");

                //System.out.println(shoppingCart + " Cart Total: " + cartTotal);
                txtNotification = (TextView)findViewById(R.id.txtNotification);
                txtNotification.setText("Successfully added " + amount + " " + item + "(s) to your cart!");
            }
        });

        Button buttonRegister = findViewById(R.id.cart);
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ItemSpecificsActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });


    }
}
