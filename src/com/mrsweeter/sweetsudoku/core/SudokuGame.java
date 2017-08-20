package com.mrsweeter.sweetsudoku.core;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import com.mrsweeter.sweetsudoku.domains.ResourcesUtils;
import com.mrsweeter.sweetsudoku.scenes.*;

public class SudokuGame extends BasicGame {
	
	private Scene currentScene;
	
	public SudokuGame() {
		super("Sweet - Sudoku");
	}
	
	@Override
	public void init(GameContainer gc) throws SlickException {
		ResourcesUtils.loadResources();
		gc.setShowFPS(false);
		currentScene = new HomeScene();
	}
	
	@Override
	public void render(GameContainer gc, Graphics graphics) throws SlickException {
		
		currentScene.draw(graphics);
		
	}

	@Override
	public void update(GameContainer gc, int timeInMilliSecond) throws SlickException {
		
		currentScene = currentScene.update(gc, timeInMilliSecond);
		
	}
}
