package asap.asap.tatonas_test.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import asap.asap.tatonas_test.Model.Sensor;
import asap.asap.tatonas_test.R;
import asap.asap.tatonas_test.databinding.ItemSensorBinding;

public class SensorAdapter extends RecyclerView.Adapter<SensorAdapter.ViewHolder> {
    private static final String TAG = "SensorAdapter";
    Context context;
    List<Sensor> sensorList = new ArrayList<>();

    public SensorAdapter(Context context, List<Sensor> sensorList) {
        this.context = context;
        this.sensorList = sensorList;
    }

    @NonNull
    @Override
    public SensorAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ItemSensorBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SensorAdapter.ViewHolder holder, int position) {
        Sensor sensor = sensorList.get(position);
        holder.binding.tvSensor.setText(sensor.getSensor());
        holder.binding.tvNama.setText(sensor.getNama());
        holder.binding.tvUnit.setText(sensor.getUnit());
    }

    @Override
    public int getItemCount() {
        return sensorList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ItemSensorBinding binding;

        public ViewHolder(ItemSensorBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
