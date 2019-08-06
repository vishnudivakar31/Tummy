package com.wanderingThinker.Tummy.supportingdocuments;

public class Ingrident {
    private String name;
    private Float quantity;
    private String unit;

    public Ingrident() {
    }

    public Ingrident(String name, Float quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    public Ingrident(String name, Float quantity, String unit) {
        this.name = name;
        this.quantity = quantity;
        this.unit = unit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getQuantity() {
        return quantity;
    }

    public void setQuantity(Float quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
