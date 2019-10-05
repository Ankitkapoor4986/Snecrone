package com.ankit;

import com.ankit.executor.DictionarySearcher;
import com.ankit.executor.FileParser;
import com.ankit.model.DictionaryAttributes;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class Executor {

    public static void main(String[] args) throws IOException {
        File file = new File("/home/ankit/IdeaProjects/Reference/Snecrone/src/main/resources/test.txt");
        FileParser fileParser = new FileParser();
        Map<String, Map<String, DictionaryAttributes>> dictionary = fileParser.parseFile(file);
        DictionarySearcher dictionarySearcher = new DictionarySearcher(dictionary);
        dictionarySearcher.isWordPresent("abcd");

    }
}
