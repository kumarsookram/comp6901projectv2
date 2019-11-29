package uwi.comp6901.klbakery.db.entity;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "route")
public class Route {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String route_name;

    private String route_region;

    public Route(String route_name, String route_region) {
        this.route_name = route_name;
        this.route_region = route_region;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getRoute_name() {
        return route_name;
    }

    public String getRoute_region() {
        return route_region;
    }
}
