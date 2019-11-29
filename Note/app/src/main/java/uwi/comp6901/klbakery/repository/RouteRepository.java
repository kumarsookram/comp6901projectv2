package uwi.comp6901.klbakery.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import uwi.comp6901.klbakery.db.BakeryDatabase;
import uwi.comp6901.klbakery.db.dao.RouteDao;
import uwi.comp6901.klbakery.db.entity.Route;

public class RouteRepository {
    private RouteDao routeDao;

    public RouteRepository(Application application) {
        BakeryDatabase db = BakeryDatabase.getInstance(application);
        this.routeDao = db.routeDao();
    }

    public void insert(Route route){
        new InsertRouteAsyncTask(routeDao).execute(route);
    }

    public void update(Route route){
        new UpdateRouteAsyncTask(routeDao).execute(route);
    }

    public LiveData<Route> loadRoute(int id){
        return routeDao.loadRoute(id);
    }

    private static class InsertRouteAsyncTask extends AsyncTask<Route,Void,Void>{
        private RouteDao routeDao;

        private InsertRouteAsyncTask(RouteDao routeDao){
            this.routeDao = routeDao;
        }

        @Override
        protected Void doInBackground(Route... routes){
            routeDao.insert(routes[0]);
            return null;
        }

    }

    private static class UpdateRouteAsyncTask extends AsyncTask<Route,Void,Void>{
        private RouteDao routeDao;

        private UpdateRouteAsyncTask(RouteDao routeDao){
            this.routeDao = routeDao;
        }

        @Override
        protected Void doInBackground(Route... routes){
            routeDao.update(routes[0]);
            return null;
        }

    }
}
