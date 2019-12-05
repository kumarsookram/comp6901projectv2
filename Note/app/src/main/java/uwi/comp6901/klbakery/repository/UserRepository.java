package uwi.comp6901.klbakery.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.core.widget.ListViewAutoScrollHelper;
import androidx.lifecycle.LiveData;

import java.util.List;

import uwi.comp6901.klbakery.db.BakeryDatabase;
import uwi.comp6901.klbakery.db.dao.UserDao;
import uwi.comp6901.klbakery.db.entity.User;

public class UserRepository {
    private UserDao userDao;
    private LiveData<List<User>> allUser;

    public UserRepository(Application application){
        BakeryDatabase database = BakeryDatabase.getInstance(application);
        userDao = database.userDao();
        allUser = userDao.allUser();
    }


    public void insert(User user){
        new InsertUserAsyncTask(userDao).execute(user);
    }

    public void delete(User user){
        new DeleteUserAsyncTask(userDao).execute(user);
    }

    public void update(User user){
        new UpdateUserAsyncTask(userDao).execute(user);

    }

    public LiveData<List<User>> getAllUser() {
        return allUser;
    }

    public LiveData<User> getUser(int Id){
        return userDao.loadUser(Id);
    }

    public LiveData<User> getUser(String email){
        return userDao.loadUser(email);
    }

    public LiveData<User> validate(String email, String password){ return userDao.validate(email, password);}


    private static class InsertUserAsyncTask extends AsyncTask<User, Void, Void> {
        private UserDao userDao;

        private InsertUserAsyncTask(UserDao userDao){
            this.userDao = userDao;
        }
        @Override
        protected Void doInBackground(User... users){
            userDao.insert(users[0]);
            return null;
        }
    }

    private static class UpdateUserAsyncTask extends AsyncTask<User, Void, Void> {
        private UserDao userDao;

        private UpdateUserAsyncTask(UserDao userDao){
            this.userDao = userDao;
        }
        @Override
        protected Void doInBackground(User... users){
            userDao.update(users[0]);
            return null;
        }
    }

    private static class DeleteUserAsyncTask extends AsyncTask<User, Void, Void> {
        private UserDao userDao;

        private DeleteUserAsyncTask(UserDao userDao){
            this.userDao = userDao;
        }
        @Override
        protected Void doInBackground(User... users){
            userDao.delete(users[0]);
            return null;
        }
    }
}
