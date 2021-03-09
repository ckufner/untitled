package com.github.ckufner.puzzles.sudoku;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@Tag("UnitTest")
class SudokuSolverTest {
    @ParameterizedTest
    @ArgumentsSource(value = SolveTestArgumentsProvider.class)
    void solveTest(int[][] unsolved, int[][] expected) {
        SudokuSolver sudokuSolver = SudokuSolver.of(unsolved);
        List<int[][]> solvedPuzzles = sudokuSolver.solve(true);

        assertNotNull(solvedPuzzles);
        assertTrue(solvedPuzzles.size() > 0);
        assertEquals(1, solvedPuzzles.size());

        int[][] solvedPuzzle = solvedPuzzles.get(0);

        System.out.println("backtracks: " + sudokuSolver.getBacktracks());
        SudokuUtil.printPuzzle(solvedPuzzle);

        assertEquals(9, solvedPuzzle.length);
        for (int row = 0; row < 9; row++) {
            assertArrayEquals(expected[row], solvedPuzzle[row]);
        }

        assertTrue(SudokuUtil.isPuzzleValid(solvedPuzzle, true));
    }

    private static class SolveTestArgumentsProvider implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) {
            final var puzzleList = SudokuTestFixtureProvider.getPuzzles();

            final var arguments = new Arguments[puzzleList.size()];
            for (int i = 0; i < puzzleList.size(); i++) {
                final var puzzleData = puzzleList.get(i);

                arguments[i] = Arguments.of(puzzleData.getUnsolved(), puzzleData.getSolved());
            }

            return Stream.of(arguments);
        }
    }
}
