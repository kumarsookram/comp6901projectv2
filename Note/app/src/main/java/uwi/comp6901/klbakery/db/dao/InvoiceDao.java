package uwi.comp6901.klbakery.db.dao;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.Date;
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


    /*
    @Query("insert into invoice (order_id, invoice_date, delivery_date, invoice_status, comment ) " +
            "select o.id, date('now'), date('now'), 'Pending', ''  from `order` o where o.order_status = 'Pending' ")
    void createInvoices(Date day);
*/

    //To implement update of orders to invoices
    /*
    @Query("insert into invoice_detail (invoice_id, product_name, quantity, price) " +
            " select i.id, p.product_name, od.quantity, p.price from invoice i join `order` o on i.order_id = o.id " +
            "join order_detail od on o.id = od.order_id " +
            "join product p on od.product_id = p.id " +
            "where o.order_status = 'Pending'")
    void createInvoiceDetails();

    @Query("update `order` set order_status = 'Invoiced' where order_status = 'Pending'")
    void updateOrdersToInvoiced();*/
}
