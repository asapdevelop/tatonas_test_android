package asap.asap.tatonas_test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import asap.asap.tatonas_test.Api.ApiClient;
import asap.asap.tatonas_test.Api.ApiInterface;
import asap.asap.tatonas_test.Response.BaseResponse;
import asap.asap.tatonas_test.Response.UserResponse;
import asap.asap.tatonas_test.Util.Temp;
import asap.asap.tatonas_test.databinding.ActivityLoginBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    
    ActivityLoginBinding binding;
    
    ApiInterface apiInterface;
    Temp temp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();
        
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        temp = new Temp();
        
        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<UserResponse> call = apiInterface.login(binding.etUsername.getText().toString(), binding.etPassword.getText().toString());
                call.enqueue(new Callback<UserResponse>() {
                    @Override
                    public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                        temp.user = response.body().getUsers().get(0);
                        Intent intent = new Intent(binding.getRoot().getContext(), MainActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<UserResponse> call, Throwable t) {

                    }
                });
            }
        });
    }
}