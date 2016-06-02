package com.zlotnleo.flinger.logic;

public class Coord
{
    public int x;
    public int y;

    public Coord(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public String toString()
    {
        return "("+x+";"+y+")";
    }
}
