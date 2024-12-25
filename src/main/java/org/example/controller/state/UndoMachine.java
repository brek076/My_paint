package org.example.controller.state;

import org.example.controller.actions.AppAction;
import org.example.view.menu.CommandActionListener;

import java.util.LinkedList;

public class UndoMachine {
    private UndoRedoState undoRedoState;
    private CommandActionListener undoActionListener;

    public UndoMachine(CommandActionListener undoActionListener, CommandActionListener redoActionListener) {
        this.undoActionListener = undoActionListener;
        this.redoActionListener = redoActionListener;
        LinkedList<AppAction> undoList = new LinkedList<>();
        LinkedList<AppAction> redoList = new LinkedList<>();
        undoRedoState = new StateDisableUndoDisableRedo(undoList, redoList);
    }

    private CommandActionListener redoActionListener;

    public UndoMachine() {
        LinkedList<AppAction> undoList = new LinkedList<>();
        LinkedList<AppAction> redoList = new LinkedList<>();
        undoRedoState = new StateDisableUndoDisableRedo(undoList, redoList);
    }

    public void executeRedo() {
        undoRedoState = undoRedoState.redo();
    }

    public void executeUndo() {
        undoRedoState = undoRedoState.undo();
    }

    public boolean isEnableUndo() {
        return !undoRedoState.getUndoActivityList().isEmpty();
    }


    public boolean isEnableRedo() {
        return !undoRedoState.getRedoActivityList().isEmpty();
    }

    public void updateButtons(){
        undoActionListener.setEnabled(isEnableUndo());
        redoActionListener.setEnabled(isEnableRedo());
    }

    public void add(AppAction action) {
        // Очищаем redo только если это новое действие, а не продолжение undo/redo операций
        if (!undoRedoState.getRedoActivityList().isEmpty()) {
            undoRedoState.clearHistory();  // Очищаем redo список
        }

        // Добавляем новое действие в undo список
        undoRedoState.addAction(action);

        // Переходим в состояние "Undo доступен, Redo недоступен"
        undoRedoState = new StateEnableUndoDisableRedo(
                undoRedoState.getUndoActivityList(),
                undoRedoState.getRedoActivityList()
        );
    }
}
