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
        for (int i = 0; i < cellViews.length; i++) {
            for (int x = 0; x < cellViews[0].length; i++) {
                cellViews[i][x].update();
            }
        }
    }

}