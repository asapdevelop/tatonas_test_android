package asap.asap.tatonas_test.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import asap.asap.tatonas_test.DetailUserActivity;
import asap.asap.tatonas_test.Model.User;
import asap.asap.tatonas_test.databinding.ItemHardwareBinding;
import asap.asap.tatonas_test.databinding.ItemUserBinding;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.Holder> {
    Context context;
    List<User> userList;

    public UserAdapter(Context context, List<User> userList) {
        this.context = context;
        this.userList = userList;
    }

    @NonNull
    @Override
    public UserAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(ItemUserBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.Holder holder, int position) {
        User user = userList.get(position);
        holder.binding.tvNama.setText(user.getNama_lengkap());
        holder.binding.tvJabatan.setText(user.getJabatan());

        holder.binding.layoutCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailUserActivity.class);
                intent.putExtra("USER", user);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class Holder extends RecyclerView.ViewHolder{
        private ItemUserBinding binding;
        public Holder(ItemUserBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
