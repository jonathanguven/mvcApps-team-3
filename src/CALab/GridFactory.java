package CALab;

import mvc.AppFactory;
import mvc.Command;
import mvc.Model;
import mvc.View;

public class GridFactory implements AppFactory
{
    @Override
    public Model makeModel()
    {
        return new Grid();
    }

    @Override
    public View makeView(Model m)
    {
        return new GridView((Grid) m);
    }

    @Override
    public String getTitle()
    {
        return null;
    }

    @Override
    public String[] getHelp()
    {
        return new String[0];
    }

    @Override
    public String about()
    {
        return null;
    }

    @Override
    public String[] getEditCommands()
    {
        return new String[0];
    }

    @Override
    public Command makeEditCommand(Model model, String name, Object object)
    {
        return null;
    }
}
