package edu.school21.game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

@Parameters(separators = "=")
public class Program {

	public static final int FORWARD = 0;
	public static final int BACKWARD = 1;
	public static final int LEFT = 2;
	public static final int RIGHT = 3;
	public static final int NOWHERE = -1;

	public static final int ENEMY = 0;
	public static final int PLAYER = 1;
	public static final int WALL = 2;
	public static final int PORTAL = 3;
	public static final int EMPTY = 4;

	public static final int USER_MODE = 0;
	public static final int DEV_MODE = 1;

	@Parameter(
			names = "--enemiesCount",
			description = "enemies Count",
			required = true
	)
	int enemiesCount;
	@Parameter(
			names = "--wallsCount",
			description = "walls Count",
			required = true
	)
	int wallsCount;

	@Parameter(
			names = "--size",
			description = "size",
			required = true
	)
	int size;

	@Parameter(
			names = "--profile",
			description = "profile",
			required = true
	)
	String profile;

	public static void main(String[] args) {

		Program param = new Program();
		JCommander.newBuilder().addObject(param).build().parse(args);	
		try {
			if (!Game.checkValid(param.enemiesCount, param.wallsCount, param.size))
				throw new IllegalParametersException("Wrong arg!");
		} catch (IllegalParametersException e) {
			System.err.println(e.getMessage());
			System.exit(-1);
		}

		OutputData outputData = new OutputData();

		Game game = new Game(param.enemiesCount, param.wallsCount, param.size, param.profile);

		Player player = null;
		List<Enemy> enemiesList = null;
		while (true) {
			game.generateGameMap();

			enemiesList = new ArrayList<>();
			for (int y = 0; y < game.getGameMap().length; y++) {
				for (int x = 0; x < game.getGameMap().length; x++) {
					if (game.getGameMap()[y][x] == ENEMY) {
						enemiesList.add(new Enemy(game.getGameMap(), x, y, USER_MODE));
					} else if (game.getGameMap()[y][x] == PLAYER) {
						player = new Player(game.getGameMap(), x, y, USER_MODE);
					}
				}
			}
			if (player.ifAble())
				break;
		}

		int[][] mapArray = game.getGameMap();

		Scanner scanner = new Scanner(System.in);

		while (true) {
			redrawMap(game.getMode(), game);

			if (!player.ifValidMove(mapArray, player.getCurPosX(), player.getCurPosY(), FORWARD) &&
					!player.ifValidMove(mapArray, player.getCurPosX(), player.getCurPosY(), BACKWARD) &&
					!player.ifValidMove(mapArray, player.getCurPosX(), player.getCurPosY(), LEFT) &&
					!player.ifValidMove(mapArray, player.getCurPosX(), player.getCurPosY(), RIGHT)) {
				System.out.println("You lose");
				System.exit(0);
			}
			String s = scanner.nextLine();
			if (s.length() != 1)
				continue;
			char c = s.charAt(0);
			int dir = 0;
			if (c == 'W') {
				dir = FORWARD;
			}
			else if (c == 'S') {
				dir = BACKWARD;
			}
			else if (c == 'A') {
				dir = LEFT;
			}
			else if (c == 'D') {
				dir = RIGHT;
			} else if (c == '9') {
				System.out.println("You surrendered!");
				System.exit(0);
			}
			else {
				continue;
			}
			if (player.move(dir) == -1) {
				continue;
			}
			for (Enemy e: enemiesList) {
				if (game.getMode() != DEV_MODE)
					e.move();
				else  {
					System.out.println("Do you confirm enemy move?");
					String s2 = scanner.nextLine();
					if (s2.length() == 1){
						char c2 = s2.charAt(0);
						if (c2 == '8')
							e.move();
					}
				}
			}
		}
	}

	public static void redrawMap(int mode, Game gameMap) {
		if (mode == USER_MODE) {
			clearConsole();
		}
		gameMap.printGameMap();
	}

	public static void clearConsole() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
		try {
			Runtime.getRuntime().exec("clear");
		
		} catch (final Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
