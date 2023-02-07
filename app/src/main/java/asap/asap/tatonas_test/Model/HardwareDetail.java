package asap.asap.tatonas_test.Model;

import com.google.gson.annotations.SerializedName;

public class HardwareDetail {

    @SerializedName("id")
    private int id;
    @SerializedName("hardware")
    private int hardware;
    @SerializedName("sensor")
    private String sensor;

    @SerializedName("deleted")
    private int deleted;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDeleted() {
        return deleted;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }

    public int getHardware() {
        return hardware;
    }

    public void setHardware(int hardware) {
        this.hardware = hardware;
    }

    public String getSensor() {
        return sensor;
    }

    public void setSensor(String sensor) {
        this.sensor = sensor;
    }
}
