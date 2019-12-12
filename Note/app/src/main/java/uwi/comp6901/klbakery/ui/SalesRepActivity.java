package uwi.comp6901.klbakery.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.preference.PreferenceManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import uwi.comp6901.klbakery.R;
import androidx.appcompat.app.AppCompatActivity;

public class SalesRepActivity extends AppCompatActivity {
    String username = "";
    String password = "";
    Cursor resultSet;
    SQLiteDatabase bakeryappdb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales_rep);

        bakeryappdb = openOrCreateDatabase("bakery db", MODE_PRIVATE, null);

        //Incase adding new columns to table, uncomment these
        //bakeryappdb.execSQL("DROP TABLE Goods");
        //bakeryappdb.execSQL("DROP TABLE Accounts");

        bakeryappdb.execSQL("CREATE TABLE IF NOT EXISTS Accounts(" +
                "Username VARCHAR," +
                " Password VARCHAR," +
                " Address VARCHAR DEFAULT 'Not Specified'," +
                " Email VARCHAR," +
                " Phone VARCHAR," +
                " Cardholder VARCHAR DEFAULT 'Not Specified'," +
                " Creditcard VARCHAR DEFAULT 'Not Specified'," +
                " ShoppingCart VARCHAR DEFAULT ''," +
                " CartTotal REAL DEFAULT 0.0);");

        bakeryappdb.execSQL("CREATE TABLE IF NOT EXISTS Goods(" +
                "Name VARCHAR," +
                " Description VARCHAR," +
                " Category VARCHAR," +
                " Price VARCHAR);");

        bakeryappdb.execSQL("INSERT INTO Accounts (Username, Password, Address, Email, Phone) " +
                "VALUES('admin','admin', '20 Mango lane Road Trinidad', 'jayron@gmail.com', '1-868-3063297');");

        //cake values
        bakeryappdb.execSQL("INSERT INTO Goods VALUES('Chocolate Butter Cream Cake', 'Buttercream cake with chocolate flavouring. Serves 15 people.', 'Cakes', '15.00');");
        bakeryappdb.execSQL("INSERT INTO Goods VALUES('Chocolate Cheese Cake','Creamy chocolate cheesecake with brownie crust. Serves 12 people.','Cakes','25.00');");
        bakeryappdb.execSQL("INSERT INTO Goods VALUES('Mango Cake','Tropical mango cake covered with real mango. Serves 15 people.','Cakes','30.00');");
        bakeryappdb.execSQL("INSERT INTO Goods VALUES('NY Style Cheese Cake','Creamy cheesecake with strawberry sauce. Serves 12 people.','Cakes','35.00');");
        bakeryappdb.execSQL("INSERT INTO Goods VALUES('Oreo Cake','Creamy cheesecake made with Oreo cookies. Serves 12 people.','Cakes','25.00');");
        bakeryappdb.execSQL("INSERT INTO Goods VALUES('Raspberry Cheesecake','Creamy cheesecake made with fresh raspberries. Serves 12 people.','Cakes','25.00');");
        bakeryappdb.execSQL("INSERT INTO Goods VALUES('Raspberry Mousse Cake','Creamy mousse cake made with fresh raspberries. Serves 12 people.','Cakes','20.00');");
        bakeryappdb.execSQL("INSERT INTO Goods VALUES('Strawberry Shortcake','Strawberry shortcake filled with fresh strawberries. Serves 10 people.','Cakes','15.00');");
        bakeryappdb.execSQL("INSERT INTO Goods VALUES('Tiramisu','Coffee-flavoured cake covered with cocoa powder. Serves 10 people.','Cakes','16.00');");
        bakeryappdb.execSQL("INSERT INTO Goods VALUES('Vanilla Buttercream cake','Buttercream cake with vanilla flavouring. Serves 15 people.','Cakes','15.00');");

        //pastry values
        bakeryappdb.execSQL("INSERT INTO Goods VALUES('Apple Pie','Fruit pie filled with fresh apples. Serves 8 people.','Pastries','4.00');");
        bakeryappdb.execSQL("INSERT INTO Goods VALUES('Blueberry Mousse','Creamy mousse portions made with fresh blueberries. Serves 1 person.','Pastries','5.00');");
        bakeryappdb.execSQL("INSERT INTO Goods VALUES('Caramel Flan','Sponge vanilla-flavoured cake covered and filled with caramel. Serves 8 people.','Pastries','4.00');");
        bakeryappdb.execSQL("INSERT INTO Goods VALUES('Chocolate Mousse','Creamy mousse portions made with chocolate. Serves 1 person.','Pastries','5.00');");
        bakeryappdb.execSQL("INSERT INTO Goods VALUES('Cream Horn','Puff pastry filled with whipped cream. Order of 4.','Pastries','6.00');");
        bakeryappdb.execSQL("INSERT INTO Goods VALUES('Fruit Tarts','Shortcrust pastry topped with filling, strawberry, kiwi, and blueberry. Order of 6.','Pastries','12.00');");
        bakeryappdb.execSQL("INSERT INTO Goods VALUES('Mango Mousse','Creamy mousse portions made with mango. Serves 1 person.','Pastries','6.00');");
        bakeryappdb.execSQL("INSERT INTO Goods VALUES('Pumpkin Pie','Sweet pie with a pumpkin-based custard filling. Serves 8 people.','Pastries','6.00');");
        bakeryappdb.execSQL("INSERT INTO Goods VALUES('Raspberry Mousse','Creamy mousse portions made with fresh raspberries. Serves 1 person.','Pastries','5.00');");
        bakeryappdb.execSQL("INSERT INTO Goods VALUES('Strawberry Flan','Sponge strawberry-flavoured cake covered and filled with syrup. Serves 8 people.','Pastries','4.00');");

        //bread values
        bakeryappdb.execSQL("INSERT INTO Goods VALUES('Almond Croissants','Loaf of artisan bread. Order of 1.','Breads','2.00');");
        bakeryappdb.execSQL("INSERT INTO Goods VALUES('Apple Turnover','Flaky pastry topped with almonds. Order of 6.','Breads','8.00');");
        bakeryappdb.execSQL("INSERT INTO Goods VALUES('Artisan Bread','Puff pastry filled with strawberry jam. Order of 4.','Breads','5.00');");
        bakeryappdb.execSQL("INSERT INTO Goods VALUES('Banana Loaf','Flaky pastry butter-flavoured. Order of 6.','Breads','6.00');");
        bakeryappdb.execSQL("INSERT INTO Goods VALUES('Blueberry Loaf','Loaf of bread made out of fresh blueberries','Breads','6.00');");
        bakeryappdb.execSQL("INSERT INTO Goods VALUES('Butter Croissants','Sweet roll filled with cinnamon and topped with cream cheese frosting. Order of 6.','Breads','5.00');");
        bakeryappdb.execSQL("INSERT INTO Goods VALUES('Cheese Croissants','Flaky pastry topped with cheese. Order of 6.','Breads','7.00');");
        bakeryappdb.execSQL("INSERT INTO Goods VALUES('Cheese Sticks','Bread sticks topped with cheese. Order of 7. Includes marinara sauce.','Breads','10.00');");
        bakeryappdb.execSQL("INSERT INTO Goods VALUES('Cinnamon Buns','Puff Pastry coated with cinnamon and sugar. Order of 6.','Breads','8.00');");

        //muffin values
        bakeryappdb.execSQL("INSERT INTO Goods VALUES('Blueberry Muffins','Order of 6.','Muffins','5.00');");
        bakeryappdb.execSQL("INSERT INTO Goods VALUES('Banana Muffins','Order of 6.','Muffins','4.00');");
        bakeryappdb.execSQL("INSERT INTO Goods VALUES('Chocolate Muffins','Order of 6.','Muffins','5.00');");
        bakeryappdb.execSQL("INSERT INTO Goods VALUES('Chocolate Chip Muffins','Order of 6.','Muffins','4.00');");
        bakeryappdb.execSQL("INSERT INTO Goods VALUES('Lemon Poppy seed muffins','Order of 6.','Muffins','4.00');");

        //cookie values
        bakeryappdb.execSQL("INSERT INTO Goods VALUES('Assorted Sugar Cookies','Order of 12.','Cookies','5.00');");
        bakeryappdb.execSQL("INSERT INTO Goods VALUES('Biscotti','Order of 7.','Cookies','$5.00');");
        bakeryappdb.execSQL("INSERT INTO Goods VALUES('Chocolate Chip Cookies','Order of 12.','Cookies','5.00');");
        bakeryappdb.execSQL("INSERT INTO Goods VALUES('Double Chocolate Chip Cookies','Order of 12.','Cookies','6.00');");
        bakeryappdb.execSQL("INSERT INTO Goods VALUES('Gingerbread Cookies','Order of 12.','Cookies','6.00');");
        bakeryappdb.execSQL("INSERT INTO Goods VALUES('Meringue Cookies','Order of 4.','Cookies','4.00');");




        Button buttonLogin = (Button)findViewById(R.id.btnLogin);
        Button buttonSignup = (Button)findViewById(R.id.btnJoin);

        final TextView txtError = (TextView)findViewById(R.id.txtError);
        final EditText txtUsername = (EditText)findViewById(R.id.txtUsername);
        final EditText txtPassword = (EditText)findViewById(R.id.txtPassword);
        final SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);


        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = txtUsername.getText().toString();
                password = txtPassword.getText().toString();

                resultSet = bakeryappdb.rawQuery("SELECT Username, Password FROM Accounts WHERE Username = '" + username + "' AND Password = '" + password + "'" , null);
                resultSet.moveToFirst();
                if(resultSet.getCount() <= 0){
                    txtError.setText("Incorrect username and or password");
                }else {

                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putString("Username", username);
                    editor.putString("Password", password);
                    editor.commit();

                    txtError.setText("");
                    startActivity(new Intent(SalesRepActivity.this, ProfileActivity.class));
                }
            }
        });

        buttonSignup.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
              //  startActivity(new Intent(SalesRepActivity.this, CreateAccount.class));
            }
        });
    }

}