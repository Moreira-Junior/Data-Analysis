package com.MoreiraJunior.desafio.models;

import java.sql.Timestamp;

public class Output {

    private Timestamp timestamp = new java.sql.Timestamp(new java.util.Date().getTime());
    private String fileName;
    private int amountOfClients;
    private int amountOfSalesmen;
    private String mostExpensiveSale;
    private String worstSalesmanEver;

    public Output(String fileName, int amountOfClients, int amountOfSalesmen, String mostExpensiveSale, String worstSalesmanEver) {
        this.fileName = fileName;
        this.amountOfClients = amountOfClients;
        this.amountOfSalesmen = amountOfSalesmen;
        this.mostExpensiveSale = mostExpensiveSale;
        this.worstSalesmanEver = worstSalesmanEver;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getAmountOfClients() {
        return amountOfClients;
    }

    public void setAmountOfClients(int amountOfClients) {
        this.amountOfClients = amountOfClients;
    }

    public int getAmountOfSalesmen() {
        return amountOfSalesmen;
    }

    public void setAmountOfSalesmen(int amountOfSalesmen) {
        this.amountOfSalesmen = amountOfSalesmen;
    }

    public String getMostExpensiveSale() {
        return mostExpensiveSale;
    }

    public void setMostExpensiveSale(String mostExpensiveSale) {
        this.mostExpensiveSale = mostExpensiveSale;
    }

    public String getWorstSalesmanEver() {
        return worstSalesmanEver;
    }

    public void setWorstSalesmanEver(String worstSalesmanEver) {
        this.worstSalesmanEver = worstSalesmanEver;
    }

    @Override
    public String toString() {
        return "Output{" +
                "timestamp=" + timestamp +
                ", fileName='" + fileName + '\'' +
                ", amountOfClients=" + amountOfClients +
                ", amountOfSalesmen=" + amountOfSalesmen +
                ", mostExpensiveSale='" + mostExpensiveSale + '\'' +
                ", worstSalesmanEver='" + worstSalesmanEver + '\'' +
                '}';
    }
}
