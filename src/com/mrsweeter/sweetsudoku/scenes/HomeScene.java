package com.mrsweeter.sweetsudoku.scenes;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import com.mrsweeter.sweetsudoku.domains.Box;
import com.mrsweeter.sweetsudoku.domains.ResourcesUtils;

public class HomeScene implements Scene {
	
	private int[] mouse = {0, 0};
	private Box play = new Box(230, 200, 100, 400);
	
	@Override
	public void draw(Graphics painter) throws SlickException {

		painter.setFont(ResourcesUtils.getFont("R"));
		
		painter.setColor(Color.black);
		
		ResourcesUtils.getImage("BG").draw(0, 0, 854, 480);
		ResourcesUtils.getImage("L").draw(367, 30, 80, 81);
		
		painter.drawString("Sudoku", 470, 40);
		painter.drawString("Sweet", 130, 40);
		
		if (play.isInBox(mouse[0], mouse[1]))	{
			ResourcesUtils.getImage("PBE").draw(230, 200, play.dx-play.x, play.dy-play.y);
		} else {
			ResourcesUtils.getImage("PB").draw(230, 200, play.dx-play.x, play.dy-play.y);
		}
		
	}

	@Override
	public Scene update(GameContainer gc, int elapsedTimeSinceLastUpdateInMillis) throws SlickException {
		
		mouse[0] = Mouse.getX();
		mouse[1] = gc.getHeight() - Mouse.getY();
		Input input = gc.getInput();
		Scene s = this;
		
		if (input.isMouseButtonDown(0) && play.isInBox(mouse[0], mouse[1]))	{
			s = new GameScene();
		}
		if (input.isKeyPressed(Keyboard.KEY_ESCAPE))	{
			gc.exit();
		}
		
		input.clearKeyPressedRecord();
		input.clearControlPressedRecord();
		input.clearMousePressedRecord();
		
		return s;
	}
}
