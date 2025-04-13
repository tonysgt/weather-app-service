package org.tonysgt.model.weather.forecast;

public class Pressure {

    private String units;
    private Integer value;

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
