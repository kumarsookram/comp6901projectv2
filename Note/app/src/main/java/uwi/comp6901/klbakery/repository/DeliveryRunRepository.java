package uwi.comp6901.klbakery.repository;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;

import java.util.Date;
import java.util.List;

import uwi.comp6901.klbakery.db.BakeryDatabase;
import uwi.comp6901.klbakery.db.dao.DeliveryRunDao;
import uwi.comp6901.klbakery.db.dao.JoinDriverDeliveryRouteDao;
import uwi.comp6901.klbakery.db.dao.JoinInvoiceOrderDao;
import uwi.comp6901.klbakery.db.entity.DeliveryRun;
import uwi.comp6901.klbakery.db.entity.JoinDriverDeliveryRoute;
import uwi.comp6901.klbakery.db.entity.JoinInvoiceOrder;

import static android.content.ContentValues.TAG;

public class DeliveryRunRepository {
    private DeliveryRunDao deliveryRunDao;
    private JoinDriverDeliveryRouteDao joinDriverDeliveryRouteDao;
    private JoinInvoiceOrderDao joinInvoiceOrderDao;

    public DeliveryRunRepository(Application application){
        BakeryDatabase db = BakeryDatabase.getInstance(application);
        this.deliveryRunDao = db.deliveryRunDao();
        this.joinDriverDeliveryRouteDao = db.joinDriverDeliveryRouteDao();
        this.joinInvoiceOrderDao = db.joinInvoiceOrderDao();
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


    public LiveData<List<JoinInvoiceOrder>> allDeliveryRunInvoices(int deliveryRunId){
        return joinInvoiceOrderDao.allDeliveryRunInvoices(deliveryRunId);
    }

    //used get Driver detail for delivery run

    public LiveData<List<JoinDriverDeliveryRoute>> allStatusDeliveryRun( int driverId, String status){
        Log.d(TAG, "allStatusDeliveryRun: Here");
        return joinDriverDeliveryRouteDao.allStatusDeliveryRun(driverId, status);
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
