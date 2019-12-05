package uwi.comp6901.klbakery.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

import uwi.comp6901.klbakery.db.entity.JoinInvoiceOrder;

@Dao
public interface JoinInvoiceOrderDao {
    @Query("select i.id as invoice_id,o.id as order_id ,u.user_name as customer,o.delivery_date as delivery_date,i.comment as invoice_comment from delivery_run_invoice dri " +
            "join invoice i on dri.invoice_id = i.id " +
            "join `order` o on i.order_id = o.id " +
            "join user u on u.id = o.user_id " +
            "where dri.delivery_run_id = :deliveryRunId " +
            "and i.invoice_status = 'Pending'" +
            "order by customer, invoice_id")
    LiveData<List<JoinInvoiceOrder>> allDeliveryRunInvoices(int deliveryRunId);
}
