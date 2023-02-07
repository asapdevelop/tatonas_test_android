package asap.asap.tatonas_test.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import asap.asap.tatonas_test.Model.Transaksi;
import asap.asap.tatonas_test.Model.TransaksiDetail;
import asap.asap.tatonas_test.databinding.ItemDetailHardwareBinding;
import asap.asap.tatonas_test.databinding.ItemSensorBinding;

public class HardwareDetailAdapter extends RecyclerView.Adapter<HardwareDetailAdapter.Holder> {
    Context context;
    List<Transaksi> transaksiList = new ArrayList<>();
    List<TransaksiDetail> transaksiDetailList = new ArrayList<>();

    public HardwareDetailAdapter(Context context, List<Transaksi> transaksiList, List<TransaksiDetail> transaksiDetailList) {
        this.context = context;
        this.transaksiList = transaksiList;
        this.transaksiDetailList = transaksiDetailList;
    }

    @NonNull
    @Override
    public HardwareDetailAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(ItemDetailHardwareBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull HardwareDetailAdapter.Holder holder, int position) {
        TransaksiDetail transaksiDetail = transaksiDetailList.get(position);
        for (int a=0; a<transaksiList.size(); a++){
            if (transaksiDetail.getId_trans()==transaksiList.get(a).getId()){
                holder.binding.tvTanggal.setText(transaksiList.get(a).getCreated_at());
            }
        }
        holder.binding.tvTransId.setText(String.valueOf(transaksiDetail.getId_trans()));
        holder.binding.tvSensor.setText(transaksiDetail.getSensor());
        holder.binding.tvValue.setText(String.valueOf(transaksiDetail.getValue()));
    }

    @Override
    public int getItemCount() {
        return transaksiDetailList.size();
    }

    public class Holder extends RecyclerView.ViewHolder{
        private ItemDetailHardwareBinding binding;
        public Holder(ItemDetailHardwareBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
