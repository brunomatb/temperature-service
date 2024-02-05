package pt.com.batista.temperatureservice.model;

import java.time.Instant;

public class TemperatureSensorRequest {
    private String id;

    private String uid;
    private double temperature;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private Instant timestamp;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }
}
