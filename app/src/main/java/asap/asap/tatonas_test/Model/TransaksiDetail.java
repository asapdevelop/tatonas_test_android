package asap.asap.tatonas_test.Model;

import com.google.gson.annotations.SerializedName;

public class TransaksiDetail {
    @SerializedName("id")
    private int id;
    @SerializedName("id_trans")
    private int id_trans;
    @SerializedName("hardware")
    private int hardware;
    @SerializedName("sensor")
    private String sensor;
    @SerializedName("value")
    private Double value;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_trans() {
        return id_trans;
    }

    public void setId_trans(int id_trans) {
        this.id_trans = id_trans;
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

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }
}
