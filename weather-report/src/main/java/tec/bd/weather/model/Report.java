package tec.bd.weather.model;

import java.util.Date;

public class Report {

    private ReportType type;
    private float temperature;
    private float pressure;
    private float tempMin;
    private float tempMax;
    private float humidity;
    private Date date;
    private String data;

    public ReportType getType() {
        return type;
    }

    public void setType(ReportType type) {
        this.type = type;
    }
    
    public float getTempMin() {
        return tempMin;
    }

    public void setTempMin(float temp_min) {
        this.tempMin = temp_min;
    }

    public float getTempMax() {
        return tempMax;
    }

    public void setTempMax(float temp_max) {
        this.tempMax = temp_max;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public Report clone() {
        var report = new Report();
        report.setHumidity(humidity);
        report.setPressure(pressure);
        report.setTemperature(temperature);
        report.setTempMin(tempMin);
        report.setTempMax(tempMax);
        report.setDate(date != null ? (Date)date.clone() : null);
        report.setType(type);
        report.setData(data);
        return report;
    }

    @Override
    public String toString() {
        return ">>> type=" + type +
        ", query_data=" + data +
        ", humidity=" + humidity +
        ", pressure=" + pressure +
        ", temp=" + temperature +
        ", tempMax=" + tempMax +
        ", tempMin=" + tempMin +
        ", date=" + date +
        "<<<";
    }
}
