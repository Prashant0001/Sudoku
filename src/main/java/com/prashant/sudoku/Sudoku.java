/**
 * This class  is the main class for the application
 */

package com.prashant.sudoku;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

class Sudoku {
    
    /**
     * Constructor
     * @param the filename which has sudoku problem which is to be solved
     */
    public Sudoku(String fileName) {
        
        // Parse the file to generate the sudoku puzzles which are to be solved
        ArrayList<int[][]> sudokuBoards = parseInput(fileName);
        
        for (int[][] sudokuBoard: sudokuBoards)
        {
            SudokuSolver solver = new SudokuSolver(new SudokuBoard(sudokuBoard));
            System.out.println("\n" +  "Solving Sudoku Puzzle:" + "\n");
            System.out.println(solver.getBoard());
            
            boolean solved = solver.solve();
            if(solved) {
                System.out.println("Solution for Sudoku Puzzle:" + "\n");
                System.out.println(solver.getBoard());            
            } else {
                System.out.println("No solution found ");
            }
        }
        
       
    }
    
    /**
     * Parse the input file as a list of SudokuBoard
     * @param the file name which contains sudoku puzzle
     * @return List of Sudoku puzzles
     */
    private ArrayList<int[][]> parseInput(String fileName) {
       
        ArrayList<int[][]> boards = new ArrayList<int[][]>();
        try{
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line;
            int j = 0;
            int[][] board = new int[9][9];
            while ((line = br.readLine()) != null) {
              	 String[] s = line.split(",");
            	 if (s.length !=9){
            	     continue;
            	 }
            	     
            	for(int i=0; i<s.length; i++) {
                    board[i%9][j] = Integer.parseInt(s[i]);
                    if((i+1) % 9 == 0) {
                        j++;
                    }
                    
                }
            	// After nine rows, create a new sudoku board
            	if (j  % 9 == 0){
                    boards.add(board);
                    board = new int[9][9];    
                    j = 0;
                }
        }
        br.close();
        }catch (Exception e){
        	e.printStackTrace();
        }
       
        return boards;
    }
    
    /**
     * Enter filename which has sudoku problem as argument
     */
    public static void main(String args[]) {
        if (args.length == 0){
            new Sudoku("SudokuBoard.txt");
        }else
        {
            new Sudoku(args[0]);
        }
    }
}