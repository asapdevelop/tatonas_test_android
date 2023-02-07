package asap.asap.tatonas_test.Response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import asap.asap.tatonas_test.Model.HardwareDetail;


public class HardwareDetailResponse {
    @SerializedName("error")
    private String error;
    @SerializedName("message")
    private String message;
    @SerializedName("HardwareDetail")
    private List<HardwareDetail> hardwareDetails;

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

    public List<HardwareDetail> getHardwareDetails() {
        return hardwareDetails;
    }

    public void setHardwareDetails(List<HardwareDetail> hardwareDetails) {
        this.hardwareDetails = hardwareDetails;
    }
}
