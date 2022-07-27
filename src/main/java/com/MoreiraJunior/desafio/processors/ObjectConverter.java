package com.MoreiraJunior.desafio.processors;

import com.MoreiraJunior.desafio.exceptions.InvalidDataException;
import com.MoreiraJunior.desafio.models.*;
import com.MoreiraJunior.desafio.repository.Repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ObjectConverter {

    private final int NUMBER_OF_DATA_FIELDS = 4;
    private final String SALESMAN_ID = "001";
    private final String CUSTOMER_ID = "002";
    private final String SALE_ID = "003";
    private final Repository repository = Repository.getRepository();

    public void convertToObjects(String data, String fileName) {
        String[] arrayString = data.split("ç|\\n|(\\s)(?=\\d)");
        if (arrayString.length % NUMBER_OF_DATA_FIELDS != 0){
            throw new InvalidDataException("Number of data fields is incompatible!");
        }
        List<Sale> sales = new ArrayList<>();
        List<Customer> customers = new ArrayList<>();
        List<Salesman> salesmen = new ArrayList<>();
        for (int i = 0; i < arrayString.length; i += NUMBER_OF_DATA_FIELDS) {
            switch (arrayString[i]) {
                case SALE_ID:
                    List<Item> items = new ArrayList<>();
                    String[] itemArray = arrayString[i + 2].replaceAll("\\[|\\]", "").replaceAll("-|,", " ").split(" ");
                    for (int j = 0; j < itemArray.length; j += 3) {
                        Item item = new Item(itemArray[j], Integer.parseInt(itemArray[j + 1]), new BigDecimal(itemArray[j + 2]));
                        items.add(item);
                    }
                    Sale sale = new Sale(arrayString[i + 1], items, arrayString[i + 3]);
                    sales.add(sale);
                    break;
                case CUSTOMER_ID:
                    Customer customer = new Customer(arrayString[i + 1], arrayString[i + 2], arrayString[i + 3]);
                    customers.add(customer);
                    break;
                case SALESMAN_ID:
                    Salesman salesman = new Salesman(arrayString[i + 1], arrayString[i + 2], new BigDecimal(arrayString[i + 1]));
                    salesmen.add(salesman);
                    break;
                default:
                    throw new InvalidDataException("Entity id not found!");
            }
        }
        storeObjects(sales, customers, salesmen, fileName);
    }
    public void storeObjects(List<Sale> sales, List<Customer> customers, List<Salesman> salesmen, String fileName){
        InputFile inputFile = new InputFile(fileName, salesmen, customers, sales);
        repository.getInputFiles().put(fileName, inputFile);
    }
}
