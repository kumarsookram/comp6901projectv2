package uwi.comp6901.klbakery.db.entity;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "invoice",
        foreignKeys = { @ForeignKey(entity = Order.class,
                                    parentColumns = "id",
                                    childColumns = "order_id")},
                indices = {@Index(value = "order_id")})
public class Invoice {

    @PrimaryKey(autoGenerate =  true)
    private int id;
    private int order_id;
    private Date invoice_date;
    private Date delivery_date;
    private String invoice_status;
    private String comment;

    public Invoice(int order_id, Date invoice_date, Date delivery_date, String invoice_status, String comment) {
        this.order_id = order_id;
        this.invoice_date = invoice_date;
        this.delivery_date = delivery_date;
        this.invoice_status = invoice_status;
        this.comment = comment;
    }

    public int getId() {
        return id;
    }

    public int getOrder_id() {
        return order_id;
    }

    public String getComment() {
        return comment;
    }

    public Date getInvoice_date() {
        return invoice_date;
    }

    public Date getDelivery_date() {
        return delivery_date;
    }

    public String getInvoice_status() {
        return invoice_status;
    }

    public void setId(int id) {
        this.id = id;
    }


}
