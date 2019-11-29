package uwi.comp6901.klbakery.db.dao;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import uwi.comp6901.klbakery.db.entity.UserRoute;

@Dao
public interface UserRouteDao {

    @Insert
    void insert(UserRoute userRoute);

    @Update
    void update(UserRoute userRoute);

    @Query("Select * from user_route where user_id = :user_id and route_id = :route_id")
    LiveData<UserRoute> loadUserRoute(int user_id, int route_id);

}
