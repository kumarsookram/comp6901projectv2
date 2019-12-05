package uwi.comp6901.klbakery.db.entity;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "delivery_run",
        foreignKeys = {@ForeignKey(entity = User.class,
                                    parentColumns = "id",
                                    childColumns = "user_id"),
                @ForeignKey(entity = Route.class,
                            parentColumns = "id",
                            childColumns = "route_id")},
            indices = {@Index(value = "user_id"), @Index(value = "route_id")})
public class DeliveryRun {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private int user_id;
    private int route_id;
    private Date delivery_date;
    private String status;

    public DeliveryRun(int user_id, int route_id, Date delivery_date, String status) {
        this.user_id = user_id;
        this.route_id = route_id;
        this.delivery_date = delivery_date;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public int getUser_id() {
        return user_id;
    }

    public int getRoute_id() {
        return route_id;
    }

    public Date getDelivery_date() {
        return delivery_date;
    }

    public String getStatus() { return status;}

    public void setId(int id) {
        this.id = id;
    }
}
