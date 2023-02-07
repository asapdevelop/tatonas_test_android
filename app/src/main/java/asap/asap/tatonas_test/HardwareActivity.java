package asap.asap.tatonas_test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import asap.asap.tatonas_test.Adapter.HardwareAdapter;
import asap.asap.tatonas_test.Api.ApiClient;
import asap.asap.tatonas_test.Api.ApiInterface;
import asap.asap.tatonas_test.Model.Hardware;
import asap.asap.tatonas_test.Response.HardwareResponse;
import asap.asap.tatonas_test.databinding.ActivityHardwareBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HardwareActivity extends AppCompatActivity {
    private static final String TAG = "HardwareActivity";

    ActivityHardwareBinding binding;
    ApiInterface apiInterface;
    RecyclerView.LayoutManager layoutManager;
    HardwareAdapter hardwareAdapter;
    List<Hardware> hardwareList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHardwareBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        layoutManager = new LinearLayoutManager(this);

        Call<HardwareResponse> call = apiInterface.getHardware();
        call.enqueue(new Callback<HardwareResponse>() {
            @Override
            public void onResponse(Call<HardwareResponse> call, Response<HardwareResponse> response) {
                hardwareList.addAll(response.body().getHardwares());
                hardwareAdapter = new HardwareAdapter(binding.getRoot().getContext(), hardwareList);
                binding.rvHardware.setLayoutManager(layoutManager);
                binding.rvHardware.setAdapter(hardwareAdapter);
                binding.rvHardware.hasFixedSize();
                hardwareAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<HardwareResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t);
            }
        });


    }
}