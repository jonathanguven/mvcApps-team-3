package mvc;

abstract public class Command
{
    public Model model;

    public Command(Model m)
    {
        this.model = m;
    }

    public abstract void execute() throws Exception;
}