package asap.asap.tatonas_test.Response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import asap.asap.tatonas_test.Model.Transaksi;


public class TransaksiResponse {
    @SerializedName("error")
    private String error;
    @SerializedName("message")
    private String message;
    @SerializedName("Transaksi")
    private List<Transaksi> transaksis;

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

    public List<Transaksi> getTransaksis() {
        return transaksis;
    }

    public void setTransaksis(List<Transaksi> transaksis) {
        this.transaksis = transaksis;
    }
}
