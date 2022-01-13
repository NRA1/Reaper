package ui.windows;

import io.qt.core.QResource;
import io.qt.core.QUrl;
import io.qt.core.Qt;
import io.qt.gui.QIcon;
import io.qt.widgets.QMainWindow;
import io.qt.widgets.QWidget;
import ui.docks.SymbolsDock;
import ui.graph.Scene;
import ui.graph.View;

public class MainWindow extends QMainWindow {
    public MainWindow() {
        // Configure window
        this.resize(1000, 800);


        // Create docks
        SymbolsDock symbolsDock = new SymbolsDock();
        this.addDockWidget(Qt.DockWidgetArea.LeftDockWidgetArea, symbolsDock);

        // Create scene
        Scene scene = new Scene();
        View view = new View(scene);
        this.setCentralWidget(view);
    }
}
