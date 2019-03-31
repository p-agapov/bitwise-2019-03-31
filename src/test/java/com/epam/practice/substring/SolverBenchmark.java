package com.epam.practice.substring;

import com.epam.practice.AbstractTestWithResources;
import org.junit.jupiter.api.Test;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Paths;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@State(Scope.Benchmark)
@SuppressWarnings({"unused", "WeakerAccess"})
public class SolverBenchmark extends AbstractTestWithResources {

    private static final int NUMBER_ENGLISH_LETTERS = 26;

    @Param({"10000000", "100000000", "1000000000"})
    private int length;

    @Setup(Level.Trial)
    public void setUp() throws Exception {
        if (!tryGetTestFile(SolverBenchmark.class, length + ".txt").isPresent()) {
            generateSourceData();
        }
    }

    void generateSourceData() throws Exception {
        File sourceFile = Paths.get(SolverBenchmark.class.getResource(".").toURI()).resolve(length + ".txt").toFile();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(sourceFile))) {
            String benchmarkData = ThreadLocalRandom.current()
                                                    .ints(length, 0, NUMBER_ENGLISH_LETTERS)
                                                    .mapToObj(i -> (char) ('a' + i))
                                                    .map(String::valueOf)
                                                    .collect(Collectors.joining());
            writer.write(benchmarkData);
        }
    }

    @Benchmark
    public int naiveSolver() throws Exception {
        Solver solver = new NaiveSolver();
        return solver.analyze(getTestFile(SolverBenchmark.class, length + ".txt"));
    }

    @Benchmark
    public int bitwiseSolver() throws Exception {
        Solver solver = new BitwiseSolver();
        return solver.analyze(getTestFile(SolverBenchmark.class, length + ".txt"));
    }

    @Test
    void benchmark() throws Exception {
        Options options = new OptionsBuilder().include(SolverBenchmark.class.getName() + ".*")
                                              .mode(Mode.AverageTime)
                                              .timeUnit(TimeUnit.SECONDS)
                                              .warmupTime(TimeValue.seconds(1))
                                              .warmupIterations(2)
                                              .measurementTime(TimeValue.seconds(1))
                                              .measurementIterations(2)
                                              .threads(1)
                                              .forks(1)
                                              .shouldFailOnError(true)
                                              .shouldDoGC(true)
                                              .build();
        new Runner(options).run();
    }
}
