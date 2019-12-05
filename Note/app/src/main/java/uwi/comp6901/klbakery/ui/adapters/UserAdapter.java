package uwi.comp6901.klbakery.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import uwi.comp6901.klbakery.R;
import uwi.comp6901.klbakery.db.entity.User;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserHolder> {
    private List<User> users = new ArrayList<>();
    private OnItemClickListener listener;

    @NonNull
    @Override
    public UserHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_item, parent, false);
        return new UserHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull UserHolder holder, int position) {
        User currentUser = users.get(position);
        holder.tvUserEmail.setText(currentUser.getUser_email());
        holder.tvUserPassword.setText(currentUser.getUser_password());
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public void setUsers(List<User> users) {
        this.users = users;
        notifyDataSetChanged();
    }

    public User getUserAt(int position){
        return users.get(position);
    }

    class UserHolder extends RecyclerView.ViewHolder {
        private TextView tvUserEmail;
        private TextView tvUserPassword;

        public UserHolder(View itemView) {
            super(itemView);
            tvUserEmail = itemView.findViewById(R.id.text_view_user_email);
            tvUserPassword = itemView.findViewById(R.id.text_view_user_password);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();

                    if(listener != null && position != RecyclerView.NO_POSITION){
                        listener.onItemClick(users.get(position));
                    }

                }
            });


        }
    }

    public interface OnItemClickListener{
        void onItemClick(User user);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }
}
