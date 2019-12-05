package uwi.comp6901.klbakery.ui.adapters;

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
import uwi.comp6901.klbakery.db.entity.JoinDriverDeliveryRoute;

public class DriverAdapter extends RecyclerView.Adapter<DriverAdapter.DriverHolder> {
    private List<JoinDriverDeliveryRoute> drivers = new ArrayList<>();
    private OnItemClickListener listener;

    @NonNull
    @Override
    public DriverHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.driver_item, parent, false);
        return new DriverHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull DriverHolder holder, int position) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yy");
        JoinDriverDeliveryRoute currentitem = drivers.get(position);


        holder.tvDeliveryId.setText(String.valueOf(currentitem.id));
        holder.tvDeliveryDate.setText(formatter.format(currentitem.delivery_date));
        holder.tvRoute.setText(currentitem.route_name);
        //holder.tvUserEmail.setText(currentUser.getUser_email());
        //holder.tvUserPassword.setText(currentUser.getUser_password());
    }

    @Override
    public int getItemCount() {
        return drivers.size();
    }

    public void setDrivers(List<JoinDriverDeliveryRoute> drivers) {
        this.drivers = drivers;
        notifyDataSetChanged();
    }

    public JoinDriverDeliveryRoute getUserAt(int position){
        return drivers.get(position);
    }

    class DriverHolder extends RecyclerView.ViewHolder {
        private TextView tvDeliveryId;
        private TextView tvRoute;
        private TextView tvDeliveryDate;

        public DriverHolder(View itemView) {
            super(itemView);
            tvDeliveryId = itemView.findViewById(R.id.tv_di_delivery_run_id);
            tvDeliveryDate = itemView.findViewById(R.id.tv_di_delivery_date);
            tvRoute = itemView.findViewById(R.id.tv_di_route);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();

                    if(listener != null && position != RecyclerView.NO_POSITION){
                        listener.onItemClick(drivers.get(position));
                    }

                }
            });


        }
    }

    public interface OnItemClickListener{
        void onItemClick(JoinDriverDeliveryRoute driver);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }
}

