package com.mrsweeter.sweetsudoku.domains;

public class Box {
	
	public int x;
	public int y;
	public int dx;
	public int dy;
	
	public Box (int x, int y, int H, int lg)	{
		
		this.x = x;
		this.y = y;
		this.dx = this.x + lg;
		this.dy = this.y + H;
	}
	
	public boolean isInBox(int a, int b)	{
		
		if (a < x || a > dx || b < y || b > dy)	{
			return false;
		}
		return true;
	}
}
