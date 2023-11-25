package org.crazytracks.viewer;

import org.crazytracks.gui.GUI;
import org.crazytracks.model.Element;

public class CoinDrawer implements ElementDrawer {

    @Override
    public void draw(Element coin, GUI gui) {
        gui.drawCoin(coin.getPosition());
    }
}