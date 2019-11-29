package uwi.comp6901.klbakery.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import uwi.comp6901.klbakery.db.entity.Route;

@Dao
public interface RouteDao {
    @Insert
    void insert(Route route);

    @Update
    void update(Route route);

    @Query("Select * from route where id = :routeId")
    LiveData<Route> loadRoute(int routeId);
}
