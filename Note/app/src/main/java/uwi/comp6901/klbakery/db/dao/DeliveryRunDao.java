package uwi.comp6901.klbakery.db.dao;



import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.Date;
import java.util.List;

import uwi.comp6901.klbakery.db.entity.DeliveryRun;

@Dao
public interface DeliveryRunDao {

    @Insert
    void insert(DeliveryRun deliveryRun);

    @Update
    void update(DeliveryRun deliveryRun);

    @Delete
    void delete(DeliveryRun deliveryRun);

    @Query("Select * from delivery_run where id = :deliveryRunId")
    LiveData<DeliveryRun> loadDeliveryRun(int deliveryRunId);

    @Query("Select * from delivery_run where delivery_date = :date")
    LiveData<List<DeliveryRun>> dailyDeliveryRun(Date date);

    @Query("Select * from delivery_run where user_id = :driverId and delivery_date = :date")
    LiveData<List<DeliveryRun>> driverDailyRun(int driverId, Date date);




}
