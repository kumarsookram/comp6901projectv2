package uwi.comp6901.klbakery.db.entity;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;

import java.util.Date;

@Entity(tableName = "delivery_run_invoice",
        primaryKeys = {"delivery_run_id", "invoice_id"},
        foreignKeys = {
                @ForeignKey(
                        entity = DeliveryRun.class,
                        parentColumns = "id",
                        childColumns = "delivery_run_id"
                ),
                @ForeignKey(
                        entity = Invoice.class,
                        parentColumns = "id",
                        childColumns = "invoice_id"
                )
        },
        indices = {@Index(value = "invoice_id")}
)
public class DeliveryRunInvoice {

    private int delivery_run_id;
    private int invoice_id;
    private Date date_generated;

    public DeliveryRunInvoice(int delivery_run_id, int invoice_id, Date date_generated) {
        this.delivery_run_id = delivery_run_id;
        this.invoice_id = invoice_id;
        this.date_generated = date_generated;
    }

    public int getDelivery_run_id() {
        return delivery_run_id;
    }

    public int getInvoice_id() {
        return invoice_id;
    }

    public Date getDate_generated() {
        return date_generated;
    }
}
