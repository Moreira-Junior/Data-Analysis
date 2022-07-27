package com.MoreiraJunior.desafio.models;

import java.util.List;

public class InputFile {

    private String name;
    private List<Salesman> salesmen;
    private List<Customer> customers;
    private List<Sale> sales;

    public InputFile(String name, List<Salesman> salesmen, List<Customer> customers, List<Sale> sales) {
        this.name = name;
        this.salesmen = salesmen;
        this.customers = customers;
        this.sales = sales;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Salesman> getSalesmen() {
        return salesmen;
    }

    public void setSalesmen(List<Salesman> salesmen) {
        this.salesmen = salesmen;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    public List<Sale> getSales() {
        return sales;
    }

    public void setSales(List<Sale> sales) {
        this.sales = sales;
    }
}
