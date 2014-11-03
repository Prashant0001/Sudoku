/**
 * This class represents a cell in the Sudoku Board
 */
package com.prashant.sudoku;

import java.util.ArrayList;

public class Cell {
    
    /*
     * List of values for this cell
     */
    private ArrayList<Integer> vals;
    /*
     * Boolean variable indicating whether the value for the cell has been determined
     */
    private boolean valueDetermined;
    
    /**
     * Constructor
     * @param Intiaial value for this cell
     */
    public Cell(int val) {
        this.vals = new ArrayList<Integer>();
        this.vals.add(val);
    }
    
    void setValueDetermined() {
        this.valueDetermined = true;
    }
    
    boolean isValueDetermined() {
        return this.valueDetermined;
    }
    
    /**
     * Set the possible values for this cell
     * @param vals
     */
    void setVals(ArrayList<Integer> vals) {
        this.vals.clear();
        this.vals.addAll(vals);       
    }
    
    void setVal(int val) {
        this.vals.clear();
        this.vals.add(val);
    }
    
    /** Return the first value from the possible values for this cell
     * 
     * @return the first value from the possible values for this cell
     */
    int getVal() {
        return vals.get(0);
    }
    
    /**
     * Remove the first value from the posssible values of this cell
     */
    void removeVal() {
        vals.remove(0);
    }
    
    /**
     * Whether the cell has multiple possible values
     * @return true if the cell has multiple possible values
     */
    boolean hasMultipleValues() {
        return vals.size() > 1;
    }
    
    public String toString() {
        return vals.toString();
    }
}
