package mvc;

import javax.swing.JPanel;

public class View extends JPanel implements Subscriber {
    public Model model;

    public View(Model newModel) {
        this.model = newModel;
        this.model.subscribe(this);
    }

    public void update() {
        this.repaint();
    }

    public void setModel(Model newModel) {
        this.model.unsubscribe(this);
        this.model = newModel;
        this.model.subscribe(this);
        this.repaint();
    }
}
