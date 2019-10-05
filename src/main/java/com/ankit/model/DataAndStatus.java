package com.ankit.model;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

public class DataAndStatus {


    private List<Future> futureList;
    private Map<String, Map<String, DictionaryAttributes>> dictionarAttMappedbWord;


    public DataAndStatus(List<Future> futureList, Map<String, Map<String, DictionaryAttributes>> dictionarAttMappedbWord) {
        this.futureList = futureList;
        this.dictionarAttMappedbWord = dictionarAttMappedbWord;

    }

    public List<Future> getFutureList() {
        return futureList;
    }

    public Map<String, Map<String, DictionaryAttributes>> getDictionarAttMappedbWord() {
        return dictionarAttMappedbWord;
    }
}
