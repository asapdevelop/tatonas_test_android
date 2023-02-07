package asap.asap.tatonas_test.Response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import asap.asap.tatonas_test.Model.Absen;


public class AbsenResponse {
    @SerializedName("error")
    private String error;
    @SerializedName("message")
    private String message;
    @SerializedName("Absen")
    private List<Absen> absens;

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

    public List<Absen> getAbsens() {
        return absens;
    }

    public void setAbsens(List<Absen> absens) {
        this.absens = absens;
    }
}
