package asap.asap.tatonas_test;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import asap.asap.tatonas_test.Adapter.HardwareDetailAdapter;
import asap.asap.tatonas_test.Api.ApiClient;
import asap.asap.tatonas_test.Api.ApiInterface;
import asap.asap.tatonas_test.Model.Hardware;
import asap.asap.tatonas_test.Model.Transaksi;
import asap.asap.tatonas_test.Model.TransaksiDetail;
import asap.asap.tatonas_test.Response.TransaksiDetailResponse;
import asap.asap.tatonas_test.Response.TransaksiResponse;
import asap.asap.tatonas_test.databinding.ActivityHardwareDetailBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HardwareDetailActivity extends AppCompatActivity {
    private static final String TAG = "HardwareDetailActivity";
    ApiInterface apiInterface;
    List<Transaksi> transaksiList = new ArrayList<>();
    List<TransaksiDetail> transaksiDetailList = new ArrayList<>();
    HardwareDetailAdapter adapter;
    Hardware hardware;
    RecyclerView.LayoutManager layoutManager;
    Double max, min;
    double latitude, longitude;

    ActivityHardwareDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHardwareDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Intent intent = getIntent();
        hardware = (Hardware)intent.getExtras().getSerializable("HARDWARE");
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        layoutManager = new LinearLayoutManager(this);

        binding.map.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull GoogleMap googleMap) {
                CameraPosition cPosition = new CameraPosition.Builder()
                        .target(new LatLng(latitude, longitude))
                        .zoom(13).bearing(0).tilt(45).build();
                googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cPosition));
                googleMap.getUiSettings().setZoomGesturesEnabled(true);
                googleMap.getUiSettings().setZoomControlsEnabled(false);
                LatLng location =new LatLng(hardware.getLatitude(), hardware.getLongitude());
                googleMap.addMarker(new MarkerOptions()
                        .position(location)
                        .title("Hardware Location"));
            }
        });


        Call<TransaksiResponse> call = apiInterface.getTransaksiByHardware(hardware.getHardware());
        call.enqueue(new Callback<TransaksiResponse>() {
            @Override
            public void onResponse(Call<TransaksiResponse> call, Response<TransaksiResponse> response) {
                transaksiList.addAll(response.body().getTransaksis());
                ambilDataDetail();
            }

            @Override
            public void onFailure(Call<TransaksiResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t);
            }
        });
    }

    private void ambilDataDetail() {
        for (int a=0; a<transaksiList.size(); a++){
            Call<TransaksiDetailResponse> call = apiInterface.getTransaksiDetailByTrans(transaksiList.get(a).getId());
            call.enqueue(new Callback<TransaksiDetailResponse>() {
                @Override
                public void onResponse(Call<TransaksiDetailResponse> call, Response<TransaksiDetailResponse> response) {
                    transaksiDetailList.addAll(response.body().getTransaksiDetails());
                    setData();
                }

                @Override
                public void onFailure(Call<TransaksiDetailResponse> call, Throwable t) {
                    Log.d(TAG, "onFailure: " + t);
                }
            });
        }
    }

    private void setData() {
        binding.tvLocation.setText(hardware.getLocation());
        binding.tvCoordinate.setText(hardware.getLatitude() + ", " + hardware.getLongitude());
        for (int a=0; a<transaksiDetailList.size(); a++){
            if (a==0){
                max = transaksiDetailList.get(a).getValue();
                min = transaksiDetailList.get(a).getValue();
            } else {
                if (transaksiDetailList.get(a).getValue()>max){
                    max = transaksiDetailList.get(a).getValue();
                }
                if (transaksiDetailList.get(a).getValue()<min){
                    min = transaksiDetailList.get(a).getValue();
                }
            }
        }
        binding.tvMaxValue.setText(String.valueOf(max));
        binding.tvMinValue.setText(String.valueOf(min));
        adapter = new HardwareDetailAdapter(binding.getRoot().getContext(), transaksiList, transaksiDetailList);
        binding.rvDetailHardware.setLayoutManager(layoutManager);
        binding.rvDetailHardware.setAdapter(adapter);
        binding.rvDetailHardware.hasFixedSize();
        adapter.notifyDataSetChanged();
    }
}