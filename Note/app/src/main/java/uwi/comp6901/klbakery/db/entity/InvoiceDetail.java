package uwi.comp6901.klbakery.db.entity;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "invoice_detail",
        foreignKeys = {@ForeignKey(entity = Invoice.class,
                parentColumns = "id",
                childColumns = "invoice_id")},
        indices = {@Index(value = "invoice_id")})
public class InvoiceDetail {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private int invoice_id;
    private String product_name;
    private int quantity;
    private double price;

    public InvoiceDetail(int invoice_id, String product_name, int quantity, double price) {
        this.invoice_id = invoice_id;
        this.product_name = product_name;
        this.quantity = quantity;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public int getInvoice_id() {
        return invoice_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setId(int id) {
        this.id = id;
    }
}
