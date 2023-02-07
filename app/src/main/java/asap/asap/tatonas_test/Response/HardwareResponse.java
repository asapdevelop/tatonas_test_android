package asap.asap.tatonas_test.Response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import asap.asap.tatonas_test.Model.Hardware;


public class HardwareResponse {
    @SerializedName("error")
    private String error;
    @SerializedName("message")
    private String message;
    @SerializedName("Hardware")
    private List<Hardware> hardwares;

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

    public List<Hardware> getHardwares() {
        return hardwares;
    }

    public void setHardwares(List<Hardware> hardwares) {
        this.hardwares = hardwares;
    }
}
