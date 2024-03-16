package CALab;

import mvc.AppFactory;
import mvc.Command;
import mvc.Model;
import mvc.View;

import java.util.Random;

public abstract class GridFactory implements AppFactory {
    @Override
    public abstract Model makeModel();

    @Override
    public abstract View makeView(Model m);

    @Override
    public abstract String getTitle();

    @Override
    public abstract String about();

    public String[] getEditCommands() {
        return new String[]{"Run1", "Run50", "Populate", "Clear"};
    }

    public Command makeEditCommand(Model model, String type, Object source) {
        Random r = new Random();
        switch (type) {
            case "Run1":
                return new RunCommand(model, 1);
            case "Run50":
                return new RunCommand(model, 50);
            case "Populate":
                return new PopulateCommand(model, r.nextBoolean());
            case "Clear":
                return new ClearCommand(model, r.nextBoolean());
        }
        return null;
    }

    public String[] getHelp() {
        return new String[]{"Run1: Runs the model one time\n", "Run50: Runs the model fifty times\n",
                "Populate: Populates the model\n", "Clear: Clears the model\n"};
    }
}
