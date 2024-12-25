package org.example.controller.state;

import org.example.controller.actions.AppAction;

import java.util.LinkedList;

public class StateEnableUndoEnableRedo extends UndoRedoState {

    protected StateEnableUndoEnableRedo(LinkedList<AppAction> undoActivityList, LinkedList<AppAction> redoActivity) {
        super(undoActivityList, redoActivity);
    }

    @Override
    public UndoRedoState undo() {
        LinkedList<AppAction> undoActivityList = getUndoActivityList();
        LinkedList<AppAction> redoActivityList = getRedoActivityList();
        AppAction action = undoActivityList.pollLast();
        if (action != null) {
            redoActivityList.add(action);
            action.unexecute();
        }
        if (undoActivityList.isEmpty()) {
            return new StateDisableUndoEnableRedo(getUndoActivityList(), getRedoActivityList());
        } else return this;
    }

    @Override
    public UndoRedoState redo() {
        LinkedList<AppAction> undoActivityList = getUndoActivityList();
        LinkedList<AppAction> redoActivityList = getRedoActivityList();
        AppAction action = redoActivityList.pollLast();

        if (action != null) {
            undoActivityList.add(action);  // Возвращаем в undo список
            action.execute();  // Повторяем действие
        }

        // Если redo список пуст, но undo доступен, переключаемся в состояние с доступным только undo
        if (redoActivityList.isEmpty()) {
            return new StateEnableUndoDisableRedo(undoActivityList, redoActivityList);
        }

        // Оба действия остаются доступны
        return this;
    }
}
