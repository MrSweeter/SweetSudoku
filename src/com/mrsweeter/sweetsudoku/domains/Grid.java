package com.mrsweeter.sweetsudoku.domains;

import java.util.Random;

public class Grid {
	
	private static final int BLOCK_SIZE = 3;
	private static final int GRID_SIZE = BLOCK_SIZE * BLOCK_SIZE;
	private Dual[][] grid;
	private Dual[][] soluce;
	
	public Grid(int nb)	{
		generateGrid(nb);
	}

	private void generateGrid(int nb) {
		
		loadGrid();
		
		for (int i = 0; i < nb; i++)	{
			
			addNumber();
			
		}
		
		for (int i = 0; i < soluce.length; i++)	{
			soluce[i] = grid[i].clone();
		}
		
		if (solve(soluce) == false)	{
			generateGrid(nb);
		}
		
		for (int i = 0; i < soluce.length; i++)	{
			for (int j = 0; j < soluce[i].length; j++)	{
				
				soluce[i][j] = new Dual(soluce[i][j].getValue(), true);
				
			}
		}
	}
	
	private void loadGrid()	{
		
		grid = new Dual[GRID_SIZE][GRID_SIZE];
		soluce = new Dual[GRID_SIZE][GRID_SIZE];
		
		for (int i = 0; i < grid.length; i++)	{
			for (int j = 0; j < grid[i].length; j++)	{
				grid[i][j] = new Dual(0, false);
			}
		}
	}
	
	private void addNumber()	{
		
		Random r = new Random();
		int v = r.nextInt(8)+1;
		int x = r.nextInt(8);
		int y = r.nextInt(8);
		
		if (grid[x][y].getValue() == 0 && isAllowed(grid, x, y, v))	{
			
			grid[x][y] = new Dual(v, true);
			
		} else {
			addNumber();
		}
		
	}
	
	public Dual[][] getGrid() {
		return grid;
	}
	
	public void solveSudoku() {
		grid = soluce;
	}
	
	public boolean isResolve()	{
		
		for (int i = 0; i < soluce.length; i++)	{
			for (int j = 0; j < soluce[i].length; j++)	{
				
				if (!isValid(grid, i, j, grid[i][j].getValue()))	{
					return false;
				}
//				if (soluce[i][j] != grid[i][j])	{
//					return false;
//				}
			}
		}
		return true;
	}
	
	private boolean solve(Dual[][] grid)	{
		if (grid.length != 0) {
			return solve(grid, 1);
		}
		return false;
	}
	
	private boolean solve(Dual[][] grid, int value)	{
		
		int[] pos = {0, 0};
		if (nextFreeCell(grid, pos))	{
			
			if (isAllowed(grid, pos[0], pos[1], value))	{
				grid[pos[0]][pos[1]] = new Dual(value, false);
				if (solve(grid))	{
					return true;
				} else {
					grid[pos[0]][pos[1]] = new Dual(0, false);
				}
			}	
			return (value+1 > GRID_SIZE) ? false : solve(grid, value+1);
			
		} else {
			return true;
		}
	}
	
	public boolean nextFreeCell(Dual[][] grid, int[] pos) {
		
		int row = pos[0], col = pos[1];
		
		if (row >= 0 && row < grid.length && col >= 0 && col < grid[row].length)	{
	
			if (grid[row][col].getValue() == 0) {
				return true;
			}

			col++;
			if (col >= grid[row].length) {
				row++;
				col = 0;
			}
			
			pos[0] = row;
			pos[1] = col;
			return nextFreeCell(grid, pos);
		}
		return false;
	}
	
	private boolean isValid(Dual[][] gridIG, int row, int col, int value) {
		
		Dual[][] grid = new Dual[gridIG.length][gridIG.length];
		
		for (int i = 0; i < grid.length; i++)	{
			grid[i] = gridIG[i].clone();
		}
		
		if (value > 0 && value <= GRID_SIZE && grid[row][col].getValue() != 0)	{
	    	
			grid[row][col] = new Dual(0, false);
	    	// selection du block
			int rowBlock = row-(row%BLOCK_SIZE), colBlock = col-(col%BLOCK_SIZE);
	    	
	        if (isValidHorizontalCell(grid, row, 0, value) && isValidVerticalCell(grid, col, 0, value) && isValidBlockCell(grid, rowBlock, colBlock, rowBlock+3, colBlock+3, value))	{
	        	grid[row][col] = new Dual(value, true);
	        	return true;
	        }
	    }
		return false;
	}
	
	public boolean isAllowed(Dual[][] grid, int row, int col, int value) {
		
	    if (value > 0 && value <= GRID_SIZE && grid[row][col].getValue() == 0)	{
	    	
	    	// selection du block
			int rowBlock = row-(row%BLOCK_SIZE), colBlock = col-(col%BLOCK_SIZE);
	    	
	        if (isValidHorizontalCell(grid, row, 0, value) && isValidVerticalCell(grid, col, 0, value) && isValidBlockCell(grid, rowBlock, colBlock, rowBlock+3, colBlock+3, value))	{
	        	return true;
	        }
	    }

	    return false;
	}
	
	private boolean isValidHorizontalCell(Dual[][] grid, int row, int col, int value) {
		
		if (row >= 0 && row < grid.length && col >= 0 && col < grid[row].length)	{
			if (grid[row][col].getValue() == value)	{
	            return false;
	        } else {
	        	return isValidHorizontalCell(grid, row, col+1, value);
	        }
		}
		return true;
	}

	private boolean isValidVerticalCell(Dual[][] grid, int col, int row, int value) {
		
		if (row >= 0 && row < grid.length && col >= 0 && col < grid[row].length)	{
			if (grid[row][col].getValue() == value)	{
	            return false;
	        } else {
	        	return isValidVerticalCell(grid, col, row+1, value);
	        }
		}
		return true;
	}
	
	private boolean isValidBlockCell(Dual[][] grid, int row, int col, int rowLimit, int colLimit, int value)	{
		 
		if (row >= rowLimit-BLOCK_SIZE && row < rowLimit && col >= colLimit-BLOCK_SIZE && col < colLimit)	{
			if (grid[row][col].getValue() == value)	{
                return false;
            } else {
            	col++;
            	if (col >= colLimit)	{
            		col = colLimit-BLOCK_SIZE;
            		row++;
            	}
            	return isValidBlockCell(grid, row, col, rowLimit, colLimit, value);
            }
		}
	    return true;

	}

	
}
