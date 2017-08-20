package com.mrsweeter.sweetsudoku.scenes;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import com.mrsweeter.sweetsudoku.domains.Box;
import com.mrsweeter.sweetsudoku.domains.Party;
import com.mrsweeter.sweetsudoku.domains.ResourcesUtils;

public class EndGameScene implements Scene {
	
	private Party game;
	private int[] mouse = {0, 0};
	private Box new_game = new Box(550, 200, 50, 200);
	private Box home = new Box(550, 280, 50, 200);
	
	public EndGameScene(Party game) {
		this.game = game;
		game.stop();
		game.showSoluce();
	}

	@Override
	public void draw(Graphics painter) throws SlickException {

		ResourcesUtils.getImage("BG").draw(0, 0, 854, 480);
		
		painter.setFont(ResourcesUtils.getFont("MS"));
		
		painter.setColor(Color.black);
		painter.drawString("Coups joués: " + game.getStrokes(), 500, 80);
		painter.drawString("Temps de jeu: " + game.getChrono(), 500, 120);
		
		game.drawGrid(painter);
		
		if (new_game.isInBox(mouse[0], mouse[1]))	{
			ResourcesUtils.getImage("NGBE").draw(550, 200, new_game.dx-new_game.x, new_game.dy-new_game.y);
		} else {
			ResourcesUtils.getImage("NGB").draw(550, 200, new_game.dx-new_game.x, new_game.dy-new_game.y);
		}
		
		
		if (home.isInBox(mouse[0], mouse[1]))	{
			ResourcesUtils.getImage("HBE").draw(550, 280, home.dx-home.x, home.dy-home.y);
		} else {
			ResourcesUtils.getImage("HB").draw(550, 280, home.dx-home.x, home.dy-home.y);
		}
	}

	@Override
	public Scene update(GameContainer gc, int elapsedTimeSinceLastUpdateInMillis) throws SlickException {
		
		mouse[0] = Mouse.getX();
		mouse[1] = gc.getHeight() - Mouse.getY();
		Input input = gc.getInput();
		Scene s = this;
		
		if (input.isMouseButtonDown(0))	{
			if (new_game.isInBox(mouse[0], mouse[1]))	{
				
				s = new GameScene();
				
			} else if (home.isInBox(mouse[0], mouse[1]))	{
				
				s = new HomeScene();
				
			}
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
