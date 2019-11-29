package uwi.comp6901.klbakery.db.dao;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import uwi.comp6901.klbakery.db.entity.Product;

@Dao
public interface ProductDao {

    @Insert
    void insert(Product product);

    @Update
    void update(Product product);

    @Delete
    void delete(Product product);

    @Query("Select * from product where product_name like :query")
    LiveData<List<Product>> searchAllProducts(String query);

    @Query("Select * from product where id = :product_id")
    LiveData<Product> loadProduct(int product_id);

}
