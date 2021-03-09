package com.github.ckufner.puzzles.sudoku.strategies;

import com.github.ckufner.IntTuple;
import com.github.ckufner.puzzles.sudoku.SudokuUtil;

import java.util.ArrayList;

//TODO: test
public interface FullHouseStrategy {
    static boolean applyStrategy(int[][] puzzle) {
        for (int i = 0; i < 9; i++) {
            if (fullHouseForRow(puzzle, i)) return true;
            if (fullHouseForColumn(puzzle, i)) return true;
            if (fullHouseForSector(puzzle, i)) return true;
        }

        return false;
    }

    static boolean fullHouseForRow(int[][] puzzle, int row) {
        int[] correspondingRow = puzzle[row];

        ArrayList<Integer> candidateValues = SudokuUtil.getCandidateList();

        int designatedPos = -1;

        for (int currentColumnIndex = 0; currentColumnIndex < 9; currentColumnIndex++) {
            if (correspondingRow[currentColumnIndex] == 0) designatedPos = currentColumnIndex;

            candidateValues.remove(Integer.valueOf(correspondingRow[currentColumnIndex]));
        }

        if (candidateValues.size() == 1 && designatedPos != -1) {
            puzzle[row][designatedPos] = candidateValues.get(0);
            return true;
        }

        return false;
    }

    static boolean fullHouseForColumn(int[][] puzzle, int column) {
        int[] candidateColumn = SudokuUtil.getColumn(puzzle, column);

        ArrayList<Integer> candidateValues = SudokuUtil.getCandidateList();

        int designatedPos = -1;

        for (int currentRowIndex = 0; currentRowIndex < 9; currentRowIndex++) {
            if (candidateColumn[currentRowIndex] == 0) designatedPos = currentRowIndex;

            candidateValues.remove(Integer.valueOf(candidateColumn[currentRowIndex]));
        }

        if (candidateValues.size() == 1 && designatedPos != -1) {
            puzzle[designatedPos][column] = candidateValues.get(0);
            return true;
        }

        return false;
    }

    static boolean fullHouseForSector(int[][] puzzle, int sector) {
        int[] candidateSector = SudokuUtil.getSector(puzzle, sector);

        ArrayList<Integer> candidateValues = SudokuUtil.getCandidateList();

        int designatedPos = -1;

        for (int currentPosition = 0; currentPosition < 9; currentPosition++) {
            if (candidateSector[currentPosition] == 0) designatedPos = currentPosition;

            candidateValues.remove(Integer.valueOf(candidateSector[currentPosition]));
        }

        if (candidateValues.size() == 1 && designatedPos != -1) {
            IntTuple cellPosition = SudokuUtil.getGridPositionForSectorAndPosition(sector, designatedPos);
            puzzle[cellPosition.getItem1()][cellPosition.getItem2()] = candidateValues.get(0);
            return true;
        }

        return false;
    }
}
