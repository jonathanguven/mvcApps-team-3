package CALab;

import mvc.Command;
import mvc.Model;

public class PopulateCommand extends Command {
    public PopulateCommand(Model m, boolean random) {
        super(m);
    }

    @Override
    public void execute() {
        Grid grid = (Grid) model;
        grid.repopulate(true);
    }
}
