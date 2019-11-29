package uwi.comp6901.klbakery.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import uwi.comp6901.klbakery.db.entity.DeliveryRunInvoice;
import uwi.comp6901.klbakery.db.entity.Invoice;
import uwi.comp6901.klbakery.db.entity.InvoiceDetail;
import uwi.comp6901.klbakery.repository.DeliveryRunInvoiceRepository;
import uwi.comp6901.klbakery.repository.DeliveryRunRepository;
import uwi.comp6901.klbakery.repository.InvoiceDetailRepository;
import uwi.comp6901.klbakery.repository.InvoiceRepository;

public class DriverViewModel extends AndroidViewModel {

    private DeliveryRunRepository deliveryRunRepository;
    private DeliveryRunInvoiceRepository deliveryRunInvoiceRepository;
    private InvoiceRepository invoiceRepository;
    private InvoiceDetailRepository invoiceDetailRepository;


    public DriverViewModel(@NonNull Application application) {
        super(application);
        this.deliveryRunRepository = new DeliveryRunRepository(application);
        this.deliveryRunInvoiceRepository = new DeliveryRunInvoiceRepository(application);
        this.invoiceRepository = new InvoiceRepository(application);
        this.invoiceDetailRepository = new InvoiceDetailRepository(application);
    }

    public void update(Invoice invoice){
        invoiceRepository.update(invoice);
    }

    public LiveData<List<DeliveryRunInvoice>> allDeliveryRunInvoices(int deliveryRunId){
        return deliveryRunInvoiceRepository.allDeliveryRunInvoice(deliveryRunId);
    }

    public LiveData<Invoice> getInvoice(int invoice_id){
        return invoiceRepository.loadInvoice(invoice_id);
    }

    public LiveData<List<InvoiceDetail>> getAllInvoiceDetails(int invoice_id){
        return invoiceDetailRepository.allInvoiceDetails(invoice_id);
    }
}
