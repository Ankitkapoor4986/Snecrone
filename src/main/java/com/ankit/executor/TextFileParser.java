package com.ankit.executor;

import com.ankit.model.DataAndStatus;
import com.ankit.model.DictionaryAttributes;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TextFileParser implements FileParser {

    private File file;


    private int numCores = 4;
    private ExecutorService service = Executors.newFixedThreadPool(numCores);


    @Override
    public DataAndStatus parseFile(File file) throws IOException {
        List<String> lines = Files.readAllLines(file.toPath(), Charset.defaultCharset());
        Map<String, Map<String, DictionaryAttributes>> dictionaryAttMappedbWord = new ConcurrentHashMap<>();

        Collection<List<String>> splitedLists = splitList(lines);
        List<Future> futureList = new ArrayList<>();
        splitedLists.forEach(splittedList -> submitDictionaryTask(dictionaryAttMappedbWord, futureList, splittedList));

        return new DataAndStatus(futureList,dictionaryAttMappedbWord);

    }

    private void submitDictionaryTask(Map<String, Map<String, DictionaryAttributes>> dictionaryAttMappedbWord,
                                      List<Future> futureList, List<String> splittedlist) {
        Runnable runnable = () -> splittedlist.forEach(line -> dictionaryAttMappedbWord.putAll(parseLine(line)));
        Future<?> future = service.submit(runnable);
        futureList.add(future);
    }

    private Collection<List<String>> splitList(List<String> lines) {
        final int chunkSize = 5000;
        final AtomicInteger counter = new AtomicInteger();

        return lines.stream()
                .collect(Collectors.groupingBy(it -> counter.getAndIncrement() / chunkSize))
                .values();


    }

    private ConcurrentMap<String, Map<String, DictionaryAttributes>> parseLine(String line) {

        return Stream.of(line.split(" ")).
                collect(Collectors.groupingByConcurrent(
                        word -> word.substring(0, 1),
                        Collectors.toMap(Function.identity(),
                                word -> new DictionaryAttributes(word, 1)))
                );


    }



}
