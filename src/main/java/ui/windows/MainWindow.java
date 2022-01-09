package ui.windows;

import io.qt.core.Qt;
import io.qt.widgets.QMainWindow;
import io.qt.widgets.QWidget;
import ui.docks.SymbolsDock;

public class MainWindow extends QMainWindow {
    public MainWindow() {
        // Configure window
        this.resize(1000, 800);

        // Create docks
        SymbolsDock symbolsDock = new SymbolsDock();
        this.addDockWidget(Qt.DockWidgetArea.LeftDockWidgetArea, symbolsDock);

        // Create central widget
        QWidget widget = new QWidget();
        this.setCentralWidget(widget);
    }
}
