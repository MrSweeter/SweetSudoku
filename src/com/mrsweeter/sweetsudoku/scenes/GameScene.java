package com.mrsweeter.sweetsudoku.scenes;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import com.mrsweeter.sweetsudoku.domains.Box;
import com.mrsweeter.sweetsudoku.domains.DVF;
import com.mrsweeter.sweetsudoku.domains.Party;
import com.mrsweeter.sweetsudoku.domains.ResourcesUtils;

public class GameScene implements Scene {
	
	private Party game;
	
	public GameScene() {
		game = new Party();
	}
	
	@Override
	public void draw(Graphics painter) throws SlickException {
		
		ResourcesUtils.getImage("BG").draw(0, 0, 854, 480);
		
		painter.setFont(ResourcesUtils.getFont("MS"));
		
		painter.setColor(Color.black);
		painter.drawString("Coups joués: " + game.getStrokes(), 500, 80);
		painter.drawString("Temps de jeu: " + game.getChrono(), 500, 120);
		
		game.drawGrid(painter);
		
		if (game.getSelectedBox() != null)	{
			ResourcesUtils.getImage("SB").draw(game.getSelectedBox().x, game.getSelectedBox().y, DVF.CASE_SIZE, DVF.CASE_SIZE);
		}
	}

	@Override
	public Scene update(GameContainer gc, int elapsedTimeSinceLastUpdateInMillis) throws SlickException {
		
		Input input = gc.getInput();
		Scene s = this;
		
		if (!gc.isPaused())	{
			
			if (input.isMouseButtonDown(0))	{
				
				int x = Mouse.getX();
				int y = gc.getHeight() - Mouse.getY();
				int temp = y;
				
				for (Box box : game.getGridBox())	{
					if (box.isInBox(x, y))	{
						// [WARNING] Slick2D Inversion des axes
						y = (x-DVF.GRID_POSITION_START[0])/DVF.LARGE_CASE;
						x = (temp-DVF.GRID_POSITION_START[1])/DVF.LARGE_CASE;
						game.setBox(x, y);
						break;
					}
				}
			}
			
			if (game.getSelectedDual() != null && !game.getSelectedDual().isDefault())	{
				
				boolean win = false;
				if (input.isKeyPressed(Keyboard.KEY_0) || input.isKeyPressed(Keyboard.KEY_NUMPAD0))	{
					win = game.setValue(0);
				} else if (input.isKeyPressed(Keyboard.KEY_1) || input.isKeyPressed(Keyboard.KEY_NUMPAD1))	{
					win = game.setValue(1);
				} else if (input.isKeyPressed(Keyboard.KEY_2) || input.isKeyPressed(Keyboard.KEY_NUMPAD2))	{
					win = game.setValue(2);
				} else if (input.isKeyPressed(Keyboard.KEY_3) || input.isKeyPressed(Keyboard.KEY_NUMPAD3))	{
					win = game.setValue(3);
				} else if (input.isKeyPressed(Keyboard.KEY_4) || input.isKeyPressed(Keyboard.KEY_NUMPAD4))	{
					win = game.setValue(4);
				} else if (input.isKeyPressed(Keyboard.KEY_5) || input.isKeyPressed(Keyboard.KEY_NUMPAD5))	{
					win = game.setValue(5);
				} else if (input.isKeyPressed(Keyboard.KEY_6) || input.isKeyPressed(Keyboard.KEY_NUMPAD6))	{
					win = game.setValue(6);
				} else if (input.isKeyPressed(Keyboard.KEY_7) || input.isKeyPressed(Keyboard.KEY_NUMPAD7))	{
					win = game.setValue(7);;
				} else if (input.isKeyPressed(Keyboard.KEY_8) || input.isKeyPressed(Keyboard.KEY_NUMPAD8))	{
					win = game.setValue(8);
				} else if (input.isKeyPressed(Keyboard.KEY_9) || input.isKeyPressed(Keyboard.KEY_NUMPAD9))	{
					win = game.setValue(9);
				}
				
				if (win)	{
					s = new EndGameScene(game);
				}
			}
			
			if (game.getSelectedBox() != null)	{
				// [WARNING] Slick2D Inversion des axes
				int y = (game.getSelectedBox().x-DVF.GRID_POSITION_START[0])/DVF.LARGE_CASE;
				int x = (game.getSelectedBox().y-DVF.GRID_POSITION_START[1])/DVF.LARGE_CASE;
			
				if (input.isKeyPressed(Keyboard.KEY_UP))	{
					
					x = x-1;
					if (x < 0)	{x = game.getGrid().length-1;}
					game.setBox(x, y);
					
				} else if (input.isKeyPressed(Keyboard.KEY_DOWN))	{
					
					x = x+1;
					if (x >= game.getGrid().length)	{x = 0;}
					game.setBox(x, y);
					
				} else if (input.isKeyPressed(Keyboard.KEY_LEFT))	{
					
					y = y-1;
					if (y < 0)	{y = game.getGrid().length-1;}
					game.setBox(x, y);
					
				} else if (input.isKeyPressed(Keyboard.KEY_RIGHT))	{
					
					y = y+1;
					if (y >= game.getGrid().length)	{y = 0;}
					game.setBox(x, y);
				}
			}
			
			if (input.isKeyDown(Keyboard.KEY_M) && input.isKeyDown(Keyboard.KEY_S) && input.isKeyPressed(Keyboard.KEY_D))	{
				s = new EndGameScene(game);
			}
		}
		
		if (input.isKeyPressed(Keyboard.KEY_ESCAPE))	{
			gc.exit();
		} else if (input.isKeyPressed(Keyboard.KEY_SPACE))	{
			if (gc.isPaused())	{
				gc.setPaused(false);
			} else {
				gc.setPaused(true);
			}
			game.pause();
		}
		
		input.clearKeyPressedRecord();
		input.clearControlPressedRecord();
		input.clearMousePressedRecord();
		
		return s;
				
	}
}
