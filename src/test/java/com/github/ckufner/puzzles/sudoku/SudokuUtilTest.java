package com.github.ckufner.puzzles.sudoku;

import com.github.ckufner.IntTuple;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@Tag("UnitTest")
class SudokuUtilTest {
    @ParameterizedTest
    @ArgumentsSource(value = GetSectorValueForCellTestArgumentsProvider.class)
    void getSectorValueForCellTest(int row, int column, int expected) {
        int actual = SudokuUtil.getSectorValueForCell(row, column);
        assertEquals(expected, actual);
    }

    private static class GetSectorValueForCellTestArgumentsProvider implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) {
            return Stream.of(
                    Arguments.of(0, 0, 0),
                    Arguments.of(0, 1, 0),
                    Arguments.of(0, 2, 0),
                    Arguments.of(1, 0, 0),
                    Arguments.of(1, 1, 0),
                    Arguments.of(1, 2, 0),
                    Arguments.of(2, 0, 0),
                    Arguments.of(2, 1, 0),
                    Arguments.of(2, 2, 0),

                    Arguments.of(0, 3, 1),
                    Arguments.of(0, 4, 1),
                    Arguments.of(0, 5, 1),
                    Arguments.of(1, 3, 1),
                    Arguments.of(1, 4, 1),
                    Arguments.of(1, 5, 1),
                    Arguments.of(2, 3, 1),
                    Arguments.of(2, 4, 1),
                    Arguments.of(2, 5, 1),

                    Arguments.of(0, 6, 2),
                    Arguments.of(0, 7, 2),
                    Arguments.of(0, 8, 2),
                    Arguments.of(1, 6, 2),
                    Arguments.of(1, 7, 2),
                    Arguments.of(1, 8, 2),
                    Arguments.of(2, 6, 2),
                    Arguments.of(2, 7, 2),
                    Arguments.of(2, 8, 2),

                    Arguments.of(3, 0, 3),
                    Arguments.of(3, 1, 3),
                    Arguments.of(3, 2, 3),
                    Arguments.of(4, 0, 3),
                    Arguments.of(4, 1, 3),
                    Arguments.of(4, 2, 3),
                    Arguments.of(5, 0, 3),
                    Arguments.of(5, 1, 3),
                    Arguments.of(5, 2, 3),

                    Arguments.of(3, 3, 4),
                    Arguments.of(3, 4, 4),
                    Arguments.of(3, 5, 4),
                    Arguments.of(4, 3, 4),
                    Arguments.of(4, 4, 4),
                    Arguments.of(4, 5, 4),
                    Arguments.of(5, 3, 4),
                    Arguments.of(5, 4, 4),
                    Arguments.of(5, 5, 4),

                    Arguments.of(3, 6, 5),
                    Arguments.of(3, 7, 5),
                    Arguments.of(3, 8, 5),
                    Arguments.of(4, 6, 5),
                    Arguments.of(4, 7, 5),
                    Arguments.of(4, 8, 5),
                    Arguments.of(5, 6, 5),
                    Arguments.of(5, 7, 5),
                    Arguments.of(5, 8, 5),

                    Arguments.of(6, 0, 6),
                    Arguments.of(6, 1, 6),
                    Arguments.of(6, 2, 6),
                    Arguments.of(7, 0, 6),
                    Arguments.of(7, 1, 6),
                    Arguments.of(7, 2, 6),
                    Arguments.of(8, 0, 6),
                    Arguments.of(8, 1, 6),
                    Arguments.of(8, 2, 6),

                    Arguments.of(6, 3, 7),
                    Arguments.of(6, 4, 7),
                    Arguments.of(6, 5, 7),
                    Arguments.of(7, 3, 7),
                    Arguments.of(7, 4, 7),
                    Arguments.of(7, 5, 7),
                    Arguments.of(8, 3, 7),
                    Arguments.of(8, 4, 7),
                    Arguments.of(8, 5, 7),

                    Arguments.of(6, 6, 8),
                    Arguments.of(6, 7, 8),
                    Arguments.of(6, 8, 8),
                    Arguments.of(7, 6, 8),
                    Arguments.of(7, 7, 8),
                    Arguments.of(7, 8, 8),
                    Arguments.of(8, 6, 8),
                    Arguments.of(8, 7, 8),
                    Arguments.of(8, 8, 8)
            );
        }
    }

    @ParameterizedTest
    @ArgumentsSource(value = GetSetValuesOfRowTestArgumentsProvider.class)
    void getSetValuesOfRowTest(int[][] puzzle, int row, int[] expected) {
        int[] actual = SudokuUtil.getSetValuesOfRow(puzzle, row);

        assertNotNull(actual);
        assertNotSame(expected, actual);
        assertArrayEquals(expected, actual);
    }

    private static class GetSetValuesOfRowTestArgumentsProvider implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) {
            int[][] puzzle = new int[][]{
                    {0, 0, 0, 0, 3, 0, 4, 1, 0},
                    {0, 0, 0, 0, 0, 2, 0, 5, 8},
                    {5, 0, 0, 0, 0, 8, 0, 0, 0},
                    {2, 0, 5, 0, 4, 0, 9, 0, 0},
                    {3, 1, 0, 6, 0, 0, 0, 0, 0},
                    {0, 9, 7, 0, 0, 0, 0, 3, 0},
                    {1, 0, 0, 0, 0, 0, 8, 0, 0},
                    {0, 2, 0, 9, 0, 0, 5, 0, 0},
                    {8, 3, 0, 0, 0, 0, 0, 0, 2}
            };

            return Stream.of(
                    Arguments.of(puzzle, 0, new int[]{3, 4, 1}),
                    Arguments.of(puzzle, 1, new int[]{2, 5, 8}),
                    Arguments.of(puzzle, 2, new int[]{5, 8}),
                    Arguments.of(puzzle, 3, new int[]{2, 5, 4, 9}),
                    Arguments.of(puzzle, 4, new int[]{3, 1, 6}),
                    Arguments.of(puzzle, 5, new int[]{9, 7, 3}),
                    Arguments.of(puzzle, 6, new int[]{1, 8}),
                    Arguments.of(puzzle, 7, new int[]{2, 9, 5}),
                    Arguments.of(puzzle, 8, new int[]{8, 3, 2})
            );
        }
    }

    @ParameterizedTest
    @ArgumentsSource(value = GetSetValuesOfColumnTestArgumentsProvider.class)
    void getSetValuesOfColumnTest(int[][] puzzle, int column, int[] expected) {
        int[] actual = SudokuUtil.getSetValuesOfColumn(puzzle, column);

        assertNotNull(actual);
        assertNotSame(expected, actual);
        assertArrayEquals(expected, actual);
    }

    private static class GetSetValuesOfColumnTestArgumentsProvider implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) {
            int[][] puzzle = new int[][]{
                    {0, 0, 0, 0, 3, 0, 4, 1, 0},
                    {0, 0, 0, 0, 0, 2, 0, 5, 8},
                    {5, 0, 0, 0, 0, 8, 0, 0, 0},
                    {2, 0, 5, 0, 4, 0, 9, 0, 0},
                    {3, 1, 0, 6, 0, 0, 0, 0, 0},
                    {0, 9, 7, 0, 0, 0, 0, 3, 0},
                    {1, 0, 0, 0, 0, 0, 8, 0, 0},
                    {0, 2, 0, 9, 0, 0, 5, 0, 0},
                    {8, 3, 0, 0, 0, 0, 0, 0, 2}
            };

            return Stream.of(
                    Arguments.of(puzzle, 0, new int[]{5, 2, 3, 1, 8}),
                    Arguments.of(puzzle, 1, new int[]{1, 9, 2, 3}),
                    Arguments.of(puzzle, 2, new int[]{5, 7}),
                    Arguments.of(puzzle, 3, new int[]{6, 9}),
                    Arguments.of(puzzle, 4, new int[]{3, 4}),
                    Arguments.of(puzzle, 5, new int[]{2, 8}),
                    Arguments.of(puzzle, 6, new int[]{4, 9, 8, 5}),
                    Arguments.of(puzzle, 7, new int[]{1, 5, 3}),
                    Arguments.of(puzzle, 8, new int[]{8, 2})
            );
        }
    }

    @ParameterizedTest
    @ArgumentsSource(value = GetSectorTestArgumentsProvider.class)
    void getSectorTest(int[][] puzzle, int sector, int[] expected) {
        int[] actual = SudokuUtil.getSector(puzzle, sector);

        assertNotNull(actual);
        assertEquals(9, actual.length);
        assertNotSame(expected, actual);
        assertArrayEquals(expected, actual);
    }

    private static class GetSectorTestArgumentsProvider implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) {
            int[][] puzzle = new int[][]{
                    {0, 0, 0, 0, 3, 0, 4, 1, 0},
                    {0, 0, 0, 0, 0, 2, 0, 5, 8},
                    {5, 0, 0, 0, 0, 8, 0, 0, 0},
                    {2, 0, 5, 0, 4, 0, 9, 0, 0},
                    {3, 1, 0, 6, 0, 0, 0, 0, 0},
                    {0, 9, 7, 0, 0, 0, 0, 3, 0},
                    {1, 0, 0, 0, 0, 0, 8, 0, 0},
                    {0, 2, 0, 9, 0, 0, 5, 0, 0},
                    {8, 3, 0, 0, 0, 0, 0, 0, 2}
            };

            return Stream.of(
                    Arguments.of(puzzle, 0, new int[]{0, 0, 0, 0, 0, 0, 5, 0, 0}),
                    Arguments.of(puzzle, 1, new int[]{0, 3, 0, 0, 0, 2, 0, 0, 8}),
                    Arguments.of(puzzle, 2, new int[]{4, 1, 0, 0, 5, 8, 0, 0, 0}),
                    Arguments.of(puzzle, 3, new int[]{2, 0, 5, 3, 1, 0, 0, 9, 7}),
                    Arguments.of(puzzle, 4, new int[]{0, 4, 0, 6, 0, 0, 0, 0, 0}),
                    Arguments.of(puzzle, 5, new int[]{9, 0, 0, 0, 0, 0, 0, 3, 0}),
                    Arguments.of(puzzle, 6, new int[]{1, 0, 0, 0, 2, 0, 8, 3, 0}),
                    Arguments.of(puzzle, 7, new int[]{0, 0, 0, 9, 0, 0, 0, 0, 0}),
                    Arguments.of(puzzle, 8, new int[]{8, 0, 0, 5, 0, 0, 0, 0, 2})
            );
        }
    }

    @ParameterizedTest
    @ArgumentsSource(value = GetSetValuesOfSectorForCellTestArgumentsProvider.class)
    void getSetValuesOfSectorForCellTest(int[][] puzzle, int row, int column, int[] expected) {
        int[] actual = SudokuUtil.getSetValuesOfSectorForCell(puzzle, row, column);

        assertNotNull(actual);
        assertNotSame(expected, actual);
        assertArrayEquals(expected, actual);
    }

    private static class GetSetValuesOfSectorForCellTestArgumentsProvider implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) {
            int[][] puzzle = new int[][]{
                    {0, 0, 0, 0, 3, 0, 4, 1, 0},
                    {0, 0, 0, 0, 0, 2, 0, 5, 8},
                    {5, 0, 0, 0, 0, 8, 0, 0, 0},
                    {2, 0, 5, 0, 4, 0, 9, 0, 0},
                    {3, 1, 0, 6, 0, 0, 0, 0, 0},
                    {0, 9, 7, 0, 0, 0, 0, 3, 0},
                    {1, 0, 0, 0, 0, 0, 8, 0, 0},
                    {0, 2, 0, 9, 0, 0, 5, 0, 0},
                    {8, 3, 0, 0, 0, 0, 0, 0, 2}
            };

            return Stream.of(
                    Arguments.of(puzzle, 0, 0, new int[]{5}),
                    Arguments.of(puzzle, 0, 1, new int[]{5}),
                    Arguments.of(puzzle, 0, 2, new int[]{5}),
                    Arguments.of(puzzle, 1, 0, new int[]{5}),
                    Arguments.of(puzzle, 1, 1, new int[]{5}),
                    Arguments.of(puzzle, 1, 2, new int[]{5}),
                    Arguments.of(puzzle, 2, 0, new int[]{5}),
                    Arguments.of(puzzle, 2, 1, new int[]{5}),
                    Arguments.of(puzzle, 2, 2, new int[]{5}),
                    Arguments.of(puzzle, 0, 3, new int[]{3, 2, 8}),
                    Arguments.of(puzzle, 0, 4, new int[]{3, 2, 8}),
                    Arguments.of(puzzle, 0, 5, new int[]{3, 2, 8}),
                    Arguments.of(puzzle, 1, 3, new int[]{3, 2, 8}),
                    Arguments.of(puzzle, 1, 4, new int[]{3, 2, 8}),
                    Arguments.of(puzzle, 1, 5, new int[]{3, 2, 8}),
                    Arguments.of(puzzle, 2, 3, new int[]{3, 2, 8}),
                    Arguments.of(puzzle, 2, 4, new int[]{3, 2, 8}),
                    Arguments.of(puzzle, 2, 5, new int[]{3, 2, 8}),
                    Arguments.of(puzzle, 0, 6, new int[]{4, 1, 5, 8}),
                    Arguments.of(puzzle, 0, 7, new int[]{4, 1, 5, 8}),
                    Arguments.of(puzzle, 0, 8, new int[]{4, 1, 5, 8}),
                    Arguments.of(puzzle, 1, 6, new int[]{4, 1, 5, 8}),
                    Arguments.of(puzzle, 1, 7, new int[]{4, 1, 5, 8}),
                    Arguments.of(puzzle, 1, 8, new int[]{4, 1, 5, 8}),
                    Arguments.of(puzzle, 2, 6, new int[]{4, 1, 5, 8}),
                    Arguments.of(puzzle, 2, 7, new int[]{4, 1, 5, 8}),
                    Arguments.of(puzzle, 2, 8, new int[]{4, 1, 5, 8}),
                    Arguments.of(puzzle, 3, 0, new int[]{2, 5, 3, 1, 9, 7}),
                    Arguments.of(puzzle, 3, 1, new int[]{2, 5, 3, 1, 9, 7}),
                    Arguments.of(puzzle, 3, 2, new int[]{2, 5, 3, 1, 9, 7}),
                    Arguments.of(puzzle, 4, 0, new int[]{2, 5, 3, 1, 9, 7}),
                    Arguments.of(puzzle, 4, 1, new int[]{2, 5, 3, 1, 9, 7}),
                    Arguments.of(puzzle, 4, 2, new int[]{2, 5, 3, 1, 9, 7}),
                    Arguments.of(puzzle, 5, 0, new int[]{2, 5, 3, 1, 9, 7}),
                    Arguments.of(puzzle, 5, 1, new int[]{2, 5, 3, 1, 9, 7}),
                    Arguments.of(puzzle, 5, 2, new int[]{2, 5, 3, 1, 9, 7}),
                    Arguments.of(puzzle, 3, 3, new int[]{4, 6}),
                    Arguments.of(puzzle, 3, 4, new int[]{4, 6}),
                    Arguments.of(puzzle, 3, 5, new int[]{4, 6}),
                    Arguments.of(puzzle, 4, 3, new int[]{4, 6}),
                    Arguments.of(puzzle, 4, 4, new int[]{4, 6}),
                    Arguments.of(puzzle, 4, 5, new int[]{4, 6}),
                    Arguments.of(puzzle, 5, 3, new int[]{4, 6}),
                    Arguments.of(puzzle, 5, 4, new int[]{4, 6}),
                    Arguments.of(puzzle, 5, 5, new int[]{4, 6}),
                    Arguments.of(puzzle, 3, 6, new int[]{9, 3}),
                    Arguments.of(puzzle, 3, 7, new int[]{9, 3}),
                    Arguments.of(puzzle, 3, 8, new int[]{9, 3}),
                    Arguments.of(puzzle, 4, 6, new int[]{9, 3}),
                    Arguments.of(puzzle, 4, 7, new int[]{9, 3}),
                    Arguments.of(puzzle, 4, 8, new int[]{9, 3}),
                    Arguments.of(puzzle, 5, 6, new int[]{9, 3}),
                    Arguments.of(puzzle, 5, 7, new int[]{9, 3}),
                    Arguments.of(puzzle, 5, 8, new int[]{9, 3}),
                    Arguments.of(puzzle, 6, 0, new int[]{1, 2, 8, 3}),
                    Arguments.of(puzzle, 6, 1, new int[]{1, 2, 8, 3}),
                    Arguments.of(puzzle, 6, 2, new int[]{1, 2, 8, 3}),
                    Arguments.of(puzzle, 7, 0, new int[]{1, 2, 8, 3}),
                    Arguments.of(puzzle, 7, 1, new int[]{1, 2, 8, 3}),
                    Arguments.of(puzzle, 7, 2, new int[]{1, 2, 8, 3}),
                    Arguments.of(puzzle, 8, 0, new int[]{1, 2, 8, 3}),
                    Arguments.of(puzzle, 8, 1, new int[]{1, 2, 8, 3}),
                    Arguments.of(puzzle, 8, 2, new int[]{1, 2, 8, 3}),
                    Arguments.of(puzzle, 6, 3, new int[]{9}),
                    Arguments.of(puzzle, 6, 4, new int[]{9}),
                    Arguments.of(puzzle, 6, 5, new int[]{9}),
                    Arguments.of(puzzle, 7, 3, new int[]{9}),
                    Arguments.of(puzzle, 7, 4, new int[]{9}),
                    Arguments.of(puzzle, 7, 5, new int[]{9}),
                    Arguments.of(puzzle, 8, 3, new int[]{9}),
                    Arguments.of(puzzle, 8, 4, new int[]{9}),
                    Arguments.of(puzzle, 8, 5, new int[]{9}),
                    Arguments.of(puzzle, 6, 6, new int[]{8, 5, 2}),
                    Arguments.of(puzzle, 6, 7, new int[]{8, 5, 2}),
                    Arguments.of(puzzle, 6, 8, new int[]{8, 5, 2}),
                    Arguments.of(puzzle, 7, 6, new int[]{8, 5, 2}),
                    Arguments.of(puzzle, 7, 7, new int[]{8, 5, 2}),
                    Arguments.of(puzzle, 7, 8, new int[]{8, 5, 2}),
                    Arguments.of(puzzle, 8, 6, new int[]{8, 5, 2}),
                    Arguments.of(puzzle, 8, 7, new int[]{8, 5, 2}),
                    Arguments.of(puzzle, 8, 8, new int[]{8, 5, 2})
            );
        }
    }

    @ParameterizedTest
    @ArgumentsSource(value = GetCandidateValuesOfCellTestArgumentsProvider.class)
    void getCandidateValuesOfCellTest(int[][] puzzle, int row, int column, int[] expected) {
        int[] actual = SudokuUtil.getCandidateValuesOfCell(puzzle, row, column);

        assertNotNull(actual);
        assertNotSame(expected, actual);
        assertArrayEquals(expected, actual);
    }

    private static class GetCandidateValuesOfCellTestArgumentsProvider implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) {
            int[][] puzzle = new int[][]{
                    {0, 3, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 1, 9, 5, 0, 0, 0},
                    {0, 0, 8, 0, 0, 0, 0, 6, 0},
                    {8, 0, 0, 0, 6, 0, 0, 0, 0},
                    {4, 0, 0, 8, 0, 0, 0, 0, 1},
                    {0, 0, 0, 0, 2, 0, 0, 0, 0},
                    {0, 6, 0, 0, 0, 0, 2, 8, 0},
                    {0, 0, 0, 4, 1, 9, 0, 0, 5},
                    {0, 0, 0, 0, 0, 0, 0, 7, 0}
            };

            return Stream.of(
                    Arguments.of(puzzle, 0, 0, new int[]{1, 2, 5, 6, 7, 9}),
                    Arguments.of(puzzle, 0, 1, new int[]{}),
                    Arguments.of(puzzle, 0, 2, new int[]{1, 2, 4, 5, 6, 7, 9}),
                    Arguments.of(puzzle, 1, 0, new int[]{2, 6, 7}),
                    Arguments.of(puzzle, 1, 1, new int[]{2, 4, 7}),
                    Arguments.of(puzzle, 1, 2, new int[]{2, 4, 6, 7}),
                    Arguments.of(puzzle, 2, 0, new int[]{1, 2, 5, 7, 9}),
                    Arguments.of(puzzle, 2, 1, new int[]{1, 2, 4, 5, 7, 9}),
                    Arguments.of(puzzle, 2, 2, new int[]{}),
                    Arguments.of(puzzle, 0, 3, new int[]{2, 6, 7}),
                    Arguments.of(puzzle, 0, 4, new int[]{4, 7, 8}),
                    Arguments.of(puzzle, 0, 5, new int[]{2, 4, 6, 7, 8}),
                    Arguments.of(puzzle, 1, 3, new int[]{}),
                    Arguments.of(puzzle, 1, 4, new int[]{}),
                    Arguments.of(puzzle, 1, 5, new int[]{}),
                    Arguments.of(puzzle, 2, 3, new int[]{2, 3, 7}),
                    Arguments.of(puzzle, 2, 4, new int[]{3, 4, 7}),
                    Arguments.of(puzzle, 2, 5, new int[]{2, 3, 4, 7}),
                    Arguments.of(puzzle, 0, 6, new int[]{1, 4, 5, 7, 8, 9}),
                    Arguments.of(puzzle, 0, 7, new int[]{1, 2, 4, 5, 9}),
                    Arguments.of(puzzle, 0, 8, new int[]{2, 4, 7, 8, 9}),
                    Arguments.of(puzzle, 1, 6, new int[]{3, 4, 7, 8}),
                    Arguments.of(puzzle, 1, 7, new int[]{2, 3, 4}),
                    Arguments.of(puzzle, 1, 8, new int[]{2, 3, 4, 7, 8}),
                    Arguments.of(puzzle, 2, 6, new int[]{1, 3, 4, 5, 7, 9}),
                    Arguments.of(puzzle, 2, 7, new int[]{}),
                    Arguments.of(puzzle, 2, 8, new int[]{2, 3, 4, 7, 9}),
                    Arguments.of(puzzle, 3, 0, new int[]{}),
                    Arguments.of(puzzle, 3, 1, new int[]{1, 2, 5, 7, 9}),
                    Arguments.of(puzzle, 3, 2, new int[]{1, 2, 3, 5, 7, 9}),
                    Arguments.of(puzzle, 4, 0, new int[]{}),
                    Arguments.of(puzzle, 4, 1, new int[]{2, 5, 7, 9}),
                    Arguments.of(puzzle, 4, 2, new int[]{2, 3, 5, 6, 7, 9}),
                    Arguments.of(puzzle, 5, 0, new int[]{1, 3, 5, 6, 7, 9}),
                    Arguments.of(puzzle, 5, 1, new int[]{1, 5, 7, 9}),
                    Arguments.of(puzzle, 5, 2, new int[]{1, 3, 5, 6, 7, 9}),
                    Arguments.of(puzzle, 3, 3, new int[]{3, 5, 7, 9}),
                    Arguments.of(puzzle, 3, 4, new int[]{}),
                    Arguments.of(puzzle, 3, 5, new int[]{1, 3, 4, 7}),
                    Arguments.of(puzzle, 4, 3, new int[]{}),
                    Arguments.of(puzzle, 4, 4, new int[]{3, 5, 7}),
                    Arguments.of(puzzle, 4, 5, new int[]{3, 7}),
                    Arguments.of(puzzle, 5, 3, new int[]{3, 5, 7, 9}),
                    Arguments.of(puzzle, 5, 4, new int[]{}),
                    Arguments.of(puzzle, 5, 5, new int[]{1, 3, 4, 7}),
                    Arguments.of(puzzle, 3, 6, new int[]{3, 4, 5, 7, 9}),
                    Arguments.of(puzzle, 3, 7, new int[]{2, 3, 4, 5, 9}),
                    Arguments.of(puzzle, 3, 8, new int[]{2, 3, 4, 7, 9}),
                    Arguments.of(puzzle, 4, 6, new int[]{3, 5, 6, 7, 9}),
                    Arguments.of(puzzle, 4, 7, new int[]{2, 3, 5, 9}),
                    Arguments.of(puzzle, 4, 8, new int[]{}),
                    Arguments.of(puzzle, 5, 6, new int[]{3, 4, 5, 6, 7, 8, 9}),
                    Arguments.of(puzzle, 5, 7, new int[]{3, 4, 5, 9}),
                    Arguments.of(puzzle, 5, 8, new int[]{3, 4, 6, 7, 8, 9}),
                    Arguments.of(puzzle, 6, 0, new int[]{1, 3, 5, 7, 9}),
                    Arguments.of(puzzle, 6, 1, new int[]{}),
                    Arguments.of(puzzle, 6, 2, new int[]{1, 3, 4, 5, 7, 9}),
                    Arguments.of(puzzle, 7, 0, new int[]{2, 3, 7}),
                    Arguments.of(puzzle, 7, 1, new int[]{2, 7, 8}),
                    Arguments.of(puzzle, 7, 2, new int[]{2, 3, 7}),
                    Arguments.of(puzzle, 8, 0, new int[]{1, 2, 3, 5, 9}),
                    Arguments.of(puzzle, 8, 1, new int[]{1, 2, 4, 5, 8, 9}),
                    Arguments.of(puzzle, 8, 2, new int[]{1, 2, 3, 4, 5, 9}),
                    Arguments.of(puzzle, 6, 3, new int[]{3, 5, 7}),
                    Arguments.of(puzzle, 6, 4, new int[]{3, 5, 7}),
                    Arguments.of(puzzle, 6, 5, new int[]{3, 7}),
                    Arguments.of(puzzle, 7, 3, new int[]{}),
                    Arguments.of(puzzle, 7, 4, new int[]{}),
                    Arguments.of(puzzle, 7, 5, new int[]{}),
                    Arguments.of(puzzle, 8, 3, new int[]{2, 3, 5, 6}),
                    Arguments.of(puzzle, 8, 4, new int[]{3, 5, 8}),
                    Arguments.of(puzzle, 8, 5, new int[]{2, 3, 6, 8}),
                    Arguments.of(puzzle, 6, 6, new int[]{}),
                    Arguments.of(puzzle, 6, 7, new int[]{}),
                    Arguments.of(puzzle, 6, 8, new int[]{3, 4, 9}),
                    Arguments.of(puzzle, 7, 6, new int[]{3, 6}),
                    Arguments.of(puzzle, 7, 7, new int[]{3}),
                    Arguments.of(puzzle, 7, 8, new int[]{}),
                    Arguments.of(puzzle, 8, 6, new int[]{1, 3, 4, 6, 9}),
                    Arguments.of(puzzle, 8, 7, new int[]{}),
                    Arguments.of(puzzle, 8, 8, new int[]{3, 4, 6, 9})
            );
        }
    }

    @ParameterizedTest
    @ArgumentsSource(value = GetGridPositionForSectorAndPositionTestArgumentsProvider.class)
    void getGridPositionForSectorAndPositionTest(int sector, int position, IntTuple expected) {
        IntTuple actual = SudokuUtil.getGridPositionForSectorAndPosition(sector, position);

        assertNotNull(actual);
        assertEquals(expected, actual);
    }

    private static class GetGridPositionForSectorAndPositionTestArgumentsProvider implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) {
            return Stream.of(
                    Arguments.of(0, 0, IntTuple.of(0, 0)),
                    Arguments.of(0, 1, IntTuple.of(0, 1)),
                    Arguments.of(0, 2, IntTuple.of(0, 2)),
                    Arguments.of(0, 3, IntTuple.of(1, 0)),
                    Arguments.of(0, 4, IntTuple.of(1, 1)),
                    Arguments.of(0, 5, IntTuple.of(1, 2)),
                    Arguments.of(0, 6, IntTuple.of(2, 0)),
                    Arguments.of(0, 7, IntTuple.of(2, 1)),
                    Arguments.of(0, 8, IntTuple.of(2, 2)),

                    Arguments.of(1, 0, IntTuple.of(0, 3)),
                    Arguments.of(1, 1, IntTuple.of(0, 4)),
                    Arguments.of(1, 2, IntTuple.of(0, 5)),
                    Arguments.of(1, 3, IntTuple.of(1, 3)),
                    Arguments.of(1, 4, IntTuple.of(1, 4)),
                    Arguments.of(1, 5, IntTuple.of(1, 5)),
                    Arguments.of(1, 6, IntTuple.of(2, 3)),
                    Arguments.of(1, 7, IntTuple.of(2, 4)),
                    Arguments.of(1, 8, IntTuple.of(2, 5)),

                    Arguments.of(2, 0, IntTuple.of(0, 6)),
                    Arguments.of(2, 1, IntTuple.of(0, 7)),
                    Arguments.of(2, 2, IntTuple.of(0, 8)),
                    Arguments.of(2, 3, IntTuple.of(1, 6)),
                    Arguments.of(2, 4, IntTuple.of(1, 7)),
                    Arguments.of(2, 5, IntTuple.of(1, 8)),
                    Arguments.of(2, 6, IntTuple.of(2, 6)),
                    Arguments.of(2, 7, IntTuple.of(2, 7)),
                    Arguments.of(2, 8, IntTuple.of(2, 8)),

                    Arguments.of(3, 0, IntTuple.of(3, 0)),
                    Arguments.of(3, 1, IntTuple.of(3, 1)),
                    Arguments.of(3, 2, IntTuple.of(3, 2)),
                    Arguments.of(3, 3, IntTuple.of(4, 0)),
                    Arguments.of(3, 4, IntTuple.of(4, 1)),
                    Arguments.of(3, 5, IntTuple.of(4, 2)),
                    Arguments.of(3, 6, IntTuple.of(5, 0)),
                    Arguments.of(3, 7, IntTuple.of(5, 1)),
                    Arguments.of(3, 8, IntTuple.of(5, 2)),

                    Arguments.of(4, 0, IntTuple.of(3, 3)),
                    Arguments.of(4, 1, IntTuple.of(3, 4)),
                    Arguments.of(4, 2, IntTuple.of(3, 5)),
                    Arguments.of(4, 3, IntTuple.of(4, 3)),
                    Arguments.of(4, 4, IntTuple.of(4, 4)),
                    Arguments.of(4, 5, IntTuple.of(4, 5)),
                    Arguments.of(4, 6, IntTuple.of(5, 3)),
                    Arguments.of(4, 7, IntTuple.of(5, 4)),
                    Arguments.of(4, 8, IntTuple.of(5, 5)),

                    Arguments.of(5, 0, IntTuple.of(3, 6)),
                    Arguments.of(5, 1, IntTuple.of(3, 7)),
                    Arguments.of(5, 2, IntTuple.of(3, 8)),
                    Arguments.of(5, 3, IntTuple.of(4, 6)),
                    Arguments.of(5, 4, IntTuple.of(4, 7)),
                    Arguments.of(5, 5, IntTuple.of(4, 8)),
                    Arguments.of(5, 6, IntTuple.of(5, 6)),
                    Arguments.of(5, 7, IntTuple.of(5, 7)),
                    Arguments.of(5, 8, IntTuple.of(5, 8)),

                    Arguments.of(6, 0, IntTuple.of(6, 0)),
                    Arguments.of(6, 1, IntTuple.of(6, 1)),
                    Arguments.of(6, 2, IntTuple.of(6, 2)),
                    Arguments.of(6, 3, IntTuple.of(7, 0)),
                    Arguments.of(6, 4, IntTuple.of(7, 1)),
                    Arguments.of(6, 5, IntTuple.of(7, 2)),
                    Arguments.of(6, 6, IntTuple.of(8, 0)),
                    Arguments.of(6, 7, IntTuple.of(8, 1)),
                    Arguments.of(6, 8, IntTuple.of(8, 2)),

                    Arguments.of(7, 0, IntTuple.of(6, 3)),
                    Arguments.of(7, 1, IntTuple.of(6, 4)),
                    Arguments.of(7, 2, IntTuple.of(6, 5)),
                    Arguments.of(7, 3, IntTuple.of(7, 3)),
                    Arguments.of(7, 4, IntTuple.of(7, 4)),
                    Arguments.of(7, 5, IntTuple.of(7, 5)),
                    Arguments.of(7, 6, IntTuple.of(8, 3)),
                    Arguments.of(7, 7, IntTuple.of(8, 4)),
                    Arguments.of(7, 8, IntTuple.of(8, 5)),

                    Arguments.of(8, 0, IntTuple.of(6, 6)),
                    Arguments.of(8, 1, IntTuple.of(6, 7)),
                    Arguments.of(8, 2, IntTuple.of(6, 8)),
                    Arguments.of(8, 3, IntTuple.of(7, 6)),
                    Arguments.of(8, 4, IntTuple.of(7, 7)),
                    Arguments.of(8, 5, IntTuple.of(7, 8)),
                    Arguments.of(8, 6, IntTuple.of(8, 6)),
                    Arguments.of(8, 7, IntTuple.of(8, 7)),
                    Arguments.of(8, 8, IntTuple.of(8, 8))
            );
        }
    }
}
