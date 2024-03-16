package CALab;

import mvc.Command;
import mvc.Model;

public class ClearCommand extends Command {
    private final boolean random;

    public ClearCommand(Model m, boolean random) {
        super(m);
        this.random = random;
    }

    @Override
    public void execute() {
        Grid grid = (Grid) model;
        grid.repopulate(random);
    }
}
