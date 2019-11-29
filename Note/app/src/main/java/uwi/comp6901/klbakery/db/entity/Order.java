package uwi.comp6901.klbakery.db.entity;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "order",
        foreignKeys = {@ForeignKey(entity = User.class,
                        parentColumns = "id",
                        childColumns = "user_id")},
        indices = {@Index(value = "user_id")})
public class Order {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private int user_id;
    private Date order_date;
    private Date delivery_date;
    private String order_status;

    public Order(int user_id, Date order_date, Date delivery_date, String order_status) {
        this.user_id = user_id;
        this.order_date = order_date;
        this.delivery_date = delivery_date;
        this.order_status = order_status;
    }

    public int getId() {
        return id;
    }

    public int getUser_id() {
        return user_id;
    }

    public Date getOrder_date() {
        return order_date;
    }

    public Date getDelivery_date() {
        return delivery_date;
    }

    public String getOrder_status(){
        return order_status;
    }

    public void setId(int id) {
        this.id = id;
    }
}
