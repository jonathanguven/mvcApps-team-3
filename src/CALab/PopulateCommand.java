package CALab;

import mvc.Command;
import mvc.Model;

public class PopulateCommand extends Command {
    private final boolean random;

    public PopulateCommand(Model m, boolean random) {
        super(m);
        this.random = random;
    }

    @Override
    public void execute() {
        Grid grid = (Grid) model;
        grid.repopulate(random);
    }
}
