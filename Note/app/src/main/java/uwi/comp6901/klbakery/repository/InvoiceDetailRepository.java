package uwi.comp6901.klbakery.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

import uwi.comp6901.klbakery.db.BakeryDatabase;
import uwi.comp6901.klbakery.db.dao.InvoiceDetailDao;
import uwi.comp6901.klbakery.db.entity.InvoiceDetail;

public class InvoiceDetailRepository {
    private InvoiceDetailDao invoiceDetailDao;

    public InvoiceDetailRepository(Application application){
        BakeryDatabase db = BakeryDatabase.getInstance(application);
        this.invoiceDetailDao =db.invoiceDetailDao();
    }

    public LiveData<InvoiceDetail> loadInvoiceDetail(int invoiceDetailId){
        return invoiceDetailDao.loadInvoiceDetail(invoiceDetailId);
    }

    public LiveData<List<InvoiceDetail>> allInvoiceDetails(int invoiceId){
        return invoiceDetailDao.allInvoiceDetails(invoiceId);
    }

    public void insert(InvoiceDetail invoiceDetail){
        new InsertInvoiceDetailAsyncTask(invoiceDetailDao).execute(invoiceDetail);
    }

    public void update(InvoiceDetail invoiceDetail){
        new UpdateInvoiceDetailAsyncTask(invoiceDetailDao).execute(invoiceDetail);
    }

    public void delete(InvoiceDetail invoiceDetail){
        new DeleteInvoiceDetailAsyncTask(invoiceDetailDao).execute(invoiceDetail);
    }

    private static class InsertInvoiceDetailAsyncTask  extends AsyncTask<InvoiceDetail, Void, Void>{
        private InvoiceDetailDao invoiceDetailDao;

        private InsertInvoiceDetailAsyncTask(InvoiceDetailDao invoiceDetailDao){
            this.invoiceDetailDao = invoiceDetailDao;
        }

        @Override
        protected Void doInBackground(InvoiceDetail... invoiceDetails){
            invoiceDetailDao.insert(invoiceDetails[0]);
            return null;
        }
    }

    private static class UpdateInvoiceDetailAsyncTask  extends AsyncTask<InvoiceDetail, Void, Void>{
        private InvoiceDetailDao invoiceDetailDao;

        private UpdateInvoiceDetailAsyncTask(InvoiceDetailDao invoiceDetailDao){
            this.invoiceDetailDao = invoiceDetailDao;
        }

        @Override
        protected Void doInBackground(InvoiceDetail... invoiceDetails){
            invoiceDetailDao.update(invoiceDetails[0]);
            return null;
        }
    }

    private static class DeleteInvoiceDetailAsyncTask  extends AsyncTask<InvoiceDetail, Void, Void>{
        private InvoiceDetailDao invoiceDetailDao;

        private DeleteInvoiceDetailAsyncTask(InvoiceDetailDao invoiceDetailDao){
            this.invoiceDetailDao = invoiceDetailDao;
        }

        @Override
        protected Void doInBackground(InvoiceDetail... invoiceDetails){
            invoiceDetailDao.delete(invoiceDetails[0]);
            return null;
        }
    }

}
