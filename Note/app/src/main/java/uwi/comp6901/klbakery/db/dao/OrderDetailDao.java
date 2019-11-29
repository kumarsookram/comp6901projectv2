package uwi.comp6901.klbakery.db.dao;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import uwi.comp6901.klbakery.db.entity.OrderDetail;

@Dao
public interface OrderDetailDao {

    @Insert
    void insert(OrderDetail orderDetail);

    @Update
    void update(OrderDetail orderDetail);

    @Delete
    void delete(OrderDetail orderDetail);

    @Query("Select * from order_detail where id = :orderDetailId")
    LiveData<OrderDetail> loadOrderDetail(int orderDetailId);

    @Query("Select * from order_detail where order_id = :orderId")
    LiveData<List<OrderDetail>> allOrderDetails(int orderId);
}
