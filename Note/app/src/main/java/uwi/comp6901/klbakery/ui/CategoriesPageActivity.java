package uwi.comp6901.klbakery.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


import uwi.comp6901.klbakery.R;
public class CategoriesPageActivity extends AppCompatActivity {

     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories_page);

        final SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        final SharedPreferences.Editor editor = sharedPref.edit();

        String[] categories = new String[] {"Breads", "Pastries", "Cakes", "Cookies", "Muffins"};

        final ListView listView = (ListView)findViewById(R.id.listView);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.single_row, R.id.textView, categories);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int itemPosition = position;
                String itemValue = (String) listView.getItemAtPosition(position);

                switch(itemValue) {
                    case "Breads":
                        editor.putString("Category", "Breads");
                        editor.commit();
                        startActivity(new Intent(CategoriesPageActivity.this, DisplayItemsByCategoryActivity.class));
                        break;

                    case "Pastries":
                        editor.putString("Category", "Pastries");
                        editor.commit();
                        startActivity(new Intent(CategoriesPageActivity.this, DisplayItemsByCategoryActivity.class));
                        break;

                    case "Cakes":
                        editor.putString("Category", "Cakes");
                        editor.commit();
                        startActivity(new Intent(CategoriesPageActivity.this, DisplayItemsByCategoryActivity.class));
                        break;

                    case "Cookies":
                        editor.putString("Category", "Cookies");
                        editor.commit();
                        startActivity(new Intent(CategoriesPageActivity.this, DisplayItemsByCategoryActivity.class));
                        break;

                    case "Muffins":
                        editor.putString("Category", "Muffins");
                        editor.commit();
                        startActivity(new Intent(CategoriesPageActivity.this, DisplayItemsByCategoryActivity.class));
                        break;

                }
            }
        });

    }
}