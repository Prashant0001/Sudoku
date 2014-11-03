/**
 * This class represents Board for one Sudoku Puzzle
 */
package com.prashant.sudoku;

import java.util.ArrayList;

class SudokuBoard {
    private Cell[][] board;
    
    /** 
     * Constructor
     * Creates a 9*9 sudoku board from input 
     * @param 9 * 9 double dimension array for sudoku puzzle
     */
    public SudokuBoard(int[][] data) {
         board = new Cell[9][9];
        for(int x=0; x<9; x++) {
            for(int y=0; y<9; y++) {
                board[x][y] = new Cell(data[x][y]);
                // If the input already has a fixed value for the cell
                // then no determination needs to be made for the value of the cell
                if(data[x][y] != 0){
                    board[x][y].setValueDetermined();
                }
            }
        }
                
    }
    
    /**
     * Returns Cell from the sudoku board
     * @param X coordinate of the cell
     * @param Y coordinate of the cell
     * @return Cell from sudoku board
     */
    
    
    Cell getCell(int x, int y) {
        return board[x][y];
    }
    
    /**
     * Return the value of the specified cell
     * @param X coordinate of the cell
     * @param Y coordinate of the cell
     * @return value of the specified cell
     */
    
    int getVal(int x, int y) {
        return getCell(x,y).getVal();
    }
    
    /**
     * Checks if a board is solved 
     * @return true if all the values in the board have been determined
     */ 
     boolean isSolved() {
        for(int x=0; x<9; x++){
            for(int y=0; y<9; y++){
                // If any of the value in the board is zero, then the puzzle is not solved
                if(getVal(x,y) == 0){
                    return false;
                }
            }
        }
        return true;
    }
    
    /**
     * Determine possible values of a cell
     * @param X coordinate of the cell
     * @param Y coordinate of the cell
     * @return List of possible values for the specifed cell 
     */
      
    ArrayList<Integer> possibleCellValues(int x, int y) {
        ArrayList<Integer> possibleValues = new ArrayList<Integer>();
        for(int val=1; val<10; val++){
            if(isAllowedCellValue(x,y,val)){
                possibleValues.add(val);
            }
        }
        return possibleValues;
    }
    
    /**
     * Check if a value is legal to insert in cell.
     */
    private boolean isAllowedCellValue(int x, int y, int value) {
        
		// Check valid in row and column
		for (int i=0; i<9; i++){
		    
            // Check if value already exists in the entire row or column of the cell coordinates
		    if (getVal(i,y) == value || getVal(x,i) == value){
        		return false;
		    }
		}
        
		// Find first element in box
    	int offsetX = (x/3) * 3;
    	int offsetY = (y/3) * 3;
	    
    	// Check valid in 3 * 3 sub box of the sudoku board
		for (int i=0; i<3; i++){
			for (int j=0; j<3; j++){
				if (getVal(offsetX+i, offsetY+j) == value)
					return false;
			}
		}
    		
    	return true;
    }
    
    /**
     * Print the sudoku board.
     */
    public String toString() {
        StringBuffer sudokuBoard  = new StringBuffer();
        
        for(int i=0; i<9; i++) {
            for(int j=0; j<9; j++) {
                sudokuBoard.append(getVal(j, i)).append(", ");

                if((j+1) % 9 == 0){
                    sudokuBoard = sudokuBoard.deleteCharAt(sudokuBoard.lastIndexOf(",") );
               }
            }
            sudokuBoard.append("\n");
        }
        return sudokuBoard.toString();
    }
}