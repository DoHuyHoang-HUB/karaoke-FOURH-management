package gui.swing.table;

import gui.swing.model.ModelAction;

public interface EventAction {

    public void delete(Object obj);

    public void update(ModelAction action);
}
