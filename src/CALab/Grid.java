package CALab;

import mvc.Model;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;

public abstract class Grid extends Model {
    static private int time = 0;
    protected int dim = 20;
    protected Cell[][] cells;

    public Grid(int dim) {
        this.dim = dim;
        cells = new Cell[dim][dim];
        populate();
    }

    public Grid() {
        this(20);
    }

    public int getDim() {
        return dim;
    }

    public int getTime() {
        return time;
    }

    public Cell getCell(int row, int col) {
        return cells[row][col];
    }

    public abstract Cell makeCell(boolean uniform);

    protected void populate() {
        // 1. use makeCell to fill in cells
        // 2. use getNeighbors to set the neighbors field of each cell
        for (int row = 0; row < dim; row++) {
            for (int col = 0; col < dim; col++) {
                cells[row][col] = makeCell(true);
            }
        }
        changed();
        for (int row = 0; row < dim; row++) {
            for (int col = 0; col < dim; col++) {
                cells[row][col].neighbors = getNeighbors(cells[row][col], 1);
            }
        }
    }

    // called when Populate button is clicked
    public void repopulate(boolean randomly) {
        for (int row = 0; row < dim; row++) {
            for (int col = 0; col < dim; col++) {
                cells[row][col].reset(randomly);
            }
        }

        // notify subscribers
        changed();
    }


    public Set<Cell> getNeighbors(Cell asker, int radius) {
        /*
        return the set of all cells that can be reached from the asker in radius steps.
        If radius = 1 this is just the 8 cells touching the asker.
        Tricky part: cells in row/col 0 or dim - 1.
        The asker is not a neighbor of itself.
        */
        Set<Cell> reachable = new HashSet<>();
        for (int row = Math.max(0, asker.row - radius); row <= Math.min(dim - 1, asker.row + radius); row++) {
            for (int col = Math.max(0, asker.col - radius); col <= Math.min(dim - 1, asker.col + radius); col++) {
                if (Math.abs(row - asker.row) + Math.abs(col - asker.col) <= radius) {
                    reachable.add(cells[row][col]);
                }
            }
        }
        return reachable;
    }

    // overide these
    public int getStatus() {
        return 0;
    }

    public Color getColor() {
        return Color.GREEN;
    }

    // cell phases:

    public void observe() {
        // call each cell's observe method and notify subscribers
        for (int row = 0; row < dim; row++) {
            for (int col = 0; col < dim; col++) {
                cells[row][col].observe();
            }
        }
        changed();
    }

    public void interact() {
        for (int row = 0; row < dim; row++) {
            for (int col = 0; col < dim; col++) {
                cells[row][col].interact();
            }
        }
        changed();
    }

    public void update() {
        for (int row = 0; row < dim; row++) {
            for (int col = 0; col < dim; col++) {
                cells[row][col].update();
            }
        }
        changed();
    }

    public void updateLoop(int cycles) {
        observe();
        for (int cycle = 0; cycle < cycles; cycle++) {
            interact();
            update();
            observe();
            time++;
            System.out.println("time = " + time);
        }
    }
}
