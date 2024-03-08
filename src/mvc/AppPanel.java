//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package stoplightSim;

import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import tools.Utilities;

public class AppPanel extends JPanel implements ActionListener {
    private Stoplight light = new Stoplight();
    private final ControlPanel controls;
    private final StoplightView view;

    public AppPanel() {
        this.view = new StoplightView(this.light);
        this.controls = new ControlPanel();
        this.setLayout(new GridLayout(1, 3));
        this.add(this.controls);
        this.add(this.view);
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(3);
        Container cp = frame.getContentPane();
        cp.add(this);
        frame.setJMenuBar(this.createMenuBar());
        frame.setTitle("Stoplight Simulator");
        frame.setSize(500, 300);
        frame.setVisible(true);
    }

    protected JMenuBar createMenuBar() {
        JMenuBar result = new JMenuBar();
        JMenu fileMenu = Utilities.makeMenu("File", new String[]{"New", "Save", "Open", "Quit"}, this);
        result.add(fileMenu);
        JMenu editMenu = Utilities.makeMenu("Edit", new String[]{"Change"}, this);
        result.add(editMenu);
        JMenu helpMenu = Utilities.makeMenu("Help", new String[]{"About", "Help"}, this);
        result.add(helpMenu);
        return result;
    }

    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();

        try {
            String fName;
            switch (cmd) {
                case "Change":
                    this.light.change();
                    break;
                case "Save":
                    fName = Utilities.getFileName((String)null, false);
                    ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(fName));
                    os.writeObject(this.light);
                    os.close();
                    break;
                case "Open":
                    if (Utilities.confirm("Are you sure? Unsaved changes will be lost!")) {
                        fName = Utilities.getFileName((String)null, true);
                        ObjectInputStream is = new ObjectInputStream(new FileInputStream(fName));
                        this.light = (Stoplight)is.readObject();
                        this.view.setLight(this.light);
                        is.close();
                    }
                    break;
                case "New":
                    this.light = new Stoplight();
                    this.view.setLight(this.light);
                    break;
                case "Quit":
                    System.exit(0);
                    break;
                case "About":
                    Utilities.inform("Cyberdellic Designs Stoplight Simulator, 2024. All rights reserved.");
                    break;
                case "Help":
                    String[] cmds = new String[]{"Change: Changes stop light"};
                    Utilities.inform(cmds);
                    break;
                default:
                    throw new Exception("Unrecognized command: " + cmd);
            }
        } catch (Exception var7) {
            Utilities.error(var7);
        }

    }

    public static void main(String[] args) {
        new AppPanel();
    }

    class ControlPanel extends JPanel {
        public ControlPanel() {
            this.setBackground(Color.PINK);
            JPanel p = new JPanel();
            JButton change = new JButton("Change");
            change.addActionListener(AppPanel.this);
            p.add(change);
            this.add(p);
        }
    }
}
