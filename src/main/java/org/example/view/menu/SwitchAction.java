package org.example.view.menu;

import org.example.controller.MenuState;
import org.example.controller.actions.AppAction;

//@AllArgsConstructor
public class SwitchAction implements AppCommand{
    private MenuState menuState;
    private AppAction appAction;
    @Override
    public void execute() {

    }
}
