package CALab;

import mvc.AppFactory;
import mvc.AppPanel;
import stopLight.StoplightFactory;
import stopLight.StoplightPanel;

import javax.swing.*;

public class GridPanel extends AppPanel
{
    private JButton run1;
    private JButton run50;
    private JButton clear;
    private JButton populate;

    public GridPanel(AppFactory factory)
    {
        super(factory);
        run1 = new JButton("Run1");
        run1.addActionListener(this);
        run50 = new JButton("Run50");
        run50.addActionListener(this);
        clear = new JButton("Clear");
        clear.addActionListener(this);
        populate = new JButton("Populate");
        populate.addActionListener(this);
        
        controls.add(run1);
        controls.add(run50);
        controls.add(clear);
        controls.add(populate);
    }

    public static void main(String[] args)
    {
        AppFactory factory = new StoplightFactory();
        AppPanel panel = new StoplightPanel(factory);
        panel.display();
    }
}
