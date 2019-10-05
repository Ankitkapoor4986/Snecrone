package com.ankit;

import com.ankit.executor.DictionarySearcher;
import com.ankit.executor.FileParser;
import com.ankit.model.DataAndStatus;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class Executor {

    public static void main(String[] args) throws IOException {
        File file = new File("/home/ankit/IdeaProjects/Reference/Snecrone/src/main/resources/test.txt");
        FileParser fileParser = new FileParser();
        DataAndStatus dataAndStatus = fileParser.parseFile(file);
        dataAndStatus.getFutureList().forEach(future ->
        {
            try {
                future.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
            );
        DictionarySearcher dictionarySearcher = new DictionarySearcher(dataAndStatus.getDictionarAttMappedbWord());
        dictionarySearcher.isWordPresent("abcd");

    }
}
