package com.epam.practice;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.Optional;

public abstract class AbstractTestWithResources {

    protected final File getTestFile(Class<? extends AbstractTestWithResources> clazz, String filename) throws FileNotFoundException {
        return tryGetTestFile(clazz, filename).orElseThrow(FileNotFoundException::new);
    }

    protected final Optional<File> tryGetTestFile(Class<? extends AbstractTestWithResources> clazz, String filename) {
        return Optional.ofNullable(clazz.getResource(filename))
                       .map(url -> {
                           try {
                               return url.toURI();
                           } catch (URISyntaxException e) {
                               throw new RuntimeException(e);
                           }
                       })
                       .map(File::new);
    }
}
