package asap.asap.tatonas_test.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import asap.asap.tatonas_test.HardwareDetailActivity;
import asap.asap.tatonas_test.Model.Hardware;
import asap.asap.tatonas_test.databinding.ItemHardwareBinding;
import asap.asap.tatonas_test.databinding.ItemSensorBinding;

public class HardwareAdapter extends RecyclerView.Adapter<HardwareAdapter.Holder> {
    private static final String TAG = "HardwareAdapter";
    Context context;
    List<Hardware> hardwareList = new ArrayList<>();

    public HardwareAdapter(Context context, List<Hardware> hardwareList) {
        this.context = context;
        this.hardwareList = hardwareList;
    }

    @NonNull
    @Override
    public HardwareAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(ItemHardwareBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull HardwareAdapter.Holder holder, int position) {
        Hardware hardware = hardwareList.get(position);
        holder.binding.tvHardware.setText(String.valueOf(hardware.getHardware()));
        holder.binding.tvLocation.setText(hardware.getLocation());
        holder.binding.tvLatitude.setText(String.valueOf(hardware.getLatitude()));
        holder.binding.tvLongitude.setText(String.valueOf(hardware.getLongitude()));

        holder.binding.layoutCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, HardwareDetailActivity.class);
                intent.putExtra("HARDWARE", hardware);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return hardwareList.size();
    }

    public class Holder extends RecyclerView.ViewHolder{
        private ItemHardwareBinding binding;

        public Holder(ItemHardwareBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
