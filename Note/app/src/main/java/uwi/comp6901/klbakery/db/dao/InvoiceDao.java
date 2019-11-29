package uwi.comp6901.klbakery.db.dao;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import uwi.comp6901.klbakery.db.entity.Invoice;

@Dao
public interface InvoiceDao {

    @Insert
    void insert(Invoice invoice);

    @Update
    void update(Invoice invoice);

    @Delete
    void delete(Invoice invoice);

    @Query("Select * from invoice where id = :invoiceId")
    LiveData<Invoice> loadInvoice(int invoiceId);

    @Query("select invoice.* from invoice join `order` on (invoice.order_id = `order`.id) where `order`.user_id = :customerId")
    LiveData<List<Invoice>> allCustomerInvoices(int customerId);

}
