package CALab;

import mvc.AppFactory;
import mvc.AppPanel;
import stopLight.StoplightFactory;
import stopLight.StoplightPanel;

import javax.swing.*;

public class GridPanel extends AppPanel {

    public GridPanel (AppFactory factory) {
        super(factory);
        JButton run1 = new JButton("Run1");
        run1.addActionListener(this);
        JButton run50 = new JButton("Run50");
        run50.addActionListener(this);
        controls.add(run1);
        controls.add(run50);
    }

    public static void main (String[] args) {
        AppFactory factory = new StoplightFactory();
        AppPanel panel = new StoplightPanel(factory);
        panel.display();
    }
}
