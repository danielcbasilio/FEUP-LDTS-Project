package org.crazytracks.viewer;

import org.crazytracks.gui.GUI;
import org.crazytracks.model.Element;

public interface ElementViewer<T extends Element> {
    void draw(Element element, GUI gui);
}
