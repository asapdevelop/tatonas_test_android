package asap.asap.tatonas_test.Api;

import asap.asap.tatonas_test.Response.AbsenResponse;
import asap.asap.tatonas_test.Response.BaseResponse;
import asap.asap.tatonas_test.Response.HardwareDetailResponse;
import asap.asap.tatonas_test.Response.HardwareResponse;
import asap.asap.tatonas_test.Response.SensorResponse;
import asap.asap.tatonas_test.Response.TransaksiDetailResponse;
import asap.asap.tatonas_test.Response.TransaksiResponse;
import asap.asap.tatonas_test.Response.UserResponse;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiInterface {

    //Absen
    @POST("Absen.php?mode=absenMasuk")
    @FormUrlEncoded
    Call<BaseResponse> absenMasuk(@Field("username") String username,
                                  @Field("latitude") Double latitude,
                                  @Field("longitude") Double longitude,
                                  @Field("foto") String foto);

    @POST("Absen.php?mode=absenPulang")
    @FormUrlEncoded
    Call<BaseResponse> absenPulang(@Field("username") String username,
                                   @Field("latitude") Double latitude,
                                   @Field("longitude") Double longitude,
                                   @Field("foto") String foto);

    @GET("Absen.php?mode=getAbsen")
    Call<AbsenResponse> getAbsen();

    //Hardware
    @POST("Hardware.php?mode=createHardware")
    @FormUrlEncoded
    Call<BaseResponse> createHardware(@Field("hardware") int hardware,
                                      @Field("location") String location,
                                      @Field("latitude") String latitude,
                                      @Field("longitude") String longitude);

    @POST("Hardware.php?mode=ubahHardware")
    @FormUrlEncoded
    Call<BaseResponse> ubahHardware(@Field("hardware") int hardware,
                                    @Field("location") String location,
                                    @Field("latitude") String latitude,
                                    @Field("longitude") String longitude,
                                    @Field("deleted") String deleted);

    @GET("Hardware.php?mode=getHardware")
    Call<HardwareResponse> getHardware();

    @POST("Hardware.php?mode=deleteHardware")
    @FormUrlEncoded
    Call<BaseResponse> deleteHardware(@Field("hardware") int hardware);

    //Hardware Detail
    @POST("HardwareDetail.php?mode=createHardwareDetail")
    @FormUrlEncoded
    Call<BaseResponse> createHardwareDetail(@Field("hardware") int hardware,
                                            @Field("sensor") String sensor);

    @POST("HardwareDetail.php?mode=ubahHardwareDetail")
    @FormUrlEncoded
    Call<BaseResponse> ubahHardwareDetail(@Field("id") int id,
                                          @Field("hardware") int hardware,
                                          @Field("sensor") String sensor,
                                          @Field("deleted") String deleted);

    @GET("HardwareDetail.php?mode=getHardwareDetail")
    Call<HardwareDetailResponse> getHardwareDetail();

    @POST("HardwareDetail.php?mode=deleteHardwareDetail")
    @FormUrlEncoded
    Call<BaseResponse> deleteHardwareDetail(@Field("id") int id);

    //Sensor
    @POST("Sensor.php?mode=createSensor")
    @FormUrlEncoded
    Call<BaseResponse> createSensor(@Field("sensor") String sensor,
                                    @Field("nama") String nama,
                                    @Field("unit") String unit);

    @POST("Sensor.php?mode=ubahSensor")
    @FormUrlEncoded
    Call<BaseResponse> ubahSensor(@Field("sensor") String sensor,
                                  @Field("nama") String nama,
                                  @Field("unit") String unit,
                                  @Field("deleted") String deleted);

    @GET("Sensor.php?mode=getSensor")
    Call<SensorResponse> getSensor();

    @POST("Sensor.php?mode=deleteSensor")
    @FormUrlEncoded
    Call<BaseResponse> deleteSensor(@Field("sensor") String sensor);

    //Transaksi

    @POST("Transaksi.php?mode=createTransaksi")
    @FormUrlEncoded
    Call<BaseResponse> createTransaksi(@Field("hardware") int hardware);

    @GET("Transaksi.php?mode=getTransaksi")
    Call<TransaksiResponse> getTransaksi();

    @POST("Transaksi.php?mode=getTransaksiByHardware")
    @FormUrlEncoded
    Call<TransaksiResponse> getTransaksiByHardware(@Field("hardware") int hardware);

    //Transaksi Detail

    @POST("TransaksiDetail.php?mode=createTransaksiDetail")
    @FormUrlEncoded
    Call<BaseResponse> createTransaksiDetail(@Field("id_trans") int id_trans,
                                             @Field("hardware") String hardware,
                                             @Field("sensor") String sensor,
                                             @Field("value") Double value);

    @POST("TransaksiDetail.php?mode=ubahTransaksiDetail")
    @FormUrlEncoded
    Call<BaseResponse> ubahTransaksiDetail(@Field("id") int id,
                                           @Field("id_trans") int id_trans,
                                           @Field("hardware") String hardware,
                                           @Field("sensor") String sensor,
                                           @Field("value") Double value);

    @GET("TransaksiDetail.php?mode=getTransaksiDetail")
    Call<TransaksiDetailResponse> getTransaksiDetail();

    @POST("TransaksiDetail.php?mode=deleteTransaksiDetail")
    @FormUrlEncoded
    Call<BaseResponse> deleteTransaksiDetail(@Field("id") int id);

    @POST("TransaksiDetail.php?mode=getTransaksiDetailByTrans")
    @FormUrlEncoded
    Call<TransaksiDetailResponse> getTransaksiDetailByTrans(@Field("id_trans") int id_trans);

    //User
    @POST("User.php?mode=createUser")
    @FormUrlEncoded
    Call<BaseResponse> createUser(@Field("user") String username,
                                  @Field("passs") String password,
                                  @Field("nama_lengkap") String nama_lengkap,
                                  @Field("jabatan") String jabatan,
                                  @Field("no_telp") String no_telp,
                                  @Field("email") String email);

    @GET("User.php?mode=getUsers")
    Call<UserResponse> getUsers();

    @POST("User.php?mode=ubahUser")
    @FormUrlEncoded
    Call<BaseResponse> ubahUser(@Field("user") String username,
                                @Field("nama_lengkap") String nama_lengkap,
                                @Field("jabatan") String jabatan,
                                @Field("no_telp") String no_telp,
                                @Field("email") String email);

    @POST("User.php?mode=deleteUser")
    @FormUrlEncoded
    Call<BaseResponse> deleteUser(@Field("user") String username);

    @POST("User.php?mode=login")
    @FormUrlEncoded
    Call<UserResponse> login(@Field("user") String username,
                             @Field("passs") String password);

//    @Headers("Content-Type: application/json")
    @Headers({"Accept: application/json"})
    @POST("User.php?mode=cekPassword")
    @FormUrlEncoded
    Call<BaseResponse> cekPassword(@Field("user") String username,
                                   @Field("passs") String password);


}
