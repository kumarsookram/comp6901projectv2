package uwi.comp6901.klbakery.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

import uwi.comp6901.klbakery.db.BakeryDatabase;
import uwi.comp6901.klbakery.db.dao.DeliveryRunInvoiceDao;
import uwi.comp6901.klbakery.db.entity.DeliveryRunInvoice;

public class DeliveryRunInvoiceRepository {
    private DeliveryRunInvoiceDao deliveryRunInvoiceDao;

    public DeliveryRunInvoiceRepository(Application application){
        BakeryDatabase db = BakeryDatabase.getInstance(application);
        this.deliveryRunInvoiceDao = db.deliveryRunInvoiceDao();
    }

    public void insert(DeliveryRunInvoice deliveryRunInvoice){
        new InsertDeliveryRunInvoiceAsyncTask(deliveryRunInvoiceDao).execute(deliveryRunInvoice);
    }

    public void update(DeliveryRunInvoice deliveryRunInvoice){
        new UpdateDeliveryRunInvoiceAsyncTask(deliveryRunInvoiceDao).execute(deliveryRunInvoice);

    }

    public void delete(DeliveryRunInvoice deliveryRunInvoice){
        new DeleteDeliveryRunInvoiceAsyncTask(deliveryRunInvoiceDao).execute(deliveryRunInvoice);
    }

    public LiveData<List<DeliveryRunInvoice>> allDeliveryRunInvoice(int deliveryRunId){
        return deliveryRunInvoiceDao.allDeliveryRunInvoice(deliveryRunId);
    }

    private static class InsertDeliveryRunInvoiceAsyncTask extends AsyncTask<DeliveryRunInvoice, Void, Void>{
        private DeliveryRunInvoiceDao deliveryRunInvoiceDao;

        private InsertDeliveryRunInvoiceAsyncTask(DeliveryRunInvoiceDao deliveryRunInvoiceDao){
            this.deliveryRunInvoiceDao = deliveryRunInvoiceDao;
        }

        @Override
        protected Void doInBackground(DeliveryRunInvoice... deliveryRunInvoices){
            deliveryRunInvoiceDao.insert(deliveryRunInvoices[0]);
            return null;
        }
    }

    private static class UpdateDeliveryRunInvoiceAsyncTask extends AsyncTask<DeliveryRunInvoice, Void, Void>{
        private DeliveryRunInvoiceDao deliveryRunInvoiceDao;

        private UpdateDeliveryRunInvoiceAsyncTask(DeliveryRunInvoiceDao deliveryRunInvoiceDao){
            this.deliveryRunInvoiceDao = deliveryRunInvoiceDao;
        }

        @Override
        protected Void doInBackground(DeliveryRunInvoice... deliveryRunInvoices){
            deliveryRunInvoiceDao.update(deliveryRunInvoices[0]);
            return null;
        }
    }

    private static class DeleteDeliveryRunInvoiceAsyncTask extends AsyncTask<DeliveryRunInvoice, Void, Void>{
        private DeliveryRunInvoiceDao deliveryRunInvoiceDao;

        private DeleteDeliveryRunInvoiceAsyncTask(DeliveryRunInvoiceDao deliveryRunInvoiceDao){
            this.deliveryRunInvoiceDao = deliveryRunInvoiceDao;
        }

        @Override
        protected Void doInBackground(DeliveryRunInvoice... deliveryRunInvoices){
            deliveryRunInvoiceDao.delete(deliveryRunInvoices[0]);
            return null;
        }
    }
}
