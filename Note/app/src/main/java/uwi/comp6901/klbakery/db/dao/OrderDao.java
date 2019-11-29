package uwi.comp6901.klbakery.db.dao;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import uwi.comp6901.klbakery.db.entity.Order;

@Dao
public interface OrderDao {

    @Insert
    void insert(Order order);

    @Update
    void update(Order order);

    @Delete
    void delete(Order order);

    @Query("Select * from `order` where user_id = :customerId")
    LiveData<List<Order>> customerOrders(int customerId);

    @Query("Select * from `order` where id = :orderId")
    LiveData<Order> loadOrder(int orderId);
}
