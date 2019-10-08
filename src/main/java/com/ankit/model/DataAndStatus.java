package com.ankit.model;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

public class DataAndStatus {


    private List<Future> futureList;
    private Map<String, Map<String, DictionaryAttributes>> dictionaryAttMappedByWord;


    public DataAndStatus(List<Future> futureList, Map<String, Map<String, DictionaryAttributes>> dictionaryAttMappedByWord) {
        this.futureList = futureList;
        this.dictionaryAttMappedByWord = dictionaryAttMappedByWord;

    }

    public List<Future> getFutureList() {
        return futureList;
    }

    public Map<String, Map<String, DictionaryAttributes>> getDictionaryAttMappedByWord() {
        return dictionaryAttMappedByWord;
    }
}
