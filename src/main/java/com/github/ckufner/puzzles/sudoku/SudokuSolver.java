package com.github.ckufner.puzzles.sudoku;

import com.github.ckufner.IntTuple;
import com.github.ckufner.puzzles.sudoku.strategies.SudokuStrategiesUtil;
import com.github.ckufner.util.ArrayUtil;

import java.util.ArrayList;
import java.util.List;

public class SudokuSolver {
    public static SudokuSolver of(int[][] unsolvedPuzzle) {
        if (!SudokuUtil.isPuzzleValid(unsolvedPuzzle, true)) throw new IllegalArgumentException("puzzle is not valid!");

        return new SudokuSolver(unsolvedPuzzle);
    }

    private int[][] unsolvedPuzzle;
    private boolean singleResult;
    private List<int[][]> solutions;

    private int backtracks;

    private int[][] puzzle;

    private SudokuSolver(int[][] unsolvedPuzzle) {
        this.unsolvedPuzzle = ArrayUtil.copy2dArray(unsolvedPuzzle);

        this.solutions = new ArrayList<>();

        this.backtracks = 0;
        this.puzzle = ArrayUtil.copy2dArray(this.unsolvedPuzzle);
    }

    public int[][] getUnsolvedPuzzle() {
        return ArrayUtil.copy2dArray(this.unsolvedPuzzle);
    }

    public int getBacktracks() {
        return this.backtracks;
    }

    public List<int[][]> solve(boolean singleResult) {
        this.solutions.clear();
        this.singleResult = singleResult;

        //TODO: check if only zeros

        SudokuStrategiesUtil.applyStrategies(this.puzzle);

        this.solvePuzzle();

        return this.solutions;
    }

    private void solvePuzzle() {
        if (!this.solutions.isEmpty() && this.singleResult) return;

        IntTuple nextUnsolvedCell = SudokuUtil.getNextUnsolvedCell(this.puzzle);
        if (nextUnsolvedCell == null) {
            this.solutions.add(ArrayUtil.copy2dArray(this.puzzle));
            return;
        }

        int cellRow = nextUnsolvedCell.getItem1();
        int cellColumn = nextUnsolvedCell.getItem2();

        int[] candidateSolutions = SudokuUtil.getCandidateValuesOfCell(this.puzzle, cellRow, cellColumn);
        if (candidateSolutions.length == 0) return;

        for (int candidateSolution : candidateSolutions) {
            this.puzzle[cellRow][cellColumn] = candidateSolution;
            if (SudokuUtil.isPuzzleValid(this.puzzle, false)) {
                int[][] puzzleStateBeforeImplications = ArrayUtil.copy2dArray(this.puzzle);
                SudokuStrategiesUtil.applyStrategies(this.puzzle);

                if (SudokuUtil.isPuzzleValid(this.puzzle, false)) {
                    this.solvePuzzle();
                }

                this.puzzle = puzzleStateBeforeImplications;
            }

            this.puzzle[cellRow][cellColumn] = 0;
            this.backtracks++;
        }
    }
}
