package com.zlotnleo.flinger.logic;

import java.util.ArrayList;

public class Field
{
    private byte[][] m;
    ArrayList<Coord> balls;

    public static final int MAX_SIZE = 8;
    public static final byte EMPTY = 0;
    public static final byte BALL = 1;

    private void Init()
    {
        m = new byte[MAX_SIZE][MAX_SIZE];
        balls = new ArrayList<>();
        int i, j;
        for(i=0;i<MAX_SIZE;i++)
            for(j=0;j<MAX_SIZE;j++)
                m[i][j]=EMPTY;
    }

    public Field()
    {
        Init();
    }

    public Field(Field prev)
    {
        Init();
        ArrayList<Coord> prevBalls = prev.getBalls();
        int size = prevBalls.size();
        Coord curBall;
        for(int i=0;i<size;i++)
        {
            curBall = prevBalls.get(i);
            SetBall(curBall.x, curBall.y);
        }
    }

    public ArrayList<Move> getPossibleMoves()
    {
        ArrayList<Move> moves = new ArrayList<>();
        int i, j;
        Coord curBall;
        Move move;
        for(i=0;i<balls.size();i++)
        {
            curBall = balls.get(i);

            move = new Move(curBall.x, curBall.y, Move.Direction.RIGHT);
            if(isValidMove(move))
                moves.add(move);

            move = new Move(curBall.x, curBall.y, Move.Direction.LEFT);
            if(isValidMove(move))
                moves.add(move);

            move = new Move(curBall.x, curBall.y, Move.Direction.DOWN);
            if(isValidMove(move))
                moves.add(move);

            move = new Move(curBall.x, curBall.y, Move.Direction.UP);
            if(isValidMove(move))
                moves.add(move);
        }
        return moves;
    }

    public void MakeMove(Move move)
    {
        if(!isValidMove(move))
            return;
        unlockedMove(move);
    }

    private void unlockedMove(Move move)
    {
        int i;
        switch (move.dir)
        {
            case RIGHT:
                for(i=move.x+1;i<MAX_SIZE;i++)
                    if(m[i][move.y] == BALL)
                    {
                        RemoveBall(move.x, move.y);
                        SetBall(i-1, move.y);
                        unlockedMove(new Move(i, move.y, move.dir));
                        break;
                    }
                if(i==MAX_SIZE)
                    RemoveBall(move.x, move.y);
                break;

            case LEFT:
                for(i=move.x-1;i>=0;i--)
                    if(m[i][move.y] == BALL)
                    {
                        RemoveBall(move.x, move.y);
                        SetBall(i+1, move.y);
                        unlockedMove(new Move(i, move.y, move.dir));
                        break;
                    }
                if(i==-1)
                    RemoveBall(move.x, move.y);
                break;

            case DOWN:
                for(i=move.y+1;i<MAX_SIZE;i++)
                    if(m[move.x][i] == BALL)
                    {
                        RemoveBall(move.x, move.y);
                        SetBall(move.x, i-1);
                        unlockedMove(new Move(move.x, i, move.dir));
                        break;
                    }
                if(i==MAX_SIZE)
                    RemoveBall(move.x, move.y);
                break;

            case UP:
                for(i=move.y-1;i>=0;i--)
                    if(m[move.x][i] == BALL)
                    {
                        RemoveBall(move.x, move.y);
                        SetBall(move.x, i+1);
                        unlockedMove(new Move(move.x, i, move.dir));
                        break;
                    }
                if(i==-1)
                    RemoveBall(move.x, move.y);
                break;
        }
    }

    public boolean isValidMove(Move move)
    {
        int i;

        switch (move.dir)
        {
            case RIGHT:
                if (move.x < MAX_SIZE - 2 && m[move.x+1][move.y] != BALL)
                    for (i = move.x + 2; i < MAX_SIZE; i++)
                        if (m[i][move.y] == BALL)
                            return true;
                break;

            case LEFT:
                if (move.x >= 2 && m[move.x-1][move.y] != BALL)
                    for (i = move.x - 2; i >= 0; i--)
                        if (m[i][move.y] == BALL)
                            return true;
                break;

            case DOWN:
                if (move.y < MAX_SIZE - 2 && m[move.x][move.y+1] != BALL)
                    for (i = move.y + 2; i < MAX_SIZE; i++)
                        if (m[move.x][i] == BALL)
                            return true;
                break;

            case UP:
                if (move.y >= 2 && m[move.x][move.y-1] != BALL)
                    for (i = move.y - 2; i >= 0; i--)
                        if (m[move.x][i] == BALL)
                            return true;
                break;
        }
        return false;
    }

    public void SetBall(int x, int y)
    {
        if(x<0 || y<0 || x>=MAX_SIZE || y>=MAX_SIZE || m[x][y]==BALL)
            return;
        m[x][y] = BALL;
        balls.add(new Coord(x, y));
    }

    public void RemoveBall(int x, int y)
    {
        if(m[x][y] != BALL)
            return;
        m[x][y] = EMPTY;
        int i;
        int count = balls.size();
        for(i=0;i<count;i++)
        {
            if(balls.get(i).x == x && balls.get(i).y == y)
            {
                balls.remove(i);
                break;
            }
        }
    }

    public ArrayList<Coord> getBalls()
    {
        return balls;
    }

    public String toString()
    {
        String str = "";
        int i, j;
        for(i=0;i<MAX_SIZE;i++)
        {
            str+="+";
            for (j = 0; j < MAX_SIZE; j++)
                str += "-+";
            str+="\n|";
            for (j = 0; j < MAX_SIZE; j++)
                str += (m[j][i] == BALL ? "O" : " ") + "|";
            str+="\n";
        }
        str+="+";
        for (j = 0; j < MAX_SIZE; j++)
            str += "-+";
        return str;
    }

    public int getBallCount()
    {
        return balls.size();
    }

    public boolean isBallAt(int x, int y)
    {
        return (m[x][y]==BALL);
    }
}
