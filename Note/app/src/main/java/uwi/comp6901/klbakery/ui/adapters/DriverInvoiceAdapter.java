package uwi.comp6901.klbakery.ui.adapters;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import uwi.comp6901.klbakery.R;
import uwi.comp6901.klbakery.db.entity.JoinInvoiceOrder;

public class DriverInvoiceAdapter extends RecyclerView.Adapter<DriverInvoiceAdapter.DriverInvoiceHolder> {
    private List<JoinInvoiceOrder> driversInvoiceOrders = new ArrayList<>();
    private OnItemClickListener listener;

    @NonNull
    @Override
    public DriverInvoiceHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.invoice_item, parent, false);
        return new DriverInvoiceHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull DriverInvoiceHolder holder, int position) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yy");
        JoinInvoiceOrder currentitem = driversInvoiceOrders.get(position);


        holder.tvInvoiceId.setText(String.valueOf(currentitem.invoice_id));
        holder.tvOrderId.setText(String.valueOf(currentitem.order_id));
        holder.tvCustomer.setText(currentitem.customer);
        holder.tvDeliveryDate.setText(formatter.format(currentitem.delivery_date));
        holder.tvComment.setText(currentitem.invoice_comment);




    }

    @Override
    public int getItemCount() {
        return driversInvoiceOrders.size();
    }

    public void setDrivers(List<JoinInvoiceOrder> invoiceOrders) {
        this.driversInvoiceOrders = invoiceOrders;
        notifyDataSetChanged();
    }

    public JoinInvoiceOrder getUserAt(int position){
        return driversInvoiceOrders.get(position);
    }

    class DriverInvoiceHolder extends RecyclerView.ViewHolder {
        private TextView tvInvoiceId;
        private TextView tvOrderId;
        private TextView tvCustomer;
        private TextView tvDeliveryDate;
        private TextView tvComment;

        public DriverInvoiceHolder(View itemView) {
            super(itemView);
            tvInvoiceId = itemView.findViewById(R.id.tv_invoice_id_driver_view);
            tvOrderId = itemView.findViewById(R.id.tv_order_id_driver_view);
            tvCustomer = itemView.findViewById(R.id.tv_customer_driver_view);
            tvDeliveryDate = itemView.findViewById(R.id.tv_delivery_date_driver_view);
            tvComment = itemView.findViewById(R.id.tv_invoice_comment_driver_view);



            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();


                    if(listener != null && position != RecyclerView.NO_POSITION){
                        listener.onItemClick(driversInvoiceOrders.get(position));
                    }

                }
            });


        }
    }

    public interface OnItemClickListener{
        void onItemClick(JoinInvoiceOrder driverInvoice);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }
}

