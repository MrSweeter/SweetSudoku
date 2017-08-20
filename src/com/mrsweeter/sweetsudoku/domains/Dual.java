package com.mrsweeter.sweetsudoku.domains;

public class Dual {
	
	private int value;
	private boolean defaultGrid;
	
	public Dual(int value, boolean defaultGrid)	{
		this.value = value;
		this.defaultGrid = defaultGrid;
	}
	
	public boolean isDefault()	{
		return defaultGrid;
	}
	
	public int getValue()	{
		return value;
	}

	public void setValue(int i) {
		value = i;
	}
}
