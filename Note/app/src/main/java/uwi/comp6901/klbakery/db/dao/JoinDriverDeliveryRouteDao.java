package uwi.comp6901.klbakery.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import java.util.Date;
import java.util.List;

import uwi.comp6901.klbakery.db.entity.JoinDriverDeliveryRoute;


@Dao
public interface JoinDriverDeliveryRouteDao {

    @Query("select  dr.id as id" +
            ", dr.user_id as user_id" +
            ", u.user_name as user_name" +
            ", r.route_name as route_name" +
            ", dr.delivery_date as delivery_date" +
            " from delivery_run dr"+
            " join user u on u.id = dr.user_id"
            +" join route r on dr.route_id = r.id "
            +" where dr.user_id = :driver_id "
            +" and status = :status "
            +" order by dr.delivery_date ")
    LiveData<List<JoinDriverDeliveryRoute>> allStatusDeliveryRun(int driver_id, String status);



}

