package uwi.comp6901.klbakery.db.entity;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "order_detail",
        foreignKeys = {
            @ForeignKey(entity = Order.class,
            parentColumns = "id",
            childColumns = "order_id"),
            @ForeignKey(entity = Product.class,
            parentColumns = "id",
            childColumns = "product_id")
        },
        indices = {@Index(value = "order_id"), @Index(value = "product_id")}
        )
public class OrderDetail {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private int order_id;
    private int product_id;
    private int quantity;

    public OrderDetail(int order_id, int product_id, int quantity) {
        this.order_id = order_id;
        this.product_id = product_id;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public int getOrder_id() {
        return order_id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setId(int id) {
        this.id = id;
    }
}
