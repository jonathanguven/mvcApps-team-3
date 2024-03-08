package mvc;

import java.io.Serializable;

public class Model extends Publisher implements Serializable
{
    private Boolean unsavedChanges;
    private String fileName;
    public Model()
    {
        unsavedChanges = false;
        fileName = null;
    }

    public void changed()
    {
        unsavedChanges = true;
        notifySubscribers();
    }
}
