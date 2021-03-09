package com.github.ckufner.puzzles.sudoku;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@Tag("UnitTest")
public class SudokuParserTest {
    @ParameterizedTest
    @ArgumentsSource(value = ParserTestArgumentsProvider.class)
    void parserTest(String input, int[][] expected) {
        int[][] actual = SudokuParser.parse(input);

        SudokuUtil.printPuzzle(actual);

        assertNotNull(actual);
        assertEquals(expected.length, actual.length);
        for (int i = 0; i < expected.length; i++) {
            assertArrayEquals(expected[i], actual[i]);
        }
    }

    private static class ParserTestArgumentsProvider implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) {
            return Stream.of(
                    Arguments.of(
                            "9..341.7..6.....3..12..6459.3.4..8.....1...9...1..7.6.6257.498..83....1..7.2.3..5",
                            new int[][]{
                                    {9, 0, 0, 3, 4, 1, 0, 7, 0},
                                    {0, 6, 0, 0, 0, 0, 0, 3, 0},
                                    {0, 1, 2, 0, 0, 6, 4, 5, 9},
                                    {0, 3, 0, 4, 0, 0, 8, 0, 0},
                                    {0, 0, 0, 1, 0, 0, 0, 9, 0},
                                    {0, 0, 1, 0, 0, 7, 0, 6, 0},
                                    {6, 2, 5, 7, 0, 4, 9, 8, 0},
                                    {0, 8, 3, 0, 0, 0, 0, 1, 0},
                                    {0, 7, 0, 2, 0, 3, 0, 0, 5}
                            }
                    ),
                    Arguments.of(
                            ".84..7.9.2319..7...7..41...6......8.7.8.1.2.34.......5.6.75.83......8571.5.1..462",
                            new int[][]{
                                    {0, 8, 4, 0, 0, 7, 0, 9, 0},
                                    {2, 3, 1, 9, 0, 0, 7, 0, 0},
                                    {0, 7, 0, 0, 4, 1, 0, 0, 0},
                                    {6, 0, 0, 0, 0, 0, 0, 8, 0},
                                    {7, 0, 8, 0, 1, 0, 2, 0, 3},
                                    {4, 0, 0, 0, 0, 0, 0, 0, 5},
                                    {0, 6, 0, 7, 5, 0, 8, 3, 0},
                                    {0, 0, 0, 0, 0, 8, 5, 7, 1},
                                    {0, 5, 0, 1, 0, 0, 4, 6, 2}
                            }
                    )
            );
        }
    }

    @ParameterizedTest
    @ArgumentsSource(value = ParseThrowsIllegalArgumentExceptionOnInvalidInputArgumentsProvider.class)
    void parseThrowsIllegalArgumentExceptionOnInvalidInput(String input) {
        assertThrows(IllegalArgumentException.class, () -> SudokuParser.parse(input));
    }

    private static class ParseThrowsIllegalArgumentExceptionOnInvalidInputArgumentsProvider implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) {
            return Stream.of(
                    Arguments.of((String) null),
                    Arguments.of("84..7.9.2319..7...7..41...6......8.7.8.1.2.34.......5.6.75.83......8571.5.1..462"),
                    Arguments.of(".94..7.9.2319..7...7..41...6......8.7.8.1.2.34.......5.6.75.83......8571.5.1..462"),
                    Arguments.of(".94..7.9.2319..7...7..41...6......8.7.8.1.2.34.......5.6.75.83.....a8571.5.1..462"),
                    Arguments.of("999999999999999999999999999999999999999999999999999999999999999999999999999999999"),
                    Arguments.of("123456789123456789123456789123456789123456789123456789123456789123456789123456789")
            );
        }
    }
}
