//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package mvc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Random;

public class Utilities
{
    public static Random rng = new Random(System.currentTimeMillis());
    private static int nextID = 100;

    public Utilities()
    {
    }

    public static boolean confirm(String query)
    {
        int result = JOptionPane.showConfirmDialog(null, query, "choose one", 0);
        return result == 0;
    }

    public static String ask(String query)
    {
        return JOptionPane.showInputDialog(null, query);
    }

    public static void inform(String info)
    {
        JOptionPane.showMessageDialog(null, info);
    }

    public static void inform(String[] items)
    {
        String helpString = "";

        for (int i = 0; i < items.length; ++i)
        {
            helpString = helpString + "\n" + items[i];
        }

        inform(helpString);
    }

    public static void error(String gripe)
    {
        JOptionPane.showMessageDialog(null, gripe, "OOPS!", 0);
    }

    public static void error(Exception gripe)
    {
        gripe.printStackTrace();
        JOptionPane.showMessageDialog(null, gripe.getMessage(), "OOPS!", 0);
    }

    public static void saveChanges(Model model)
    {
        if (model.getUnsavedChanges() && confirm("current model has unsaved changes, continue?"))
        {
            save(model, false);
        }

    }

    public static String getFileName(String fName, Boolean open)
    {
        JFileChooser chooser = new JFileChooser();
        String result = null;
        if (fName != null)
        {
            chooser.setCurrentDirectory(new File(fName));
        }

        int returnVal;
        if (open)
        {
            returnVal = chooser.showOpenDialog(null);
            if (returnVal == 0)
            {
                result = chooser.getSelectedFile().getPath();
            }
        }
        else
        {
            returnVal = chooser.showSaveDialog(null);
            if (returnVal == 0)
            {
                result = chooser.getSelectedFile().getPath();
            }
        }

        return result;
    }

    public static void save(Model model, Boolean saveAs)
    {
        String fName = model.getFileName();
        if (fName == null || saveAs)
        {
            fName = getFileName(fName, false);
            model.setFileName(fName);
        }

        try
        {
            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(fName));
            model.setUnsavedChanges(false);
            os.writeObject(model);
            os.close();
        } catch (Exception var4)
        {
            model.setUnsavedChanges(true);
            error(var4);
        }

    }

    public static Model open(Model model)
    {
        saveChanges(model);
        String fName = getFileName(model.getFileName(), true);
        Model newModel = null;

        try
        {
            ObjectInputStream is = new ObjectInputStream(new FileInputStream(fName));
            newModel = (Model) is.readObject();
            is.close();
        } catch (Exception var4)
        {
            error(var4);
        }

        return newModel;
    }

    public static JMenu makeMenu(String name, String[] items, ActionListener handler)
    {
        JMenu result = new JMenu(name);

        for (int i = 0; i < items.length; ++i)
        {
            JMenuItem item = new JMenuItem(items[i]);
            item.addActionListener(handler);
            result.add(item);
        }

        return result;
    }

    public static void log(String msg)
    {
        System.out.println(msg);
    }

    public static int getID()
    {
        return nextID++;
    }
}
