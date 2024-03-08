//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package mvc;

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

public class AppPanel extends JPanel implements ActionListener {
    private Model model;
    public ControlPanel controls = new ControlPanel();
    private final View view;
    private AppFactory factory;

    public AppPanel(AppFactory newFactory) {
        this.factory = newFactory;
        this.model = this.factory.makeModel();
        this.view = this.factory.makeView(this.model);
        this.setLayout(new GridLayout(1, 3));
        this.add(this.controls);
        this.add(this.view);
    }

    protected JMenuBar createMenuBar() {
        JMenuBar result = new JMenuBar();
        JMenu fileMenu = Utilities.makeMenu("File", new String[]{"New", "Save", "Open", "Quit"}, this);
        result.add(fileMenu);
        JMenu editMenu = Utilities.makeMenu("Edit", this.factory.getEditCommands(), this);
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
                case "Save":
                    fName = Utilities.getFileName((String)null, false);
                    ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(fName));
                    os.writeObject(this.model);
                    os.close();
                    break;
                case "Open":
                    if (Utilities.confirm("Are you sure? Unsaved changes will be lost!")) {
                        fName = Utilities.getFileName((String)null, true);
                        ObjectInputStream is = new ObjectInputStream(new FileInputStream(fName));
                        this.model = (Model)is.readObject();
                        this.view.setModel(this.model);
                        is.close();
                    }
                    break;
                case "About":
                    Utilities.inform(this.factory.about());
                    break;
                case "Help":
                    Utilities.inform(this.factory.getHelp());
                    break;
                case "New":
                    this.model = this.factory.makeModel();
                    this.view.setModel(this.model);
                    break;
                case "Quit":
                    System.exit(0);
                    break;
                default:
                    Command c = this.factory.makeEditCommand(this.model, cmd, e.getSource());
                    c.execute();
            }
        } catch (Exception var7) {
            Utilities.error(var7);
        }

    }

    public void display() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(3);
        Container cp = frame.getContentPane();
        cp.add(this);
        frame.setJMenuBar(this.createMenuBar());
        frame.setTitle(this.factory.getTitle());
        frame.setSize(500, 300);
        frame.setVisible(true);
    }

    public class ControlPanel extends JPanel {
        public JPanel buttons;

        public ControlPanel() {
            this.setBackground(Color.PINK);
            this.buttons = new JPanel();
        }

        public void add(JButton newButtons) {
            this.buttons.add(newButtons);
            this.add(this.buttons);
        }
    }
}
