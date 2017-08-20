package com.mrsweeter.sweetsudoku.core;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

public class Program {
	
	public static void main(String[] args)	{
		
		try	{
			
			AppGameContainer appgc;
			appgc = new AppGameContainer(new SudokuGame());
			appgc.setDisplayMode(854, 480, false);		
			appgc.start();
			
		} catch (SlickException e)	{
			
		}	
	}
}
