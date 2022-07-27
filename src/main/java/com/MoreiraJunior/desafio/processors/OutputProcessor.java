package com.MoreiraJunior.desafio.processors;

import com.MoreiraJunior.desafio.models.*;
import com.MoreiraJunior.desafio.repository.Repository;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class OutputProcessor {

    private final Repository repository = Repository.getRepository();

    public int amountOfClients(String fileName){
        List<Customer> customers = repository.getInputFiles().get(fileName).getCustomers();
        return (int) customers.stream().map(Customer::getCnpj).distinct().count();
    }

    public int amountOfSalesmen(String fileName) {
        List<Salesman> salesmen = repository.getInputFiles().get(fileName).getSalesmen();
        return (int) salesmen.stream().map(Salesman::getCpf).distinct().count();
    }
    public String mostExpensiveSale(){
        List<Sale> sales = new ArrayList<>();
        repository.getInputFiles().forEach((s, inputFile) -> sales.addAll(inputFile.getSales()));
        Map<String, BigDecimal> salesValues = sales.stream()
                .collect(Collectors.groupingBy(Sale::getSaleId, Collectors.reducing(BigDecimal.ZERO, Sale::getValue, BigDecimal::add)));
        List<Map.Entry<String, BigDecimal>> ranking = new LinkedList<>(salesValues.entrySet());
        ranking.sort((o1, o2) -> o2.getValue().compareTo(o1.getValue()));
        return ranking.size() > 0 ? ranking.get(0).getKey() : "Do not have sales yet!";
    }

    public String worstSalesmanEver() {
        List<Sale> sales = new ArrayList<>();
        repository.getInputFiles().forEach((s, inputFile) -> sales.addAll(inputFile.getSales()));
        Map<String, BigDecimal> salesValues = sales.stream()
                .collect(Collectors.groupingBy(Sale::getSalesman, Collectors.reducing(BigDecimal.ZERO, Sale::getValue, BigDecimal::add)));
        List<Map.Entry<String, BigDecimal>> ranking = new LinkedList<>(salesValues.entrySet());
        ranking.sort((o1, o2) -> o1.getValue().compareTo(o2.getValue()));
        return ranking.size() > 0 ? ranking.get(0).getKey() : "Do not have sales yet!";
    }

    public String generateOutput(String fileName){
        Output output = new Output(fileName, amountOfClients(fileName), amountOfSalesmen(fileName), mostExpensiveSale(), worstSalesmanEver());
        return output.toString();
    }
}
