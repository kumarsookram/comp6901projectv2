package uwi.comp6901.klbakery.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.Date;
import java.util.List;

import uwi.comp6901.klbakery.db.BakeryDatabase;
import uwi.comp6901.klbakery.db.dao.DeliveryRunDao;
import uwi.comp6901.klbakery.db.entity.DeliveryRun;

public class DeliveryRunRepository {
    private DeliveryRunDao deliveryRunDao;

    public DeliveryRunRepository(Application application){
        BakeryDatabase db = BakeryDatabase.getInstance(application);
        this.deliveryRunDao = db.deliveryRunDao();
    }

    public void insert(DeliveryRun deliveryRun){
        new InsertDeliveryRunAsyncTask(deliveryRunDao).execute(deliveryRun);
    }

    public void update(DeliveryRun deliveryRun){
        new UpdateDeliveryRunAsyncTask(deliveryRunDao).execute(deliveryRun);
    }

    public void delete(DeliveryRun deliveryRun){
        new DeleteDeliveryRunAsyncTask(deliveryRunDao).execute(deliveryRun);
    }

    public LiveData<DeliveryRun> loadDeliveryRun(int deliveryRunId){
        return deliveryRunDao.loadDeliveryRun(deliveryRunId);
    }

    public LiveData<List<DeliveryRun>> dailyDeliveryRuns(Date date){
        return deliveryRunDao.dailyDeliveryRun(date);
    }

    public LiveData<List<DeliveryRun>> driverDailyDeliveryRuns(int driverId, Date date){
        return deliveryRunDao.driverDailyRun(driverId, date);
    }

    private static class InsertDeliveryRunAsyncTask extends AsyncTask<DeliveryRun, Void, Void> {
        private DeliveryRunDao deliveryRunDao;

        private InsertDeliveryRunAsyncTask(DeliveryRunDao deliveryRunDao){
            this.deliveryRunDao = deliveryRunDao;
        }

        @Override
        protected Void doInBackground(DeliveryRun... deliveryRuns){
            deliveryRunDao.insert(deliveryRuns[0]);
            return null;
        }
    }

    private static class UpdateDeliveryRunAsyncTask extends AsyncTask<DeliveryRun, Void, Void> {
        private DeliveryRunDao deliveryRunDao;

        private UpdateDeliveryRunAsyncTask(DeliveryRunDao deliveryRunDao){
            this.deliveryRunDao = deliveryRunDao;
        }

        @Override
        protected Void doInBackground(DeliveryRun... deliveryRuns){
            deliveryRunDao.update(deliveryRuns[0]);
            return null;
        }
    }

    private static class DeleteDeliveryRunAsyncTask extends AsyncTask<DeliveryRun, Void, Void> {
        private DeliveryRunDao deliveryRunDao;

        private DeleteDeliveryRunAsyncTask(DeliveryRunDao deliveryRunDao){
            this.deliveryRunDao = deliveryRunDao;
        }

        @Override
        protected Void doInBackground(DeliveryRun... deliveryRuns){
            deliveryRunDao.delete(deliveryRuns[0]);
            return null;
        }
    }
}
