package com.wanderingThinker.Tummy.supportingdocuments;

public class Ingrident {
    private String name;
    private Float quantity;

    public Ingrident() {
    }

    public Ingrident(String name, Float quantity) {
        this.name = name;
        this.quantity = quantity;
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
}
