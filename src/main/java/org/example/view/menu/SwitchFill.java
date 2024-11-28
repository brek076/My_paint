package org.example.view.menu;

import org.example.controller.MenuState;

public class SwitchFill implements AppCommand {
    private boolean fill;
    private MenuState menuState;

    public SwitchFill(boolean fill, MenuState menuState) {
        this.fill = fill;
        this.menuState = menuState;
    }

    @Override
    public void execute() {
        menuState.setFill(this.fill);
    }
}
