package com.MoreiraJunior.desafio.models;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Sale {

    private final String id = "003";
    private String saleId;
    private List<Item> items;
    private String salesman;

    private BigDecimal value;

    public Sale(String saleId, List<Item> items, String salesman) {
        this.saleId = saleId;
        this.items = items;
        this.salesman = salesman;
        this.setValue();
    }

    public void setValue(){
        this.value = this.items.stream().map(item -> item.getItemPrice().multiply(new BigDecimal(item.getItemQuantity()))).reduce(BigDecimal.ZERO, BigDecimal::add);
    }
    public BigDecimal getValue(){
        return this.value;
    }

    public String getId() {
        return id;
    }

    public String getSaleId() {
        return saleId;
    }

    public void setSaleId(String saleId) {
        this.saleId = saleId;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public String getSalesman() {
        return salesman;
    }

    public void setSalesman(String salesman) {
        this.salesman = salesman;
    }
}
