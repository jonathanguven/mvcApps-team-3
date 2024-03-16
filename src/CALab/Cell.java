package CALab;

import mvc.Publisher;

import java.awt.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

abstract class Cell extends Publisher implements Serializable {

    protected int row = 0, col = 0;
    protected Set<Cell> neighbors = new HashSet<>();
    protected Grid myGrid = null;
    protected Cell partner = null;
    protected int color;


    // choose a random neighbor as a partner
    public void choosePartner() {
        if (partner == null && neighbors != null) {
			/*
			Set partner to null
			Convert neighbors set to a local array
			Starting at a random position in the array search for a neighbor without a partner
			Make the first such neighbor (if any) the partner and set its partner field to this
			*/
        }

    }

    public void unpartner() {
        if (partner != null) {
            if (partner.partner != null) {
                partner.partner = null;
            }
            partner = null;
        }
    }

    // observer neighbors' states
    public abstract void observe();

    // interact with a random neighbor
    public abstract void interact();

    // update my state
    public abstract void update();

    // set status to status + 1 mod whatever
    public abstract void nextState();

    // set status to a random or initial value
    public abstract void reset(boolean randomly);

    public abstract int getStatus();

    public Color getColor() {
        if (color == 0) {
            return Color.RED;
        }
        else {
            return Color.GREEN;
        }
    }
}
