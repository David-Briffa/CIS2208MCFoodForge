package com.example.cis2208mcfoodforge.Database;

public class MeasurementQty {

    private int amount;
    private int measurement_qty_id;

    public MeasurementQty(int amount, int measurement_qty_id) {
        this.amount = amount;
        this.measurement_qty_id = measurement_qty_id;
    }
    public int getMeasurementAmount() {
        return amount;
    }

    public int getMeasurementQtyID() {
        return measurement_qty_id;
    }

}
