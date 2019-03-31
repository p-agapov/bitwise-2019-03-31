package com.epam.practice.bio;

import com.epam.practice.AbstractTestWithResources;
import lombok.Value;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static java.util.Collections.singletonList;
import static org.hamcrest.Matchers.hasEntry;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SolverTest extends AbstractTestWithResources {

    private Solver solver;

    @BeforeEach
    void setUp() {
        solver = null;
        throw new UnsupportedOperationException();
    }

    @Test
    void baseTest() throws Exception {
        File source = getTestFile(SolverTest.class, "baseTest.txt");

        Map<Section, List<Integer>> result = solver.analyze(source);

        assertEquals(6, result.size());
        hasEntry(new DummySection("AC"), singletonList(0));
        hasEntry(new DummySection("CC"), Arrays.asList(1, 2, 5));
        hasEntry(new DummySection("CG"), singletonList(3));
        hasEntry(new DummySection("CT"), singletonList(6));
        hasEntry(new DummySection("GC"), Arrays.asList(4, 8));
        hasEntry(new DummySection("TG"), singletonList(7));
    }

    @Value
    private static class DummySection extends Section {
        String section;
    }
}