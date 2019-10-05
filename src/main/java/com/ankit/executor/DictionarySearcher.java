package com.ankit.executor;

import com.ankit.model.DictionaryAttributes;
import javafx.beans.value.ObservableBooleanValue;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;

public class DictionarySearcher {

    private Map<String, Map<String, DictionaryAttributes>> dictionarAttMappedbWord;

    public DictionarySearcher(Map<String, Map<String, DictionaryAttributes>> dictionarAttMappedbWord){
        this.dictionarAttMappedbWord = dictionarAttMappedbWord;
    }

    public boolean isWordPresent(String word) throws IOException {
        Map<String, DictionaryAttributes> stringDictionaryAttributesMap = dictionarAttMappedbWord.get(word.substring(0, 1));

        if(stringDictionaryAttributesMap!= null) {
            return Optional.ofNullable(stringDictionaryAttributesMap.get(word)).isPresent();
        }
        return false;

    }
}
