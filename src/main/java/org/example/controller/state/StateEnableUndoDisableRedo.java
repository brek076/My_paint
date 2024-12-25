package org.example.controller.state;

import org.example.controller.actions.AppAction;

import java.util.LinkedList;

public class StateEnableUndoDisableRedo extends UndoRedoState {

    protected StateEnableUndoDisableRedo(LinkedList<AppAction> undoActivityList, LinkedList<AppAction> redoActivity) {
        super(undoActivityList, redoActivity);
    }

    @Override
    public UndoRedoState undo() {
        LinkedList<AppAction> undoActivityList = getUndoActivityList();
        LinkedList<AppAction> redoActivityList = getRedoActivityList();
        AppAction action = undoActivityList.pollLast();

        if (action != null) {
            redoActivityList.add(action);  // Перемещаем в redo список
            action.unexecute();  // Отменяем действие
        }

        // Если undo список пуст, но redo доступен, переходим в состояние "Redo доступен"
        if (undoActivityList.isEmpty()) {
            return new StateDisableUndoEnableRedo(undoActivityList, redoActivityList);
        }

        // Переход в состояние, где доступны оба действия
        return new StateEnableUndoEnableRedo(undoActivityList, redoActivityList);
    }

    @Override
    public UndoRedoState redo() {
        return this;
    }
}
