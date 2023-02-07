package asap.asap.tatonas_test.Response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import asap.asap.tatonas_test.Model.Sensor;

public class SensorResponse {
    @SerializedName("error")
    private String error;
    @SerializedName("message")
    private String message;
    @SerializedName("Sensor")
    private List<Sensor> sensors;

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

    public List<Sensor> getSensors() {
        return sensors;
    }

    public void setSensors(List<Sensor> sensors) {
        this.sensors = sensors;
    }
}
