package ui.docks;

import enums.SymbolEnum;
import io.qt.core.Qt;
import io.qt.widgets.QDockWidget;
import io.qt.widgets.QGridLayout;
import io.qt.widgets.QWidget;
import ui.widgets.buttons.SymbolButton;

public class SymbolsDock extends QDockWidget {
    public SymbolsDock() {
        // Configure dock
        this.setFeatures(DockWidgetFeature.NoDockWidgetFeatures);

        // Create buttons
        SymbolButton button1 = new SymbolButton(SymbolEnum.Assignment);
        SymbolButton button2 = new SymbolButton(SymbolEnum.Input);
        SymbolButton button3 = new SymbolButton(SymbolEnum.Output);
        SymbolButton button4 = new SymbolButton(SymbolEnum.Case);
        SymbolButton button5 = new SymbolButton(SymbolEnum.Loop);

        // Create layout
        QGridLayout layout = new QGridLayout();
        layout.setAlignment(Qt.AlignmentFlag.AlignTop);
        layout.addWidget(button1, 0, 0);
        layout.addWidget(button2, 0, 1);
        layout.addWidget(button3, 1, 0);
        layout.addWidget(button4, 1, 1);
        layout.addWidget(button5, 2, 0, 1, 2);

        // Apply the layout
        QWidget layoutHolder = new QWidget();
        layoutHolder.setLayout(layout);
        this.setWidget(layoutHolder);
    }
}
