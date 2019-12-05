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
import android.widget.Toast;

import java.util.Date;
import java.util.List;

import uwi.comp6901.klbakery.R;
import uwi.comp6901.klbakery.db.entity.DeliveryRun;
import uwi.comp6901.klbakery.db.entity.Invoice;
import uwi.comp6901.klbakery.db.entity.JoinInvoiceOrder;
import uwi.comp6901.klbakery.ui.adapters.DriverInvoiceAdapter;
import uwi.comp6901.klbakery.viewModel.DriverViewModel;

public class DriverDeliveryRunActivity extends AppCompatActivity {

    private DriverViewModel driverViewModel;//recyclerlist uses this view
    private DriverViewModel delivery;
    private int deliveryId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_delivery_run);

        Intent intent = getIntent();
        if (intent.hasExtra(DriverActivity.EXTRA_DELIVERY_RUN_ID)){
            deliveryId = intent.getIntExtra(DriverActivity.EXTRA_DELIVERY_RUN_ID, -1);
        }

        //load delivery invoices
        RecyclerView recyclerView = findViewById(R.id.recycler_view_driver_delivery_invoices);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(false);

        final DriverInvoiceAdapter adapter = new DriverInvoiceAdapter();
        recyclerView.setAdapter(adapter);

        driverViewModel = ViewModelProviders.of(this).get(DriverViewModel.class);
        delivery = ViewModelProviders.of(this).get(DriverViewModel.class);


        driverViewModel.allDeliveryRunInvoices(deliveryId).observe(this, new Observer<List<JoinInvoiceOrder>>() {
            @Override
            public void onChanged(List<JoinInvoiceOrder> joinInvoiceOrders) {
                //Toast.makeText(DriverDeliveryRunActivity.this, "Inside on changed", Toast.LENGTH_SHORT).show();
                adapter.setDrivers(joinInvoiceOrders);
            }
        });


        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, final int direction) {
                // get object swiped
                JoinInvoiceOrder i = adapter.getUserAt(viewHolder.getAdapterPosition());

                delivery.getInvoice(i.invoice_id).observe(DriverDeliveryRunActivity.this, new Observer<Invoice>() {
                    @Override
                    public void onChanged(@Nullable Invoice deliveryInvoice) {
                        if (deliveryInvoice == null) {
                            Toast.makeText(DriverDeliveryRunActivity.this, "Could not retrieve user to update", Toast.LENGTH_SHORT).show();
                        } else {
                            if (direction == ItemTouchHelper.LEFT){
                                updateSwipeLeft(deliveryInvoice);
                                Toast.makeText(DriverDeliveryRunActivity.this, "Invoice Updated as delivered", Toast.LENGTH_SHORT).show();
                            }else if(direction == ItemTouchHelper.RIGHT){
                                Toast.makeText(DriverDeliveryRunActivity.this, "Right Action", Toast.LENGTH_SHORT).show();
                                updateSwipeRight();
                            }else{
                                Toast.makeText(DriverDeliveryRunActivity.this, "Invalid Action", Toast.LENGTH_SHORT).show();
                            }


                        }
                    }

                });


               // Toast.makeText(DriverDeliveryRunActivity.this, "Updated Run to completed", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerView);
    }

    private void updateSwipeLeft(Invoice invoice){
        //Left Swipe updates invoice status to Delivered
        String statusComplete = "Delivered";

        Invoice updatedInvoice = new Invoice(invoice.getId(),invoice.getInvoice_date(),new Date(),statusComplete,invoice.getComment());
        updatedInvoice.setId(invoice.getId());

        delivery.update(updatedInvoice);
        refresh();


    }

    private void updateSwipeRight(){
        //Right Swipe open open Activity to view Details
        refresh();
    }

    private void refresh(){
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }
}
