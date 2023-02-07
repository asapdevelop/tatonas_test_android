package asap.asap.tatonas_test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import asap.asap.tatonas_test.Adapter.UserAdapter;
import asap.asap.tatonas_test.Api.ApiClient;
import asap.asap.tatonas_test.Api.ApiInterface;
import asap.asap.tatonas_test.Model.User;
import asap.asap.tatonas_test.Response.UserResponse;
import asap.asap.tatonas_test.databinding.ActivityUserBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserActivity extends AppCompatActivity {
    private static final String TAG = "UserActivity";
    ActivityUserBinding binding;
    ApiInterface apiInterface;
    RecyclerView.LayoutManager layoutManager;
    UserAdapter adapter;
    List<User> userList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUserBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        layoutManager = new LinearLayoutManager(this);

        Call<UserResponse> call = apiInterface.getUsers();
        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if (response.body() != null) {
                    userList.addAll(response.body().getUsers());
                }
                adapter = new UserAdapter(binding.getRoot().getContext(), userList);
                binding.rvUser.setLayoutManager(layoutManager);
                binding.rvUser.setAdapter(adapter);
                binding.rvUser.hasFixedSize();
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {

            }
        });

        binding.tvTambahUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(binding.getRoot().getContext(), TambahUserActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart: ");
    }
}