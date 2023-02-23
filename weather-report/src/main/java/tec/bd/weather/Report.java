package tec.bd.weather;

public class Report {

    private float temperature;
    private float pressure;
    private float temp_min;

    private float temp_max;

    private float humidity;

    public Report() {

    }

    public float getTemp_min() {
        return temp_min;
    }

    public void setTemp_min(float temp_min) {
        this.temp_min = temp_min;
    }

    public float getTemp_max() {
        return temp_max;
    }

    public void setTemp_max(float temp_max) {
        this.temp_max = temp_max;
    }

    public float getHumidity() {
        return humidity;
    }

    public void setHumidity(float humidity) {
        this.humidity = humidity;
    }

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public float getPressure() {
        return pressure;
    }

    public void setPressure(float pressure) {
        this.pressure = pressure;
    }

    @Override
    public String toString() {
        return ">>> humidity=" + humidity +
        ", pressure=" + pressure +
        ", temp=" + temperature +
        ", tempMax=" + temp_max +
        ", tempMin=" + temp_min +
        "<<<";
    }
}
