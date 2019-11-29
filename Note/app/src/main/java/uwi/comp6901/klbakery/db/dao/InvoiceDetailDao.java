package uwi.comp6901.klbakery.db.dao;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import uwi.comp6901.klbakery.db.entity.InvoiceDetail;

@Dao
public interface InvoiceDetailDao {

    @Insert
    void insert(InvoiceDetail invoiceDetail);

    @Update
    void update(InvoiceDetail invoiceDetail);

    @Delete
    void delete(InvoiceDetail invoiceDetail);

    @Query("Select * from invoice_detail where invoice_id = :invoiceId")
    LiveData<List<InvoiceDetail>> allInvoiceDetails(int invoiceId);

    @Query("Select * from invoice_detail where id = :invoiceDetailId")
    LiveData<InvoiceDetail> loadInvoiceDetail(int invoiceDetailId);

}
