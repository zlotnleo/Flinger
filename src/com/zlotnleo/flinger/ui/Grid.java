package com.zlotnleo.flinger.ui;

import com.zlotnleo.flinger.logic.Coord;
import com.zlotnleo.flinger.logic.Field;
import com.zlotnleo.flinger.logic.Move;

import java.awt.*;

public class Grid
{
    private int x0, y0, gridNum, cellSize;

    public Grid(int winWidth, int winHeight)
    {
        gridNum = Field.MAX_SIZE;
        cellSize = (winWidth-140 <= winHeight-80 ? winWidth-140 : winHeight-80);
        cellSize/= gridNum;
        x0 = (winWidth - gridNum*cellSize)/2;
        y0 = (winHeight - gridNum*cellSize)/2;
    }

    public void draw(Graphics g)
    {
        int i;
        for(i=0;i<=gridNum;i++)
        {
            g.drawLine(x0, y0+i*cellSize, x0 + gridNum*cellSize, y0+i*cellSize);
            g.drawLine(x0+i*cellSize, y0, x0+i*cellSize, y0+gridNum*cellSize);
        }
    }

    public void drawCircle(Graphics g, int x, int y)
    {
        g.drawArc(x0 + x*cellSize + cellSize/8, y0 + y*cellSize + cellSize/8, 3*cellSize/4, 3*cellSize/4, 0, 360);
    }

    public void drawArrow(Graphics g, int x, int y, Move.Direction dir)
    {
        switch (dir)
        {
            case RIGHT:
                g.drawLine(x0 + x*cellSize + cellSize/4, y0 + y*cellSize + cellSize/2, x0 + x*cellSize + 3*cellSize/4, y0 + y*cellSize + cellSize/2);
                g.drawLine(x0 + x*cellSize + 3*cellSize/4, y0 + y*cellSize + cellSize/2, x0 + x*cellSize + cellSize/2, y0 + y*cellSize + cellSize/4);
                g.drawLine(x0 + x*cellSize + 3*cellSize/4, y0 + y*cellSize + cellSize/2, x0 + x*cellSize + cellSize/2, y0 + y*cellSize + 3*cellSize/4);
                break;
            case LEFT:
                g.drawLine(x0 + x*cellSize + cellSize/4, y0 + y*cellSize + cellSize/2, x0 + x*cellSize + 3*cellSize/4, y0 + y*cellSize + cellSize/2);
                g.drawLine(x0 + x*cellSize + cellSize/4, y0 + y*cellSize + cellSize/2, x0 + x*cellSize + cellSize/2, y0 + y*cellSize + cellSize/4);
                g.drawLine(x0 + x*cellSize + cellSize/4, y0 + y*cellSize + cellSize/2, x0 + x*cellSize + cellSize/2, y0 + y*cellSize + 3*cellSize/4);
                break;
            case DOWN:
                g.drawLine(x0 + x*cellSize + cellSize/2, y0 + y*cellSize + cellSize/4, x0 + x*cellSize + cellSize/2, y0 + y*cellSize + 3*cellSize/4);
                g.drawLine(x0 + x*cellSize + cellSize/2, y0 + y*cellSize + 3*cellSize/4, x0 + x*cellSize + cellSize/4, y0 + y*cellSize+cellSize/2);
                g.drawLine(x0 + x*cellSize + cellSize/2, y0 + y*cellSize + 3*cellSize/4, x0 + x*cellSize + 3*cellSize/4, y0 + y*cellSize+cellSize/2);
                break;
            case UP:
                g.drawLine(x0 + x*cellSize + cellSize/2, y0 + y*cellSize + cellSize/4, x0 + x*cellSize + cellSize/2, y0 + y*cellSize + 3*cellSize/4);
                g.drawLine(x0 + x*cellSize + cellSize/2, y0 + y*cellSize + cellSize/4, x0 + x*cellSize + cellSize/4, y0 + y*cellSize+cellSize/2);
                g.drawLine(x0 + x*cellSize + cellSize/2, y0 + y*cellSize + cellSize/4, x0 + x*cellSize + 3*cellSize/4, y0 + y*cellSize+cellSize/2);
                break;
        }
    }

    public Coord getCell(int x, int y)
    {
        return new Coord((x-x0)/cellSize, (y-y0)/cellSize);
    }
}
