package com.github.ckufner.puzzles.sudoku.strategies;

import com.github.ckufner.util.ArrayUtil;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@Tag("UnitTest")
class HiddenSingleStrategyTest {
    @ParameterizedTest
    @ArgumentsSource(value = HiddenSingleForRowTestArgumentsProvider.class)
    void hiddenSingleForRowTest(int[][] puzzle, int row, boolean expectedReturnValue, int[][] expectedPuzzle) {
        boolean actual = HiddenSingleStrategy.hiddenSingleForRow(puzzle, row);

        assertEquals(expectedReturnValue, actual);

        assertNotNull(puzzle);
        assertNotSame(puzzle, expectedPuzzle);
        assertEquals(expectedPuzzle.length, puzzle.length);

        for (int i = 0; i < expectedPuzzle.length; i++) {
            assertArrayEquals(expectedPuzzle[i], puzzle[i]);
        }
    }

    private static class HiddenSingleForRowTestArgumentsProvider implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) {
            int[][] p1 = new int[][]{
                    {0, 0, 0, 0, 0, 0, 5, 0, 0},
                    {1, 6, 0, 9, 0, 0, 0, 0, 0},
                    {0, 0, 9, 0, 6, 4, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 4},
                    {4, 0, 0, 0, 2, 0, 1, 0, 0},
                    {0, 0, 0, 3, 0, 0, 0, 5, 0},
                    {0, 0, 2, 0, 8, 9, 0, 0, 0},
                    {0, 1, 0, 2, 5, 0, 0, 3, 0},
                    {7, 0, 0, 1, 0, 0, 0, 0, 9}
            };

            int[][] e1 = ArrayUtil.copy2dArray(p1);
            e1[0][7] = 9;

            int[][] p2 = ArrayUtil.copy2dArray(p1);
            int[][] e2 = ArrayUtil.copy2dArray(p2);

            int[][] p3 = ArrayUtil.copy2dArray(p2);
            int[][] e3 = ArrayUtil.copy2dArray(p3);

            int[][] p4 = ArrayUtil.copy2dArray(p3);
            int[][] e4 = ArrayUtil.copy2dArray(p4);

            int[][] p5 = ArrayUtil.copy2dArray(p4);
            int[][] e5 = ArrayUtil.copy2dArray(p5);

            int[][] p6 = ArrayUtil.copy2dArray(p5);
            int[][] e6 = ArrayUtil.copy2dArray(p6);
            e6[5][4] = 4;

            int[][] p7 = ArrayUtil.copy2dArray(p6);
            int[][] e7 = ArrayUtil.copy2dArray(p7);

            int[][] p8 = ArrayUtil.copy2dArray(p7);
            int[][] e8 = ArrayUtil.copy2dArray(p8);
            e8[7][0] = 9;

            int[][] p9 = ArrayUtil.copy2dArray(p8);
            int[][] e9 = ArrayUtil.copy2dArray(p9);

            return Stream.of(
                    Arguments.of(p1, 0, true, e1),
                    Arguments.of(p2, 1, false, e2),
                    Arguments.of(p3, 2, false, e3),
                    Arguments.of(p4, 3, false, e4),
                    Arguments.of(p5, 4, false, e5),
                    Arguments.of(p6, 5, true, e6),
                    Arguments.of(p7, 6, false, e7),
                    Arguments.of(p8, 7, true, e8),
                    Arguments.of(p9, 8, false, e9)
            );
        }
    }

    @ParameterizedTest
    @ArgumentsSource(value = HiddenSingleForColumnTestArgumentsProvider.class)
    void hiddenSingleForColumnTest(int[][] puzzle, int column, boolean expectedReturnValue, int[][] expectedPuzzle) {
        boolean actual = HiddenSingleStrategy.hiddenSingleForColumn(puzzle, column);

        assertEquals(expectedReturnValue, actual);

        assertNotNull(puzzle);
        assertNotSame(puzzle, expectedPuzzle);
        assertEquals(expectedPuzzle.length, puzzle.length);

        for (int i = 0; i < expectedPuzzle.length; i++) {
            assertArrayEquals(expectedPuzzle[i], puzzle[i]);
        }
    }

    private static class HiddenSingleForColumnTestArgumentsProvider implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) {
            int[][] p1 = new int[][]{
                    {0, 0, 0, 0, 0, 0, 5, 0, 0},
                    {1, 6, 0, 9, 0, 0, 0, 0, 0},
                    {0, 0, 9, 0, 6, 4, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 4},
                    {4, 0, 0, 0, 2, 0, 1, 0, 0},
                    {0, 0, 0, 3, 0, 0, 0, 5, 0},
                    {0, 0, 2, 0, 8, 9, 0, 0, 0},
                    {0, 1, 0, 2, 5, 0, 0, 3, 0},
                    {7, 0, 0, 1, 0, 0, 0, 0, 9}
            };

            int[][] e1 = ArrayUtil.copy2dArray(p1);

            int[][] p2 = ArrayUtil.copy2dArray(p1);
            int[][] e2 = ArrayUtil.copy2dArray(p2);

            int[][] p3 = ArrayUtil.copy2dArray(p2);
            int[][] e3 = ArrayUtil.copy2dArray(p3);

            int[][] p4 = ArrayUtil.copy2dArray(p3);
            int[][] e4 = ArrayUtil.copy2dArray(p4);
            e4[6][3] = 4;

            int[][] p5 = ArrayUtil.copy2dArray(p4);
            int[][] e5 = ArrayUtil.copy2dArray(p5);

            int[][] p6 = ArrayUtil.copy2dArray(p5);
            int[][] e6 = ArrayUtil.copy2dArray(p6);

            int[][] p7 = ArrayUtil.copy2dArray(p6);
            int[][] e7 = ArrayUtil.copy2dArray(p7);

            int[][] p8 = ArrayUtil.copy2dArray(p7);
            int[][] e8 = ArrayUtil.copy2dArray(p8);

            int[][] p9 = ArrayUtil.copy2dArray(p8);
            int[][] e9 = ArrayUtil.copy2dArray(p9);
            e9[6][8] = 5;

            return Stream.of(
                    Arguments.of(p1, 0, false, e1),
                    Arguments.of(p2, 1, false, e2),
                    Arguments.of(p3, 2, false, e3),
                    Arguments.of(p4, 3, true, e4),
                    Arguments.of(p5, 4, false, e5),
                    Arguments.of(p6, 5, false, e6),
                    Arguments.of(p7, 6, false, e7),
                    Arguments.of(p8, 7, false, e8),
                    Arguments.of(p9, 8, true, e9)
            );
        }
    }

    @ParameterizedTest
    @ArgumentsSource(value = HiddenSingleForSectorTestArgumentsProvider.class)
    void hiddenSingleForSectorTest(int[][] puzzle, int sector, boolean expectedReturnValue, int[][] expectedPuzzle) {
        boolean actual = HiddenSingleStrategy.hiddenSingleForSector(puzzle, sector);

        assertEquals(expectedReturnValue, actual);

        assertNotNull(puzzle);
        assertNotSame(puzzle, expectedPuzzle);
        assertEquals(expectedPuzzle.length, puzzle.length);

        for (int i = 0; i < expectedPuzzle.length; i++) {
            assertArrayEquals(expectedPuzzle[i], puzzle[i]);
        }
    }

    private static class HiddenSingleForSectorTestArgumentsProvider implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) {
            int[][] p1 = new int[][]{
                    {0, 0, 0, 0, 0, 0, 5, 0, 0},
                    {1, 6, 0, 9, 0, 0, 0, 0, 0},
                    {0, 0, 9, 0, 6, 4, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 4},
                    {4, 0, 0, 0, 2, 0, 1, 0, 0},
                    {0, 0, 0, 3, 0, 0, 0, 5, 0},
                    {0, 0, 2, 0, 8, 9, 0, 0, 0},
                    {0, 1, 0, 2, 5, 0, 0, 3, 0},
                    {7, 0, 0, 1, 0, 0, 0, 0, 9}
            };

            int[][] e1 = ArrayUtil.copy2dArray(p1);

            int[][] p2 = ArrayUtil.copy2dArray(p1);
            int[][] e2 = ArrayUtil.copy2dArray(p2);

            int[][] p3 = ArrayUtil.copy2dArray(p2);
            int[][] e3 = ArrayUtil.copy2dArray(p3);
            e3[0][7] = 9;

            int[][] p4 = ArrayUtil.copy2dArray(p3);
            int[][] e4 = ArrayUtil.copy2dArray(p4);

            int[][] p5 = ArrayUtil.copy2dArray(p4);
            int[][] e5 = ArrayUtil.copy2dArray(p5);
            e5[5][4] = 4;

            int[][] p6 = ArrayUtil.copy2dArray(p5);
            int[][] e6 = ArrayUtil.copy2dArray(p6);

            int[][] p7 = ArrayUtil.copy2dArray(p6);
            int[][] e7 = ArrayUtil.copy2dArray(p7);
            e7[7][0] = 9;

            int[][] p8 = ArrayUtil.copy2dArray(p7);
            int[][] e8 = ArrayUtil.copy2dArray(p8);

            int[][] p9 = ArrayUtil.copy2dArray(p8);
            int[][] e9 = ArrayUtil.copy2dArray(p9);
            e9[6][8] = 5;

            return Stream.of(
                    Arguments.of(p1, 0, false, e1),
                    Arguments.of(p2, 1, false, e2),
                    Arguments.of(p3, 2, true, e3),
                    Arguments.of(p4, 3, false, e4),
                    Arguments.of(p5, 4, true, e5),
                    Arguments.of(p6, 5, false, e6),
                    Arguments.of(p7, 6, true, e7),
                    Arguments.of(p8, 7, false, e8),
                    Arguments.of(p9, 8, true, e9)
            );
        }
    }
}