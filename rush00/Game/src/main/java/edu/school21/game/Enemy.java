package edu.school21.game;

import edu.school21.enemylogic.ChaseLogic;

public class Enemy {

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
    public static final int EDGE = 5;
    public static final int PATH = 6;


    int[][] mapArray;
    private int curPosX;
    private int curPosY;
    private int mapWidth;
    private int mapHeight;
    int[] newPosition;

    public Enemy(int[][] mapArray, int curPosX, int curPosY, int mode) {
        this.mapArray = mapArray;
        this.curPosX = curPosX;
        this.curPosY = curPosY;
        this.mapWidth = mapArray[0].length;
        this.mapHeight = mapArray.length;
        this.newPosition = new int[2];
    }

    public int[] getItemPosition(int item) {
        int[] pos = new int[2];

        for (int y = 0; y < mapHeight; y++) {
            for (int x = 0; x < mapWidth; x++) {
                if (mapArray[y][x] == item) {
                    pos[0] = x;
                    pos[1] = y;
                    return pos;
                }
            }
        }
        return null;
    }

    public int nextMove() {
        return ChaseLogic.nextMove(mapArray, curPosX, curPosY, PLAYER);
    }

    public int[] getNewPosition(int pX, int pY, int direction) {
        int newpX = -1;
        int newpY = -1;

        if (direction == FORWARD) {
            newpX = pX;
            newpY = pY - 1;
        }
        else if (direction == BACKWARD) {
            newpX = pX;
            newpY = pY + 1;
        }
        else if (direction == LEFT) {
            newpX = pX - 1;
            newpY = pY;
        }
        else if (direction == RIGHT) {
            newpX = pX + 1;
            newpY = pY;
        }
        newPosition[0] = newpX;
        newPosition[1] = newpY;
        return newPosition;
    }

    public boolean isValidMove(int[][] gameMap, int pX, int pY, int direction) {
        int newpX;
        int newpY;

        if (direction == FORWARD && (pY - 1 >= 0)) {
            newpX = pX;
            newpY = pY - 1;
        }
        else if (direction == BACKWARD && (pY + 1 <= mapHeight - 1)) {
            newpX = pX;
            newpY = pY + 1;
        }
        else if (direction == LEFT && pX - 1 >= 0) {
            newpX = pX - 1;
            newpY = pY;
        }
        else if (direction == RIGHT && pX + 1 <= mapWidth - 1) {
            newpX = pX + 1;
            newpY = pY;
        }
        else {
            return false;
        }
        return (gameMap[newpY][newpX] == EMPTY || gameMap[newpY][newpX] == PLAYER);
    }

    public void move() {
        int nextMoveDirection = nextMove();
        int[] newPos = getNewPosition(curPosX, curPosY, nextMoveDirection);
        if (nextMoveDirection != NOWHERE) {
            mapArray[curPosY][curPosX] = EMPTY;
            if (mapArray[newPos[1]][newPos[0]] == PLAYER) {
                System.out.println("You lose :(");
                System.exit(0);
            }
            curPosX = newPos[0];
            curPosY = newPos[1];
            mapArray[newPos[1]][newPos[0]] = ENEMY;
        }
	}
}
