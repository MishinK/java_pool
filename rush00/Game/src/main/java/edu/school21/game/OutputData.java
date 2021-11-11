package edu.school21.game;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class OutputData {

	private static char ENEMY_CHAR;
	private static char PLAYER_CHAR;
	private static char WALL_CHAR;
	private static char PORTAL_CHAR;
	private static char EMPTY_CHAR;

	private static String ENEMY_COLOR;
	private static String PLAYER_COLOR;
	private static String WALL_COLOR;
	private static String PORTAL_COLOR;
	private static String EMPTY_COLOR;

	public static char getEnemyChar() {
		return ENEMY_CHAR;
	}

	public static char getPlayerChar() {
		return PLAYER_CHAR;
	}

	public static char getWallChar() {
		return WALL_CHAR;
	}

	public static char getPortalChar() {
		return PORTAL_CHAR;
	}

	public static char getEmptyChar() {
		return EMPTY_CHAR;
	}

	public static String getEnemyColor() {
		return ENEMY_COLOR;
	}

	public static String getPlayerColor() {
		return PLAYER_COLOR;
	}

	public static String getWallColor() {
		return WALL_COLOR;
	}

	public static String getPortalColor() {
		return PORTAL_COLOR;
	}

	public static String getEmptyColor() {
		return EMPTY_COLOR;
	}

	public OutputData() {
		
		try (InputStream input = Program.class.getClassLoader().getResourceAsStream("application-production.properties")) {

			Properties propData = new Properties();

			propData.load(input);
			
			ENEMY_CHAR = valid(propData.getProperty("enemy.char"), 0).charAt(0);
			PLAYER_CHAR = valid(propData.getProperty("player.char"), 0).charAt(0);
			WALL_CHAR = valid(propData.getProperty("wall.char"), 0).charAt(0);
			PORTAL_CHAR = valid(propData.getProperty("goal.char"), 0).charAt(0);
			EMPTY_CHAR = valid(propData.getProperty("empty.char"), 0).charAt(0);
			ENEMY_COLOR = valid(propData.getProperty("enemy.color"), 1);
			PLAYER_COLOR = valid(propData.getProperty("player.color"), 1);
			WALL_COLOR = valid(propData.getProperty("wall.color"), 1);
			PORTAL_COLOR = valid(propData.getProperty("goal.color"), 1);
			EMPTY_COLOR = valid(propData.getProperty("empty.color"), 1);
	
			input.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	private String valid(String property, int is_color) {
		if (property.equals("")) {
			if (is_color == 1)
				return "BLACK";
			else 
				return " ";
		}
		else {
			return property;
		}
	}

}