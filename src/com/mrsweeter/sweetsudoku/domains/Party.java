package com.mrsweeter.sweetsudoku.domains;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Party {
	
	private Grid grid;
	private int sum_change = 0;
	private Box selectBox;
	private Dual selected;
	private Chronometer chrono;
	private List<Box> gridBox;
	
	public Party()	{
		
		grid = new Grid(36);
		gridBox = new ArrayList<>();
		chrono = new Chronometer();
		chrono.start();
		
	}
	
	public void pause()	{
		chrono.pause();
	}
	
	public void stop()	{
		
		chrono.stop();
		
	}
	
	public void setBox(int x, int y)	{
		if(x >= 9 || y >= 9 || x < 0 || y < 0)	{return;}
		selectBox = new Box(DVF.GRID_POSITION_START[0] + y*DVF.LARGE_CASE, DVF.GRID_POSITION_START[1] + x*DVF.LARGE_CASE, DVF.CASE_SIZE, DVF.CASE_SIZE);
		selected = this.getGrid()[x][y];
	}
	
	public List<Box> getGridBox()	{
		return gridBox;
	}
	
	public int getStrokes()	{
		return sum_change;
	}

	public boolean setValue(int v)	{
		selected.setValue(v);
		sum_change++;
		return grid.isResolve();
	}
	
	public Dual[][] getGrid()	{
		return grid.getGrid();
	}
	
	public Box getSelectedBox()	{
		return selectBox;
	}
	public Dual getSelectedDual()	{
		return selected;
	}
	public void showSoluce() {
		grid.solveSudoku();
	}
	
	public String getChrono()	{
		return chrono.timeToString('h', 'm', 's', ' ');
	}
	
	public void drawGrid(Graphics painter) throws SlickException	{
		
		int x, y, dx, dy, nbCase;
		
		painter.setColor(Color.darkGray);
		painter.setLineWidth(DVF.LINE_WIDTH-4);
		
		for (int i = 0; i < 9; i++)	{
			
			x = DVF.GRID_POSITION_START[0] - DVF.LINE_WIDTH;
			y = DVF.GRID_POSITION_START[1] - DVF.LINE_WIDTH + i * DVF.LARGE_CASE;
			dx = x + DVF.GRID_SIZE;
			dy = y;
			
			painter.drawLine(x, y, dx, dy);
			
			x = DVF.GRID_POSITION_START[1] - DVF.LINE_WIDTH;
			y = DVF.GRID_POSITION_START[0] - DVF.LINE_WIDTH + i * DVF.LARGE_CASE;
			dx = x + DVF.GRID_SIZE;
			dy = y;
			
			painter.drawLine(y, x, dy, dx);
			
		}
		
		painter.setColor(Color.black);
		painter.setLineWidth(DVF.LINE_WIDTH);
		
		for (int i = 0; i < 4; i++)	{
			
			nbCase = i * 3;
			
			x = DVF.GRID_POSITION_START[0] - DVF.LINE_WIDTH;
			y = DVF.GRID_POSITION_START[1] - DVF.LINE_WIDTH + nbCase * DVF.LARGE_CASE;
			dx = x + DVF.GRID_SIZE;
			dy = y;
			
			painter.drawLine(x, y, dx, dy);
			
			x = DVF.GRID_POSITION_START[1] - DVF.LINE_WIDTH;
			y = DVF.GRID_POSITION_START[0] - DVF.LINE_WIDTH + nbCase * DVF.LARGE_CASE;
			dx = x + DVF.GRID_SIZE;
			dy = y;
			
			painter.drawLine(y, x, dy, dx);
			
		}
		
		painter.setLineWidth(1);
		
		drawGridValue(painter);
		
	}
	
	private void drawGridValue(Graphics painter) throws SlickException	{
		
		Dual[][] grid = this.getGrid();
		gridBox.clear();
		int x = DVF.GRID_POSITION_START[0]-1;
		int y = DVF.GRID_POSITION_START[1];
		Image value;
		
		for (int i = 0; i < grid.length; i++)	{
			for (int j = 0; j < grid[i].length; j++)	{
				
				if (grid[i][j].getValue() != 0)	{
					value = ResourcesUtils.getImage("CV" + grid[i][j].getValue());
					value.draw(x, y, DVF.CASE_SIZE, DVF.CASE_SIZE);
				}
				
				if (grid[i][j].isDefault())	{
					painter.setColor(Color.green);
				} else {
					painter.setColor(Color.red);
				}
				painter.drawRect(x, y, DVF.CASE_SIZE, DVF.CASE_SIZE);
				
				gridBox.add(new Box(x, y, DVF.CASE_SIZE, DVF.CASE_SIZE));
				
				x += DVF.LARGE_CASE;
				
			}
			y += DVF.LARGE_CASE;
			x = DVF.GRID_POSITION_START[0]-1;
		}
	}
}
