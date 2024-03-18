package Life;

import CALab.GridFactory;
import CALab.GridView;
import mvc.Model;
import mvc.View;

public class LifeFactory extends GridFactory {
    @Override
    public Model makeModel() {
        return new Society();
    }

    @Override
    public View makeView(Model m) {
        return new GridView((Society) m);
    }
}
