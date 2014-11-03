/**
 * This is the main worker clas for solving the sudoku puzzle
 */
package com.prashant.sudoku;

import java.util.ArrayList;

public class SudokuSolver {
    
    private SudokuBoard sudokuBoard;
    
    /** Constructor
     * 
     * @param sudokuBoard
     */
    public SudokuSolver(SudokuBoard sudokuBoard){
         this.sudokuBoard = sudokuBoard; 
    }
    
    /**
     * Return Sudoku Board which is being worked on
     * @return SudokuBoard
     */
    SudokuBoard getBoard() {
        return sudokuBoard;
    }
    
    /** Determine if the puzzle has been solved
     * 
     * @return true if the sudoku puzzle has been solved
     */
    boolean solve() {
        
        /*The backtracking algorithm fills up a blank cell with a valid number 
        (i.e. no two same numbers in any row, column or big box), 
        moves on to the next cell, and then does the same thing. 
        If all the possible numbers from 1 to 9 are invalid for any cell 
        that the algorithm is currently “at”, the algorithm moves back to 
        the previous cell and changes that cell’s value to another valid number. 
        Afterwards, it moves back to the next cell and the whole process repeats
        */
        int x = 0;
        int y = 0;
        boolean backtrack = false;
        
        while(isValidCell(x,y)) {
            if(!backtrack && sudokuBoard.getVal(x,y) == 0) {
                backtrack = determinePossibleValues(x,y,backtrack);
            } else {
                backtrack = backtrack(x,y,backtrack);
            }
            
            int oldX = x;
            int oldY = y;
            if(backtrack) {
                x = prevX(oldX,oldY);
                y = prevY(oldX,oldY);
            } else {
                x = nextX(oldX,oldY);
                y = nextY(oldX,oldY);
            }
        }
        return sudokuBoard.isSolved();
    }
    
    private boolean backtrack(int x, int y, boolean backtrack) {
        if(sudokuBoard.getCell(x,y).hasMultipleValues()) {
            sudokuBoard.getCell(x,y).removeVal();
            return false;
        }
        if(!sudokuBoard.getCell(x,y).isValueDetermined())
            sudokuBoard.getCell(x,y).setVal(0);
        
        return backtrack;     
    }
    
    private boolean determinePossibleValues(int x, int y, boolean backtrack) {
        ArrayList<Integer> possibleValues = sudokuBoard.possibleCellValues(x,y);
        if(possibleValues.isEmpty()) {
            return true;
        } else {
            sudokuBoard.getCell(x,y).setVals(possibleValues);
        }
        return backtrack;
    }
    
    private boolean isValidCell(int x, int y) {
        return x > -1 && y > -1 && x < 9 && y < 9;
    }
        
    private int nextX(int x, int y) {
        return nextPos(x,y)[0];
    }
    
    private int nextY(int x, int y) { 
        return nextPos(x,y)[1]; 
    }
    
    private int prevX(int x, int y) {
        return prevPos(x,y)[0];
    }
    
    private int prevY(int x, int y) {
        return prevPos(x,y)[1];
    }
    
    private int[] nextPos(int x, int y) {
        if((x+1) % 9 == 0) {
            y++;
            x = 0;
        } else {
            x++;
        }
        int[] position = { x,y };
        return position;
    }
    
    private int[] prevPos(int x, int y) {
        if(x == 0) {
            y--;
            x = 8;
        } else {
            x--;
        }
        int[] position = { x,y };
        return position;
    }
}