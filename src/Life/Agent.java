package Life;

import CALab.Cell;

import java.awt.*;

public class Agent extends Cell {
    public int status = 0;
    public int ambience = 8;

    @Override
    public void observe() {
        ambience = 0;
        for (Cell a : neighbors) {
            if (a.getStatus() == 1) {
                ambience++;
            }
        }
    }

    @Override
    public void interact() {
    }

    @Override
    public void update() {
        nextState();
        notifySubscribers();
    }

    @Override
    public void nextState() {
        if (status == 0 && ambience == 3) {
            status = 1;
        } else if (status == 1 && (ambience <= 1 || ambience >= 4)) {
            status = 0;
        }
    }

    @Override
    public void reset(boolean randomly) {
        if (randomly) {
            status = (int) (Math.random() * 2);
        } else {
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
        } else {
            return Color.GREEN;
        }
    }
    
    @Override
    public int getAmbience() {
        return ambience;
    }
}
