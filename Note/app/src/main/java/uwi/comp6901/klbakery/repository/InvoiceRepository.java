package uwi.comp6901.klbakery.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

import uwi.comp6901.klbakery.db.BakeryDatabase;
import uwi.comp6901.klbakery.db.dao.InvoiceDao;
import uwi.comp6901.klbakery.db.entity.Invoice;

public class InvoiceRepository {
    private InvoiceDao invoiceDao;

    public InvoiceRepository(Application application){
        BakeryDatabase db = BakeryDatabase.getInstance(application);
        this.invoiceDao = db.invoiceDao();
    }

    public LiveData<Invoice> loadInvoice(int invoiceId){
        return invoiceDao.loadInvoice(invoiceId);
    }

    public LiveData<List<Invoice>> allCustomerInvoices(int customerId){
        return invoiceDao.allCustomerInvoices(customerId);
    }

    public void insert(Invoice invoice){
        new InsertInvoiceAsyncTask(invoiceDao).execute(invoice);

    }

    public void update(Invoice invoice){
        new UpdateInvoiceAsyncTask(invoiceDao).execute(invoice);
    }

    public void delete(Invoice inoice){
        new DeleteInvoiceAsyncTask(invoiceDao).execute(inoice);
    }

    public static class InsertInvoiceAsyncTask extends AsyncTask<Invoice, Void, Void>{
        private InvoiceDao invoiceDao;

        private InsertInvoiceAsyncTask(InvoiceDao invoiceDao){
            this.invoiceDao = invoiceDao;
        }

        @Override
        protected Void doInBackground(Invoice... invoices){
            invoiceDao.insert(invoices[0]);
            return null;
        }
    }

    public static class UpdateInvoiceAsyncTask extends AsyncTask<Invoice, Void, Void>{
        private InvoiceDao invoiceDao;

        private UpdateInvoiceAsyncTask(InvoiceDao invoiceDao){
            this.invoiceDao = invoiceDao;
        }

        @Override
        protected Void doInBackground(Invoice... invoices){
            invoiceDao.update(invoices[0]);
            return null;
        }
    }

    public static class DeleteInvoiceAsyncTask extends AsyncTask<Invoice, Void, Void>{
        private InvoiceDao invoiceDao;

        private DeleteInvoiceAsyncTask(InvoiceDao invoiceDao){
            this.invoiceDao = invoiceDao;
        }

        @Override
        protected Void doInBackground(Invoice... invoices){
            invoiceDao.delete(invoices[0]);
            return null;
        }
    }
}
