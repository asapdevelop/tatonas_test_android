package asap.asap.tatonas_test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;

import asap.asap.tatonas_test.Api.ApiClient;
import asap.asap.tatonas_test.Api.ApiInterface;
import asap.asap.tatonas_test.Model.User;
import asap.asap.tatonas_test.Response.BaseResponse;
import asap.asap.tatonas_test.Response.UserResponse;
import asap.asap.tatonas_test.databinding.ActivityDetailUserBinding;
import asap.asap.tatonas_test.databinding.BottomDialogCekPasswordBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailUserActivity extends AppCompatActivity {
    private static final String TAG = "DetailUserActivity";

    ActivityDetailUserBinding binding;
    User user;
    ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailUserBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();

        Intent intent = getIntent();
        user = (User) intent.getExtras().getSerializable("USER");
        apiInterface = ApiClient.getClient().create(ApiInterface.class);

        binding.etUsername.setText(user.getUsername());
        binding.etNamaLengkap.setText(user.getNama_lengkap());
        binding.etJabatan.setText(user.getJabatan());
        binding.etNoTelp.setText(user.getNo_telp());
        binding.etEmail.setText(user.getEmail());
        Log.d(TAG, "onCreate: " + user.getEmail());

        binding.tvUbahPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showBottomCekPassword();
            }
        });

        binding.imgSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<BaseResponse> call = apiInterface.ubahUser(
                        user.getUsername(),
                        binding.etNamaLengkap.getText().toString(),
                        binding.etJabatan.getText().toString(),
                        binding.etNoTelp.getText().toString(),
                        binding.etEmail.getText().toString());
                call.enqueue(new Callback<BaseResponse>() {
                    @Override
                    public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                        if (!Boolean.valueOf(response.body().getError())){
                            Toast.makeText(binding.getRoot().getContext(), "Berhasil Ubah Data", Toast.LENGTH_SHORT).show();
                            Intent intent1 = new Intent(binding.getRoot().getContext(), UserActivity.class);
                            startActivity(intent1);
                        }
                    }

                    @Override
                    public void onFailure(Call<BaseResponse> call, Throwable t) {
                        Log.d(TAG, "onFailure: " + t);
                    }
                });
            }
        });

    }

    private void showBottomCekPassword() {
        BottomDialogCekPasswordBinding bottomBinding;
        bottomBinding = BottomDialogCekPasswordBinding.inflate(getLayoutInflater());
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        bottomSheetDialog.setContentView(bottomBinding.getRoot());

        bottomBinding.btnKonfirmasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: " + bottomBinding.etPasswordLama.getText().toString().trim() + user.getUsername());

                Call<BaseResponse> call = apiInterface.cekPassword(user.getUsername(), bottomBinding.etPasswordLama.getText().toString().trim());
                call.enqueue(new Callback<BaseResponse>() {
                    @Override
                    public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                        if (Boolean.valueOf(response.body().getError())){
                            Toast.makeText(binding.getRoot().getContext(), "Password Benar", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(binding.getRoot().getContext(), "Password Salah", Toast.LENGTH_SHORT).show();
                            bottomBinding.etPasswordLama.setError("Password Salah");
                        }
                    }

                    @Override
                    public void onFailure(Call<BaseResponse> call, Throwable t) {
                        Log.d(TAG, "onFailure: " + t);
                    }
                });
            }
        });
        bottomSheetDialog.show();
    }

}