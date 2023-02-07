package asap.asap.tatonas_test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import asap.asap.tatonas_test.Adapter.SensorAdapter;
import asap.asap.tatonas_test.Api.ApiClient;
import asap.asap.tatonas_test.Api.ApiInterface;
import asap.asap.tatonas_test.Model.Sensor;
import asap.asap.tatonas_test.Response.SensorResponse;
import asap.asap.tatonas_test.databinding.ActivitySensorBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SensorActivity extends AppCompatActivity {
    private static final String TAG = "SensorActivity";

    ActivitySensorBinding binding;

    RecyclerView.LayoutManager layoutManager;
    SensorAdapter sensorAdapter;
    List<Sensor> sensorList = new ArrayList<>();
    ApiInterface apiInterface;

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySensorBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        apiInterface = ApiClient.getClient().create(ApiInterface.class);

        layoutManager = new LinearLayoutManager(this);

        Call<SensorResponse> call = apiInterface.getSensor();
        call.enqueue(new Callback<SensorResponse>() {
            @Override
            public void onResponse(Call<SensorResponse> call, Response<SensorResponse> response) {
                if (response.body() != null) {
                    sensorList.addAll(response.body().getSensors());
                    Log.d(TAG, "onResponse: " + response.body().getMessage());
                    Log.d(TAG, "onResponse: response size " + response.body().getSensors().size());
                    sensorAdapter = new SensorAdapter(binding.getRoot().getContext(), sensorList);
                    binding.rvSensor.setLayoutManager(layoutManager);
                    binding.rvSensor.setAdapter(sensorAdapter);
                    binding.rvSensor.hasFixedSize();
                    sensorAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<SensorResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t);
            }
        });

    }
}