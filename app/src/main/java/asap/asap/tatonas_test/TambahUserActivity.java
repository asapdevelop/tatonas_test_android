package asap.asap.tatonas_test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import asap.asap.tatonas_test.Api.ApiClient;
import asap.asap.tatonas_test.Api.ApiInterface;
import asap.asap.tatonas_test.Response.BaseResponse;
import asap.asap.tatonas_test.databinding.ActivityTambahUserBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TambahUserActivity extends AppCompatActivity {
    private static final String TAG = "TambahUserActivity";
    ActivityTambahUserBinding binding;
    ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTambahUserBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();

        apiInterface = ApiClient.getClient().create(ApiInterface.class);

        binding.imgSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<BaseResponse> call = apiInterface.createUser(
                        binding.etUsername.getText().toString(),
                        binding.etPassword.getText().toString(),
                        binding.etNamaLengkap.getText().toString(),
                        binding.etJabatan.getText().toString(),
                        binding.etNoTelp.getText().toString(),
                        binding.etEmail.getText().toString()
                );
                call.enqueue(new Callback<BaseResponse>() {
                    @Override
                    public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                        Log.d(TAG, "onResponse: " + response.message());
                    }

                    @Override
                    public void onFailure(Call<BaseResponse> call, Throwable t) {
                        Log.d(TAG, "onFailure: " +t);
                    }
                });
            }
        });



    }
}