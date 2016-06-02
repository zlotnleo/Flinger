package com.zlotnleo.flinger.ui;

import com.zlotnleo.flinger.logic.Coord;
import com.zlotnleo.flinger.logic.Field;
import com.zlotnleo.flinger.logic.Move;
import com.zlotnleo.flinger.logic.Solver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public final class Window extends Frame implements ActionListener, MouseListener, WindowListener
{
    public static final int winWidth = 640;
    public static final int winHeight = 480;

    private Grid grid;

    private byte mode;
    private final static byte EDITING = 0;
    private final static byte SOLVING = 1;

    ArrayList<Move> solution;
    int stepsDone;
    int allSteps;

    Field f;
    Field initial;

    Button btnQuit;
    Button btnSolveNext;
    Button btnClearBack;
    Button btnEditNew;

    public Window()
    {
        grid = new Grid(winWidth, winHeight);
        mode = EDITING;
        f = new Field();
        stepsDone = 0;

        addMouseListener(this);

        setLayout(null);
        setSize(winWidth, winHeight);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((screenSize.width - winWidth) / 2, (screenSize.height - winHeight) / 2);
        setVisible(true);
        setResizable(false);
        setTitle("Flinger");

        btnSolveNext = new Button("Solve");
        btnSolveNext.setSize(50, 50);
        btnSolveNext.setLocation(10, 40);
        btnSolveNext.addActionListener(this);
        add(btnSolveNext);

        btnClearBack = new Button("Clear");
        btnClearBack.setSize(50, 50);
        btnClearBack.setLocation(10, 110);
        btnClearBack.addActionListener(this);
        add(btnClearBack);

        btnQuit = new Button("Quit");
        btnQuit.setSize(50, 50);
        btnQuit.setLocation(winWidth - 60, 40);
        btnQuit.addActionListener(this);
        add(btnQuit);

        btnEditNew = new Button("Edit");
        btnEditNew.setSize(50, 50);
        btnEditNew.setLocation(winWidth - 60, 110);
        btnEditNew.addActionListener(this);
        add(btnEditNew);
        btnEditNew.setVisible(false);
    }

    public void paint(Graphics g)
    {
        repaint();
        grid.draw(g);
        ArrayList<Coord> balls = f.getBalls();
        int i;
        int count = balls.size();
        Coord tmp;
        for(i=0;i<count;i++)
        {
            tmp = balls.get(i);
            grid.drawCircle(g, tmp.x, tmp.y);
        }
        if(solution != null && stepsDone < allSteps && mode == SOLVING)
        {
            Move move = solution.get(stepsDone);
            grid.drawArrow(g, move.x, move.y, move.dir);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        switch (e.getActionCommand())
        {
            case "Solve":
                solution = new Solver().Solve(f);
                allSteps = solution.size();
                initial = new Field(f);
                stepsDone = 0;
                for(int i=0;i<solution.size();i++)
                    f.MakeMove(solution.get(i));
                if(f.getBallCount() > 1)
                {
                    JOptionPane.showMessageDialog(null, "No solution found");
                }
                else if(f.getBallCount()<1)
                {
                    JOptionPane.showMessageDialog(null, "No furballs found");
                }
                else
                {
                    f = new Field(initial);

                    mode = SOLVING;
                    btnSolveNext.setLabel("Next");
                    btnClearBack.setLabel("Back");
                    btnEditNew.setLabel("Edit");
                    btnEditNew.setVisible(true);
                }
                break;

            case "Next":
                if(stepsDone < allSteps)
                {
                    f.MakeMove(solution.get(stepsDone));
                    stepsDone++;
                }
                if(stepsDone == allSteps)
                    btnEditNew.setLabel("New");
                break;

            case "Clear":
                f = new Field();
                break;

            case "Back":
                if(stepsDone > 0)
                {
                    stepsDone--;
                    f = new Field(initial);
                    for (int i = 0; i < stepsDone; i++)
                        f.MakeMove(solution.get(i));
                    btnEditNew.setLabel("Edit");
                }
                break;

            case "New":
                mode = EDITING;
                f = new Field();
                btnEditNew.setVisible(false);
                btnSolveNext.setLabel("Solve");
                btnClearBack.setLabel("Clear");
                break;

            case "Edit":
                mode = EDITING;
                f = new Field(initial);
                btnEditNew.setVisible(false);
                btnSolveNext.setLabel("Solve");
                btnClearBack.setLabel("Clear");
                break;

            case "Quit":
                dispose();
                break;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e)
    {
        if(mode == EDITING)
        {
            Coord tmp = grid.getCell(e.getX(), e.getY());
            if(f.isBallAt(tmp.x, tmp.y))
                f.RemoveBall(tmp.x, tmp.y);
            else
                f.SetBall(tmp.x, tmp.y);
        }
    }

    @Override
    public void mousePressed(MouseEvent e)
    {

    }

    @Override
    public void mouseReleased(MouseEvent e)
    {

    }

    @Override
    public void mouseEntered(MouseEvent e)
    {

    }

    @Override
    public void mouseExited(MouseEvent e)
    {

    }

    @Override
    public void windowOpened(WindowEvent e)
    {

    }

    @Override
    public void windowClosing(WindowEvent e)
    {
        dispose();
    }

    @Override
    public void windowClosed(WindowEvent e)
    {

    }

    @Override
    public void windowIconified(WindowEvent e)
    {

    }

    @Override
    public void windowDeiconified(WindowEvent e)
    {

    }

    @Override
    public void windowActivated(WindowEvent e)
    {

    }

    @Override
    public void windowDeactivated(WindowEvent e)
    {

    }
}
