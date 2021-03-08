package com.github.ckufner.puzzles.sudoku;

import com.github.ckufner.IntTuple;
import com.github.ckufner.util.ArrayUtil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

//TODO: hamming weight

interface SudokuUtil {
    static void printPuzzle(int[][] puzzle) {
        for (int row = 0; row < 9; row++) {
            System.out.print("{ ");
            for (int column = 0; column < 9; column++) {
                if (column > 0) System.out.print(", ");
                System.out.print("" + puzzle[row][column]);
            }
            System.out.print(" }");
            System.out.println();
        }
    }

    //TODO: test
    static IntTuple getNextUnsolvedCell(int[][] puzzle) {
        for (int row = 0; row < 9; row++) {
            for (int column = 0; column < 9; column++) {
                if (puzzle[row][column] != 0) continue;

                return IntTuple.of(row, column);
            }
        }

        return null;
    }

    //TODO: test
    static ArrayList<Integer> getCandidateList() {
        ArrayList<Integer> candidates = new ArrayList<>();
        candidates.add(1);
        candidates.add(2);
        candidates.add(3);
        candidates.add(4);
        candidates.add(5);
        candidates.add(6);
        candidates.add(7);
        candidates.add(8);
        candidates.add(9);

        return candidates;
    }

    //TODO: test
    static int[] getColumn(int[][] puzzle, int column) {
        return ArrayUtil.getColumn(column, puzzle);
    }

    static int[] getSector(int[][] puzzle, int sector) {
        int[] result = new int[9];

        int row = (sector / 3) * 3;
        int col = (sector % 3) * 3;

        result[0] = puzzle[row + 0][col + 0];
        result[1] = puzzle[row + 0][col + 1];
        result[2] = puzzle[row + 0][col + 2];

        result[3] = puzzle[row + 1][col + 0];
        result[4] = puzzle[row + 1][col + 1];
        result[5] = puzzle[row + 1][col + 2];

        result[6] = puzzle[row + 2][col + 0];
        result[7] = puzzle[row + 2][col + 1];
        result[8] = puzzle[row + 2][col + 2];

        return result;
    }

    static int[] getSetValuesOfRow(int[][] puzzle, int row) {
        int[] buffer = new int[9];

        int foundValues = 0;
        for (int column = 0; column < 9; column++) {
            int value = puzzle[row][column];

            if (value == 0) continue;

            buffer[foundValues++] = value;
        }

        return ArrayUtil.copyArray(buffer, foundValues);
    }

    static int[] getSetValuesOfColumn(int[][] puzzle, int column) {
        int[] buffer = new int[9];

        int foundValues = 0;
        for (int row = 0; row < 9; row++) {
            int value = puzzle[row][column];

            if (value == 0) continue;

            buffer[foundValues++] = value;
        }

        return ArrayUtil.copyArray(buffer, foundValues);
    }

    static int[] getSetValuesOfSectorForCell(int[][] puzzle, int row, int column) {
        int sector = SudokuUtil.getSectorValueForCell(row, column);

        int[] values = getSector(puzzle, sector);

        int[] buffer = new int[9];
        int foundValues = 0;
        for (int position = 0; position < 9; position++) {
            int value = values[position];

            if (value == 0) continue;

            buffer[foundValues++] = value;
        }

        return ArrayUtil.copyArray(buffer, foundValues);
    }

    static int[] getCandidateValuesOfCell(int[][] puzzle, int row, int column) {
        if (puzzle[row][column] != 0) return new int[0];

        ArrayList<Integer> candidates = getCandidateList();

        for (int value : getSetValuesOfRow(puzzle, row)) {
            candidates.remove(Integer.valueOf(value));
        }

        for (int value : getSetValuesOfColumn(puzzle, column)) {
            candidates.remove(Integer.valueOf(value));
        }

        for (int value : getSetValuesOfSectorForCell(puzzle, row, column)) {
            candidates.remove(Integer.valueOf(value));
        }

        int[] result = new int[candidates.size()];
        for (int i = 0; i < candidates.size(); i++) {
            result[i] = candidates.get(i);
        }

        return result;
    }

    static List<int[]> getCandidateValuesOfRow(int[][] puzzle, int row) {
        List<int[]> candidates = new ArrayList<>();

        candidates.add(getCandidateValuesOfCell(puzzle, row, 0));
        candidates.add(getCandidateValuesOfCell(puzzle, row, 1));
        candidates.add(getCandidateValuesOfCell(puzzle, row, 2));
        candidates.add(getCandidateValuesOfCell(puzzle, row, 3));
        candidates.add(getCandidateValuesOfCell(puzzle, row, 4));
        candidates.add(getCandidateValuesOfCell(puzzle, row, 5));
        candidates.add(getCandidateValuesOfCell(puzzle, row, 6));
        candidates.add(getCandidateValuesOfCell(puzzle, row, 7));
        candidates.add(getCandidateValuesOfCell(puzzle, row, 8));

        return candidates;
    }

    static List<int[]> getCandidateValuesOfColumn(int[][] puzzle, int column) {
        List<int[]> candidates = new ArrayList<>();

        candidates.add(getCandidateValuesOfCell(puzzle, 0, column));
        candidates.add(getCandidateValuesOfCell(puzzle, 1, column));
        candidates.add(getCandidateValuesOfCell(puzzle, 2, column));
        candidates.add(getCandidateValuesOfCell(puzzle, 3, column));
        candidates.add(getCandidateValuesOfCell(puzzle, 4, column));
        candidates.add(getCandidateValuesOfCell(puzzle, 5, column));
        candidates.add(getCandidateValuesOfCell(puzzle, 6, column));
        candidates.add(getCandidateValuesOfCell(puzzle, 7, column));
        candidates.add(getCandidateValuesOfCell(puzzle, 8, column));

        return candidates;
    }

    static List<int[]> getCandidateValuesOfSector(int[][] puzzle, int sector) {
        List<int[]> candidates = new ArrayList<>();

        candidates.add(getCandidateValuesOfCell(puzzle, (sector / 3) * 3 + 0 / 3, (sector % 3) * 3 + 0 % 3));
        candidates.add(getCandidateValuesOfCell(puzzle, (sector / 3) * 3 + 1 / 3, (sector % 3) * 3 + 1 % 3));
        candidates.add(getCandidateValuesOfCell(puzzle, (sector / 3) * 3 + 2 / 3, (sector % 3) * 3 + 2 % 3));
        candidates.add(getCandidateValuesOfCell(puzzle, (sector / 3) * 3 + 3 / 3, (sector % 3) * 3 + 3 % 3));
        candidates.add(getCandidateValuesOfCell(puzzle, (sector / 3) * 3 + 4 / 3, (sector % 3) * 3 + 4 % 3));
        candidates.add(getCandidateValuesOfCell(puzzle, (sector / 3) * 3 + 5 / 3, (sector % 3) * 3 + 5 % 3));
        candidates.add(getCandidateValuesOfCell(puzzle, (sector / 3) * 3 + 6 / 3, (sector % 3) * 3 + 6 % 3));
        candidates.add(getCandidateValuesOfCell(puzzle, (sector / 3) * 3 + 7 / 3, (sector % 3) * 3 + 7 % 3));
        candidates.add(getCandidateValuesOfCell(puzzle, (sector / 3) * 3 + 8 / 3, (sector % 3) * 3 + 8 % 3));

        return candidates;
    }

    //TODO: test
    static boolean isPuzzleValid(int[][] puzzle) {
        return areRowsValid(puzzle)
                && areColumnsValid(puzzle)
                && areSectorsValid(puzzle);
    }

    //TODO: test
    static boolean areRowsValid(int[][] puzzle) {
        HashSet<Integer> set = new HashSet<>();

        for (int rowIndex = 0; rowIndex < 9; rowIndex++) {
            set.clear();

            for (int columnIndex = 0; columnIndex < 9; columnIndex++) {
                int value = puzzle[rowIndex][columnIndex];
                if (value == 0) continue;

                if (!set.add(value)) return false;
            }
        }

        return true;
    }

    //TODO: test
    static boolean areColumnsValid(int[][] puzzle) {
        HashSet<Integer> set = new HashSet<>();

        for (int columnIndex = 0; columnIndex < 9; columnIndex++) {
            set.clear();

            for (int rowIndex = 0; rowIndex < 9; rowIndex++) {
                int value = puzzle[rowIndex][columnIndex];
                if (value == 0) continue;

                if (!set.add(value)) return false;
            }
        }

        return true;
    }

    //TODO: test
    static boolean areSectorsValid(int[][] puzzle) {
        HashSet<Integer> set = new HashSet<>();

        for (int sectorIndex = 0; sectorIndex < 9; sectorIndex++) {
            set.clear();

            int[] sectorValues = getSector(puzzle, sectorIndex);

            for (int cellPosition = 0; cellPosition < 9; cellPosition++) {
                int value = sectorValues[cellPosition];
                if (value == 0) continue;

                if (!set.add(value)) return false;
            }
        }

        return true;
    }

    static int getSectorValueForCell(int row, int column) {
        return (row / 3) * 3 + (column / 3);
    }

    static IntTuple getGridPositionForSectorAndPosition(int sector, int position) {
        return IntTuple.of(
                (sector / 3) * 3 + position / 3,
                (sector % 3) * 3 + position % 3
        );
    }
}
