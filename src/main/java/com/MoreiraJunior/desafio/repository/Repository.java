package com.MoreiraJunior.desafio.repository;

import com.MoreiraJunior.desafio.models.InputFile;

import java.util.HashMap;
import java.util.Map;

public class Repository {

    private static Repository repository;
    private Map<String, InputFile> inputFiles = new HashMap<>();

    public Repository (){

    }

    public static Repository getRepository (){
        if(repository == null){
            repository = new Repository();
        }
        return repository;
    }

    public Map<String, InputFile> getInputFiles() {
        return inputFiles;
    }

    public void setInputFiles(Map<String, InputFile> inputFiles) {
        this.inputFiles = inputFiles;
    }
}
