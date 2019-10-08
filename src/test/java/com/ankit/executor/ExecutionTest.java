package com.ankit.executor;

import com.ankit.executor.fileSearcher.FileParser;
import com.ankit.executor.fileSearcher.TextFileParser;
import com.ankit.model.DataAndStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class ExecutionTest {

    DataAndStatus dataAndStatus;
    File file;

    @BeforeEach
    public void parseFile()  {
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        file = new File(classLoader.getResource("test.txt").getFile());
    }


    @Test
    public void shouldFindWord() throws IOException {
        FileParser fileParser = new TextFileParser();
        dataAndStatus = fileParser.parseFile(file);
        blockFuture(dataAndStatus);
        DictionarySearcher dictionarySearcher = new DictionarySearcher(dataAndStatus.getDictionaryAttMappedByWord());
        Assertions.assertTrue(dictionarySearcher.isWordPresent("abcd"));

    }

    private static void blockFuture(DataAndStatus dataAndStatus) {
        dataAndStatus.getFutureList().forEach(future ->
                {
                    try {
                        future.get();
                    } catch (InterruptedException | ExecutionException e) {
                        e.printStackTrace();
                    }
                }
        );
    }
}
