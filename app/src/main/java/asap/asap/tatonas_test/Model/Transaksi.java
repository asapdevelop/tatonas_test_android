package asap.asap.tatonas_test.Model;

import com.google.gson.annotations.SerializedName;

public class Transaksi {
    @SerializedName("id")
    private int id;
    @SerializedName("hardware")
    private int hardware;
    @SerializedName("created_at")
    private String created_at;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHardware() {
        return hardware;
    }

    public void setHardware(int hardware) {
        this.hardware = hardware;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }
}
