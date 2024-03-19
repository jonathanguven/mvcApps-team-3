package CALab;

import mvc.Subscriber;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class CellView extends JButton implements ActionListener, Subscriber {
    Cell cell;

    public CellView(Cell c) {
        cell = c;
        if (c != null) {
            c.subscribe(this);
        }
        this.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (cell.getStatus() == 1) {
            cell.setStatus(0);
        }
        else {
            cell.setStatus(1);
        }
        cell.observe();
        cell.notifySubscribers();
    }

    // called by notifySubscribers and GridView.update
    @Override
    public void update() {
        setOpaque(true);
        setBackground(cell.getColor());
        setBorder(BorderFactory.createLineBorder(Color.black)); // needed?
        setText("" + cell.getAmbience());
    }
}
