package com.zlotnleo.flinger.logic;

public class Move
{
    /*public static final byte UP = 2;
    public static final byte DOWN = 3;
    public static final byte LEFT = 4;
    public static final byte RIGHT = 5;*/

    public enum Direction
    {
        RIGHT, LEFT, UP, DOWN
    }

    public int x;
    public int y;
    public Direction dir;

    public Move(int x, int y, Direction dir)
    {
        this.x = x;
        this.y = y;
        this.dir = dir;
    }

    public String toString()
    {
        String strDir = "";
        switch (dir)
        {
            case RIGHT:
                strDir = "right";
                break;
            case LEFT:
                strDir = "left";
                break;
            case DOWN:
                strDir = "down";
                break;
            case UP:
                strDir = "up";
                break;
        }
        return "Ball at " + new Coord(x, y) + " going " + strDir;
    }
}
