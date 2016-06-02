package com.zlotnleo.flinger.logic;

import java.util.ArrayList;

public class Solver
{
    private boolean solutionFound;
    ArrayList<Move> solution;

    private boolean func(Field field)
    {
        if(solutionFound)
            return false;

        if(field.getBallCount() == 1)
        {
            solutionFound = true;
            return true;
        }

        ArrayList<Move> moves = field.getPossibleMoves();
        int count = moves.size();
        int i;
        Field f;
        Move m;
        for(i=0;i<count;i++)
        {
            f = new Field(field);
            m = moves.get(i);
            f.MakeMove(m);
            if(func(f))
            {
                solution.add(m);
                return true;
            }
        }
        return false;
    }

    public Solver()
    {
        solutionFound = false;
        solution = new ArrayList<>();
    }

    public ArrayList<Move> Solve(Field field)
    {
        func(field);
        int size = solution.size();
        ArrayList<Move> result = new ArrayList<>();
        for(int i=size-1;i>=0;i--)
            result.add(solution.get(i));

        return result;
    }
}
