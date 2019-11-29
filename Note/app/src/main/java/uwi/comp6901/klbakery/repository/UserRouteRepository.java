package uwi.comp6901.klbakery.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import uwi.comp6901.klbakery.db.BakeryDatabase;
import uwi.comp6901.klbakery.db.dao.UserRouteDao;
import uwi.comp6901.klbakery.db.entity.UserRoute;

public class UserRouteRepository {

    private UserRouteDao userRouteDao;

    public UserRouteRepository(Application application){
        BakeryDatabase db = BakeryDatabase.getInstance(application);
        userRouteDao = db.userRouteDao();
    }

    public void insert(UserRoute userRoute){
        new InsertUserRouteAsyncTask(userRouteDao).execute(userRoute);
    }

    public void update(UserRoute userRoute){
        new UpdateUserRouteAsyncTask(userRouteDao).execute(userRoute);
    }

    public LiveData<UserRoute> loadUserRoute(int user_id, int route_id){
        return userRouteDao.loadUserRoute(user_id, route_id);
    }

    private static class InsertUserRouteAsyncTask extends AsyncTask<UserRoute, Void, Void>{
        private UserRouteDao userRouteDao;

        private InsertUserRouteAsyncTask(UserRouteDao userRouteDao){
            this.userRouteDao = userRouteDao;
        }

        @Override
        protected Void doInBackground(UserRoute... userRoutes){
            userRouteDao.insert(userRoutes[0]);
            return null;
        }
    }

    private static class UpdateUserRouteAsyncTask extends AsyncTask<UserRoute, Void, Void>{
        private UserRouteDao userRouteDao;

        private UpdateUserRouteAsyncTask(UserRouteDao userRouteDao){
            this.userRouteDao = userRouteDao;
        }

        @Override
        protected Void doInBackground(UserRoute... userRoutes){
            userRouteDao.update(userRoutes[0]);
            return null;
        }
    }
}
