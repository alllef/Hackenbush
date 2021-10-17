package org.Lipovetskii;

import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.router.Route;

/**
 * The main view contains a button and a click listener.
 */
@Route
@PWA(name = "My Application", shortName = "My Application")
public class MainView extends HorizontalLayout {




    public MainView() {
        getStyle().set("padding", "0");
        add(HackenBashGraph.canvas,Game.turnNotify,Game.difficulty,Game.restart,Game.endNotify);
    }
}
