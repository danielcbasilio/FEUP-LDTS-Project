package org.crazytracks.control;

import org.crazytracks.Game;
import org.crazytracks.gui.GUI;
import org.crazytracks.model.Menu;

import java.io.IOException;

public class MenuController extends Controller<Menu> {
        private Menu model;

        public MenuController(Menu model) {
            super(model);
        }

    @Override
    public void step(Game game, GUI.ACTION action, long time) throws IOException {

    }
}
