package uwi.comp6901.klbakery.ui.drivers;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import uwi.comp6901.klbakery.R;
import uwi.comp6901.klbakery.db.entity.DeliveryRun;
import uwi.comp6901.klbakery.db.entity.JoinDriverDeliveryRoute;
import uwi.comp6901.klbakery.ui.HomeActivity;
import uwi.comp6901.klbakery.ui.adapters.DriverAdapter;
import uwi.comp6901.klbakery.viewModel.DriverViewModel;

public class DriverActivity extends AppCompatActivity {

    public static final String EXTRA_DELIVERY_RUN_ID = "uwi.comp6901.klbakery.ui.drivers.EXTRA_DELIVERY_RUN_ID";
    private DriverViewModel driverViewModel;//recyclerlist uses this view
    private DriverViewModel driver;

    private int driverId = 2; //used for test. after testing, values would be supplied from HomeActivity
    private final String statusFilter = "Pending";

    TextView mDriverId;
    EditText mStatus;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver);

        mDriverId = (TextView)findViewById(R.id.tv_da_driver_id);
        mStatus = (EditText) findViewById(R.id.tv_da_status);

        Intent intent = getIntent();
        if (intent.hasExtra(HomeActivity.EXTRA_USER_ID)){
            setTitle("Driver Options");
            //Todo set driverId to EXTRA_USER_ID
            int i = intent.getIntExtra(HomeActivity.EXTRA_USER_ID,-1);
            if (i != -1){
                driverId = i;
                Toast.makeText(this, "Using data from login", Toast.LENGTH_SHORT).show();
            }
            mDriverId.setText(String.valueOf(driverId));
            mStatus.setText(statusFilter);
        }

        //driverViewModel = ViewModelProviders.of(this).get(DriverViewModel.class);


        FloatingActionButton btnDetails = findViewById(R.id.btn_da_view_detail);
        btnDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DriverActivity.this, "Button Functionality not Implemented", Toast.LENGTH_SHORT).show();
                //Intent intent = new Intent(DriverActivity.this, DriverDeliveryRunActivity.class);
                //intent.putExtra(EXTRA_DELIVERY_RUN_ID, 1);
                //startActivityForResult(intent, ADD_NOTE_REQUEST);
            }
        });

        RecyclerView recyclerView = findViewById(R.id.recycler_view_driver);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final DriverAdapter adapter = new DriverAdapter();
        recyclerView.setAdapter(adapter);

        driverViewModel = ViewModelProviders.of(this).get(DriverViewModel.class);
        driver = ViewModelProviders.of(this).get(DriverViewModel.class);
        driverViewModel.allStatusDelivery(driverId, mStatus.getText().toString().trim()).observe(this, new Observer<List<JoinDriverDeliveryRoute>>() {
            @Override
            public void onChanged(List<JoinDriverDeliveryRoute> joinDriverDeliveryRoutes) {
                adapter.setDrivers(joinDriverDeliveryRoutes);
            }
        });


        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
               // get object swiped
                JoinDriverDeliveryRoute i = adapter.getUserAt(viewHolder.getAdapterPosition());

                driver.loadDeliveryRun(i.id).observe(DriverActivity.this, new Observer<DeliveryRun>() {
                    @Override
                    public void onChanged(@Nullable DeliveryRun deliveryRun) {
                        if (deliveryRun == null) {
                            Toast.makeText(DriverActivity.this, "Could not retrieve user to update", Toast.LENGTH_SHORT).show();
                        } else {
                            updateProfile(deliveryRun);

                        }
                    }

                });


                Toast.makeText(DriverActivity.this, "Updated Run to completed", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerView);

        adapter.setOnItemClickListener(new DriverAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(JoinDriverDeliveryRoute driver) {
                Intent intent = new Intent(DriverActivity.this, DriverDeliveryRunActivity.class);
                intent.putExtra(EXTRA_DELIVERY_RUN_ID, driver.id);
                startActivity(intent);
            }
        });




    }

    private void updateProfile(DeliveryRun deliveryRun){
        String statusComplete = "Completed";

        DeliveryRun updatedRun = new DeliveryRun(deliveryRun.getUser_id(), deliveryRun.getRoute_id(), deliveryRun.getDelivery_date(),statusComplete);
        updatedRun.setId(deliveryRun.getId());

        driver.updateDeliveryRun(updatedRun);
    }
}
