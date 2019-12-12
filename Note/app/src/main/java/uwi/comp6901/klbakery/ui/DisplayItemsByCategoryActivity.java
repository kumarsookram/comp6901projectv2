package uwi.comp6901.klbakery.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.preference.PreferenceManager;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;
import uwi.comp6901.klbakery.R;
import java.util.LinkedList;

public class DisplayItemsByCategoryActivity extends AppCompatActivity {
    SQLiteDatabase bakeryappdb;
    Cursor resultSet;
    SharedPreferences sharedPref;
    String category;
    TextView txtCategoryName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_items_by_category);

    }

    protected void onResume() {
        super.onResume();

        bakeryappdb = openOrCreateDatabase("bakery db", MODE_PRIVATE, null);
        sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        final SharedPreferences.Editor editor = sharedPref.edit();

        txtCategoryName = (TextView) findViewById(R.id.txtCategoryName);
        category = sharedPref.getString("Category", "");
        txtCategoryName.setText(category);

        resultSet = bakeryappdb.rawQuery("SELECT * FROM Goods WHERE Category = '" + category + "'", null);
        resultSet.moveToFirst();

        LinkedList<String> items = new LinkedList<String>();

        while (!resultSet.isAfterLast()) {
            items.add(resultSet.getString(0));
            resultSet.moveToNext();
        }

        final ListView listView = (ListView) findViewById(R.id.listView);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.single_row, R.id.textView, items);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int itemPosition = position;
                String itemValue = (String) listView.getItemAtPosition(position);
                System.out.println(itemValue);

                editor.putString("Item", itemValue);
                editor.commit();
                startActivity(new Intent(DisplayItemsByCategoryActivity.this, ItemSpecificsActivity.class));
            }

        });
    }
}

