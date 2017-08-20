package com.mrsweeter.sweetsudoku.scenes;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public interface Scene {
	
	void draw(Graphics painter) throws SlickException;
	
	Scene update(GameContainer gc, int elapsedTimeSinceLastUpdateInMillis) throws SlickException;
	
//	void gainingFocus(GameContainer gc) throws SlickException;
//	
//	void loosingFocus(GameContainer gc) throws SlickException;
}
