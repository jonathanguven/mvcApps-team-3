package Life;

import CALab.Cell;

import java.awt.*;

public class Agent extends Cell {
    public int status = 0;
    public int ambience = 8;

    @Override
    public void observe() {
        ambience = this.neighbors.size();
    }

    @Override
    public void interact() {}

    @Override
    public void update() {
        nextState();
    }

    @Override
    public void nextState() {
        if (status == 0) {
            status = 1;
        }
        else {
            status = 0;
        }
    }

    @Override
    public void reset(boolean randomly) {
        if (randomly) {
            status = 1;
        }
        else {
            status = 0;
        }
    }

    @Override
    public int getStatus() {
        return status;
    }

    @Override
    public Color getColor() {
        if (getStatus() == 0) {
            return Color.RED;
        }
        else {
            return Color.GREEN;
        }
    }
}
