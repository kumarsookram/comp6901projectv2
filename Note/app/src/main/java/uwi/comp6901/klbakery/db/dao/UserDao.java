package uwi.comp6901.klbakery.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import uwi.comp6901.klbakery.db.entity.User;

@Dao
public interface UserDao {

    @Insert
    void insert(User user);

    @Update
    void update(User user);

    @Delete
    void delete(User user);

    @Query("Select * from user where id = :userId")
    LiveData<User> loadUser(int userId);

    @Query("Select * from user")
    LiveData<List<User>> allUser();

    @Query("Select * from user where user_email like :email")
    LiveData<User> loadUser(String email);
}
