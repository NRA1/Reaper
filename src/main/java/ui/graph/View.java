package ui.graph;

import io.qt.widgets.QGraphicsView;

public class View extends QGraphicsView {
    public View(Scene scene) {
        this.setScene(scene);
        this.setAcceptDrops(true);
    }
}
