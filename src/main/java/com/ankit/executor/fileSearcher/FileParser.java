package com.ankit.executor.fileSearcher;

import com.ankit.model.DataAndStatus;

import java.io.File;
import java.io.IOException;

public interface FileParser {

    DataAndStatus parseFile(File file) throws IOException;
}
