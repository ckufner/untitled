package com.github.ckufner.puzzles.sudoku;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
final class SudokuData {
    private int[][] unsolved;
    private int[][] solved;
}
