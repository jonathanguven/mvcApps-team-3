package CALab;

import mvc.Command;
import mvc.Model;

public class RunCommand extends Command {
    public RunCommand (Model m) {
        super(m);
    }

    @Override
    public void execute () {
        Grid grid = (Grid) model;
        // if run 1
        grid.updateLoop(1);
        // if run 50
        grid.updateLoop(50);
    }
}
