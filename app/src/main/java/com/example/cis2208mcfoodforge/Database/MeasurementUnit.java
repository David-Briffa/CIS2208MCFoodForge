package com.example.cis2208mcfoodforge.Database;

public class MeasurementUnit {
    private String description;
    private int measurement_unit_id;

    public MeasurementUnit(String description, int measurement_unit_id) {
        this.description = description;
        this.measurement_unit_id = measurement_unit_id;
    }
    public String getDescription() {
        return description;
    }

    public int getMeasurement_unit_id() {
        return measurement_unit_id;
    }
}
