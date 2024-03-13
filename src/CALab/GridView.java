package CALab;

import mvc.Model;
import mvc.View;

public class GridView extends View {

    private CellView cellViews[][];

    public GridView (Model model) {
        /*
        Cell cell = new CellView(((Grid)model).getCell(row, col)
        cellViews[row][col] = cell
        set cell.row and cell.col here
        */
    }

    public void update () {
        // call update method of each CellView
    }

}