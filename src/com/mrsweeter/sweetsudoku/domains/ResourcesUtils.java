package com.mrsweeter.sweetsudoku.domains;

import java.awt.Font;
import java.io.InputStream;
import java.util.HashMap;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.util.ResourceLoader;

public class ResourcesUtils {
	
	private static HashMap<String, Image> images = new HashMap<>();
	private static HashMap<String, TrueTypeFont> fonts = new HashMap<>();
	
	public static void loadResources()	{
		
		try {
			// GENERAL
			images.put("BG", new Image("resources/images/background.png"));
			// HOME SCENE
			images.put("L", new Image("resources/images/logo.png"));
			images.put("PB", new Image("resources/images/playButton.png"));
			images.put("PBE", new Image("resources/images/playButtonEnable.png"));
			// GAME SCENE
			images.put("SB", new Image("resources/images/selected.png"));
			images.put("CV1", new Image("resources/images/1.png"));
			images.put("CV2", new Image("resources/images/2.png"));
			images.put("CV3", new Image("resources/images/3.png"));
			images.put("CV4", new Image("resources/images/4.png"));
			images.put("CV5", new Image("resources/images/5.png"));
			images.put("CV6", new Image("resources/images/6.png"));
			images.put("CV7", new Image("resources/images/7.png"));
			images.put("CV8", new Image("resources/images/8.png"));
			images.put("CV9", new Image("resources/images/9.png"));
			// END SCENE
			images.put("HB", new Image("resources/images/homeButton.png"));
			images.put("HBE", new Image("resources/images/homeButtonEnable.png"));
			images.put("NGB", new Image("resources/images/newGameButton.png"));
			images.put("NGBE", new Image("resources/images/newGameButtonEnable.png"));
			
			fonts.put("R", getFontFromResource("resources/fonts/roman.ttf", 60));
			fonts.put("MS", getFontFromResource("resources/fonts/montserrat.ttf", 20));
			
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
	public static TrueTypeFont getFont(String id)	{
		if (fonts.get(id) == null)	{throw new NullPointerException("This id is missing");}
		return fonts.get(id);
	}
	
	public static Image getImage(String id)	{
		if (images.get(id) == null)	{throw new NullPointerException("This id is missing");}
		return images.get(id);
	}
	
	// https://stackoverflow.com/questions/11504233/changing-default-font-in-slick-graphics-api
	private static TrueTypeFont getFontFromResource(String path, int size)	{
		
		Font font = new Font(path, Font.BOLD, size);
		TrueTypeFont ttf = new TrueTypeFont(font, false);
		
		try {
	        InputStream inputStream = ResourceLoader.getResourceAsStream(path);

	        Font awtFont2 = Font.createFont(Font.TRUETYPE_FONT, inputStream);
	        awtFont2 = awtFont2.deriveFont((float) size); // set font size
	        ttf = new TrueTypeFont(awtFont2, false);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
		return ttf;
	}
}
