package edu.school21.game;

import java.util.Arrays;

public class Player {

    public static final int FORWARD = 0;
    public static final int BACKWARD = 1;
    public static final int LEFT = 2;
    public static final int RIGHT = 3;

    public static final int ENEMY = 0;
    public static final int PLAYER = 1;
    public static final int WALL = 2;
    public static final int PORTAL = 3;
    public static final int EMPTY = 4;
    public static final int EDGE = 5;
    public static final int PATH = 6;

    int[][] mapArray;
    int[][] mapWithPathToPortal;
    private int curPosX;
    private int curPosY;
    private int mapWidth;
    private int mapHeight;
    int[] portalPosition;
    int[] newPosition;
    int mode;

    public Player(int[][] mapArray, int curPosX, int curPosY, int mode) {
        this.mapArray = mapArray;
        this.curPosX = curPosX;
        this.curPosY = curPosY;
        this.mapWidth = mapArray[0].length;
        this.mapHeight = mapArray.length;
        this.mapWithPathToPortal = Arrays.stream(mapArray).map(int[]::clone).toArray(int[][]::new);
        this.portalPosition = getItemPosition(PORTAL);
        this.newPosition = new int[2];
        this.mode = mode;
    }

    public boolean ifValidMove(int[][] gameMap, int pX, int pY, int direction) {
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
        return (gameMap[newpY][newpX] == EMPTY || gameMap[newpY][newpX] == PORTAL || gameMap[newpY][newpX] == ENEMY);
    }

    public int move(int direction) {
        if (!ifValidMove(mapArray, curPosX, curPosY, direction)) {
            System.out.println("Try another direction to move");
            return -1;
        }
        else {
            int[] curPos = getItemPosition(PLAYER);
            int[] newPos = getNewPosition(curPosX, curPosY, direction);
            mapArray[curPos[1]][curPos[0]] = EMPTY;
            if (mapArray[newPos[1]][newPos[0]] == PORTAL) {
                System.out.println("You win");
                System.exit(0);
            }
            if (mapArray[newPos[1]][newPos[0]] == ENEMY) {
                System.out.println("You lose!");
                System.exit(0);
            }
            mapArray[newPos[1]][newPos[0]] = PLAYER;
            curPosX = newPos[0];
            curPosY = newPos[1];
        }
        return 0;
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

    public void findMoves(int pX, int pY, int item, int[] variants) {
        int[] itemPos = getItemPosition(item);

        int diffX = pX - itemPos[0];
        int diffY = pY - itemPos[1];

        if (Math.abs(diffX) > Math.abs(diffY)) {
            if (diffX < 0) {
                variants[0] = RIGHT;
                variants[3] = LEFT;
            }
            else {
                variants[0] = LEFT;
                variants[3] = RIGHT;
            }
            if (diffY < 0) {
                variants[1] = BACKWARD;
                variants[2] = FORWARD;
            }
            else {
                variants[2] = BACKWARD;
                variants[1] = FORWARD;
            }
        }
        else {
            if (diffY < 0) {
                variants[0] = BACKWARD;
                variants[3] = FORWARD;
            }
            else {
                variants[0] = FORWARD;
                variants[3] = BACKWARD;
            }
            if (diffX < 0) {
                variants[1] = RIGHT;
                variants[2] = LEFT;
            }
            else {
                variants[1] = LEFT;
                variants[2] = RIGHT;
            }
        }
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

    public boolean ifAble() {
        int[] variants = new int[4];
        return hasMovePortal(curPosX, curPosY, variants);
    }

    public boolean hasMovePortal(int pX, int pY, int[] variants) {
        findMoves(pX, pY, PORTAL, variants);
        int[] newPos;

        if (mapWithPathToPortal[pY][pX] == PORTAL) {
            return true;
        }
        for (int i = 0; i < 4; i++) {
            if (ifValidMove(mapWithPathToPortal, pX, pY, variants[i])) {
                newPos = getNewPosition(pX, pY, variants[i]);
                mapWithPathToPortal[pY][pX] = PATH;
                if (hasMovePortal(newPos[0], newPos[1], variants)) {
                    mapWithPathToPortal[newPos[1]][newPos[0]] = EMPTY;
                    return true;
                }
            }
        }
        return false;
    }

    public int[][] getMapArray() {
        return mapArray;
    }

    public void setMapArray(int[][] mapArray) {
        this.mapArray = mapArray;
    }

    public int[][] getMapWithPathToPortal() {
        return mapWithPathToPortal;
    }

    public void setMapWithPathToPortal(int[][] mapWithPathToPortal) {
        this.mapWithPathToPortal = mapWithPathToPortal;
    }

    public int getCurPosX() {
        return curPosX;
    }

    public void setCurPosX(int curPosX) {
        this.curPosX = curPosX;
    }

    public int getCurPosY() {
        return curPosY;
    }

    public void setCurPosY(int curPosY) {
        this.curPosY = curPosY;
    }

    public int getMapWidth() {
        return mapWidth;
    }

    public void setMapWidth(int mapWidth) {
        this.mapWidth = mapWidth;
    }

    public int getMapHeight() {
        return mapHeight;
    }

    public void setMapHeight(int mapHeight) {
        this.mapHeight = mapHeight;
    }

    public void setPortalPosition(int[] portalPosition) {
        this.portalPosition = portalPosition;
    }
}
