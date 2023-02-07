package asap.asap.tatonas_test;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextClock;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import java.text.SimpleDateFormat;
import java.util.Date;

import asap.asap.tatonas_test.Api.ApiClient;
import asap.asap.tatonas_test.Api.ApiInterface;
import asap.asap.tatonas_test.Response.BaseResponse;
import asap.asap.tatonas_test.Util.Temp;
import asap.asap.tatonas_test.databinding.ActivityAbsenBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AbsenActivity extends AppCompatActivity {
    private static final String TAG = "AbsenActivity";
    ActivityAbsenBinding binding;

    Temp temp;

    private SimpleDateFormat textDate = new SimpleDateFormat("EEEE, dd MMMM yyyy");

    ApiInterface apiInterface;
    private FusedLocationProviderClient fusedLocationClient;
    double latitude, longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAbsenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();

        temp = new Temp();
        apiInterface = ApiClient.getClient().create(ApiInterface.class);

        Log.d(TAG, "onCreate: " + temp.user.getNama_lengkap());

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        binding.tvNama.setText(temp.user.getNama_lengkap());
        binding.tvTanggal.setText(textDate.format(new Date()));
        binding.tvJam.setFormat24Hour("HH:mm:ss");
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        fusedLocationClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                Log.d(TAG, "onSuccess: ");
                latitude = location.getLatitude();
                longitude = location.getLongitude();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d(TAG, "onFailure: " +e);
            }
        });

        binding.btnMasuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<BaseResponse> call = apiInterface.absenMasuk(temp.user.getUsername(), latitude, longitude, "foto.jpg");
                call.enqueue(new Callback<BaseResponse>() {
                    @Override
                    public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                        Toast.makeText(binding.getRoot().getContext(), "Absen Berhasil", Toast.LENGTH_SHORT);
                    }

                    @Override
                    public void onFailure(Call<BaseResponse> call, Throwable t) {

                    }
                });
            }
        });

        binding.btnPulang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<BaseResponse> call = apiInterface.absenPulang(temp.user.getUsername(), latitude, longitude, "foto.jpg");
                call.enqueue(new Callback<BaseResponse>() {
                    @Override
                    public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                        Toast.makeText(binding.getRoot().getContext(), "Absen Berhasil", Toast.LENGTH_SHORT);
                    }

                    @Override
                    public void onFailure(Call<BaseResponse> call, Throwable t) {

                    }
                });
            }
        });
    }
}