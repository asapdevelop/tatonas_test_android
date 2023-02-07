package asap.asap.tatonas_test.Response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import asap.asap.tatonas_test.Model.TransaksiDetail;


public class TransaksiDetailResponse {
    @SerializedName("error")
    private String error;
    @SerializedName("message")
    private String message;
    @SerializedName("TransaksiDetail")
    private List<TransaksiDetail> transaksiDetails;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<TransaksiDetail> getTransaksiDetails() {
        return transaksiDetails;
    }

    public void setTransaksiDetails(List<TransaksiDetail> transaksiDetails) {
        this.transaksiDetails = transaksiDetails;
    }
}
