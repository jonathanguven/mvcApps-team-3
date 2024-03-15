package CALab;

import mvc.AppFactory;
import mvc.AppPanel;
import stopLight.StoplightFactory;
import stopLight.StoplightPanel;

import javax.swing.*;

public class GridPanel extends AppPanel {
    private JButton run1;
    private JButton run50;
    private JButton clear;
    private JButton populate;

    private JPanel r1, r50, populatePanel, clearPanel;

    public GridPanel(AppFactory factory) {
        super(factory);

        r1 = new JPanel();
        r50 = new JPanel();
        populatePanel = new JPanel();
        clearPanel = new JPanel();

        run1 = new JButton("Run1");
        run1.addActionListener(this);
        r1.add(run1);

        run50 = new JButton("Run50");
        run50.addActionListener(this);
        r50.add(run50);

        clear = new JButton("Clear");
        clear.addActionListener(this);
        clearPanel.add(clear);

        populate = new JButton("Populate");
        populate.addActionListener(this);
        populatePanel.add(populate);

        controls.add(run1);
        controls.add(run50);
        controls.add(clear);
        controls.add(populate);
    }

    public static void main(String[] args) {
        AppFactory factory = new StoplightFactory();
        AppPanel panel = new StoplightPanel(factory);
        panel.display();
    }
}
