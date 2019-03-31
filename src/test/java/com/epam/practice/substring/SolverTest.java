package com.epam.practice.substring;

import com.epam.practice.AbstractTestWithResources;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SolverTest extends AbstractTestWithResources {

    private final Supplier<Solver> solverFactory;
    private Solver solver;

    SolverTest(Supplier<Solver> solverFactory) {
        this.solverFactory = solverFactory;
    }

    @BeforeEach
    void setUp() {
        solver = solverFactory.get();
    }

    @Test
    void emptyString() throws Exception {
        File source = getTestFile(SolverTest.class, "emptyString.txt");
        assertEquals(0, solver.analyze(source));
    }

    @Test
    void oneSymbolString() throws Exception {
        File source = getTestFile(SolverTest.class, "oneSymbolString.txt");
        assertEquals(1, solver.analyze(source));
    }

    @Test
    void oneRepeatingSymbolString() throws Exception {
        File source = getTestFile(SolverTest.class, "oneRepeatingSymbolString.txt");
        assertEquals(1, solver.analyze(source));
    }

    @Test
    void allDifferentSymbols() throws Exception {
        File source = getTestFile(SolverTest.class, "allDifferentSymbols.txt");
        assertEquals(7, solver.analyze(source));
    }

    @Test
    void englishAlphabet() throws Exception {
        File source = getTestFile(SolverTest.class, "englishAlphabet.txt");
        assertEquals(26, solver.analyze(source));
    }

    @Test
    void overflowEnglishAlphabet() throws Exception {
        File source = getTestFile(SolverTest.class, "overflowEnglishAlphabet.txt");
        assertEquals(26, solver.analyze(source));
    }

    @Test
    void sameLengthIntervals() throws Exception {
        File source = getTestFile(SolverTest.class, "sameLengthIntervals.txt");
        assertEquals(4, solver.analyze(source));
    }
}