package uwi.comp6901.klbakery.db.dao;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import uwi.comp6901.klbakery.db.entity.DeliveryRunInvoice;

@Dao
public interface DeliveryRunInvoiceDao {

    @Insert
    void insert(DeliveryRunInvoice deliveryRunInvoice);

    @Update
    void update(DeliveryRunInvoice deliveryRunInvoice);

    @Delete
    void delete(DeliveryRunInvoice deliveryRunInvoice);

    @Query("Select * from delivery_run_invoice where delivery_run_id = :deliverRunId")
    LiveData<List<DeliveryRunInvoice>> allDeliveryRunInvoice(int deliverRunId);

}
