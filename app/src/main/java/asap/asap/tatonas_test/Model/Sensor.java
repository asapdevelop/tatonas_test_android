package asap.asap.tatonas_test.Model;

import com.google.gson.annotations.SerializedName;

public class Sensor {

    @SerializedName("sensor")
    private String sensor;
    @SerializedName("nama")
    private String nama;
    @SerializedName("unit")
    private String unit;

    public String getSensor() {
        return sensor;
    }

    public void setSensor(String sensor) {
        this.sensor = sensor;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
