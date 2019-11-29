package uwi.comp6901.klbakery.db.entity;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;

import java.util.Date;

@Entity(tableName = "user_route",
        primaryKeys = {"user_id","route_id"},
        foreignKeys = {
        @ForeignKey(entity = User.class,
            parentColumns = "id",
            childColumns = "user_id"),
        @ForeignKey(entity = Route.class,
            parentColumns = "id",
            childColumns = "route_id")
        },
        indices = {@Index(value = "route_id")})
public class UserRoute {
    private int user_id;
    private int route_id;
    private Date date_assigned;

    public UserRoute(int user_id, int route_id, Date date_assigned) {
        this.user_id = user_id;
        this.route_id = route_id;
        this.date_assigned = date_assigned;
    }

    public int getUser_id() {
        return user_id;
    }

    public int getRoute_id() {
        return route_id;
    }

    public Date getDate_assigned() {
        return date_assigned;
    }

}
