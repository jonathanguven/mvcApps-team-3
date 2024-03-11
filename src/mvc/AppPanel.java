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

public class AppPanel extends JPanel implements Subscriber, ActionListener {
    private Model model;
    public JPanel controls;
    private final View view;
    private AppFactory factory;
    private JFrame frame;
    public static int FRAME_WIDTH = 500;
    public static int FRAME_HEIGHT = 300;

    public AppPanel(AppFactory newFactory) {
        this.factory = newFactory;
        controls = new ControlPanel();
        model = factory.makeModel();
        view = factory.makeView(model);
        setLayout(new GridLayout(1, 3));
        add(controls);
        add(view);
        model.subscribe(this);

        frame = new SafeFrame();
        Container cp = frame.getContentPane();
        cp.add(this);
        frame.setJMenuBar(createMenuBar());
        frame.setTitle(factory.getTitle());
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
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
            switch (cmd) {
                case "Save":
                    Utilities.save(model, false);
                    break;
                case "Open":
                    Model newModel = Utilities.open(model);
                    if (newModel != null) setModel(newModel);
                    break;
                case "About":
                    Utilities.inform(this.factory.about());
                    break;
                case "Help":
                    Utilities.inform(this.factory.getHelp());
                    break;
                case "New":
                    Utilities.saveChanges(model);
                    setModel(factory.makeModel());
                    model.setUnsavedChanges(false);
                    break;
                case "Quit":
                    Utilities.saveChanges(model);
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
//        JFrame frame = new JFrame();
//        frame.setDefaultCloseOperation(3);
//        Container cp = frame.getContentPane();
//        cp.add(this);
//        frame.setJMenuBar(this.createMenuBar());
//        frame.setTitle(this.factory.getTitle());
//        frame.setSize(500, 300);
//        frame.setVisible(true);
        frame.setVisible(true);
    }

    public void update() {

    }

    public void setModel(Model newModel) {
        this.model.unsubscribe(this);
        this.model = newModel;
        this.model.subscribe(this);
        view.setModel(this.model);
        model.changed();
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
