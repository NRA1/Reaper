package ui.widgets.buttons;

import enums.SymbolEnum;
import io.qt.core.QByteArray;
import io.qt.core.QMimeData;
import io.qt.core.QPoint;
import io.qt.core.Qt;
import io.qt.gui.QDrag;
import io.qt.gui.QMouseEvent;
import io.qt.widgets.QApplication;
import io.qt.widgets.QPushButton;

public class SymbolButton extends QPushButton {
    private QPoint dragStartPos;
    private SymbolEnum symbol;

    public SymbolButton(SymbolEnum symbol) {
        this.symbol = symbol;
        this.setText(symbol.name());
    }

    @Override
    protected void mousePressEvent(QMouseEvent event) {
        if (event.button() == Qt.MouseButton.LeftButton)
            dragStartPos = event.pos();
        super.mousePressEvent(event);
    }

    @Override
    protected void mouseMoveEvent(QMouseEvent event) {
        if (event.buttons().isSet(Qt.MouseButton.LeftButton) && (event.pos().subtract(dragStartPos)).manhattanLength() >
                QApplication.startDragDistance()) {
            QDrag drag = new QDrag(this);
            QMimeData mimeData = new QMimeData();
            mimeData.setData(SymbolEnum.class.getName(), new QByteArray(symbol.name()));
            drag.setMimeData(mimeData);
            Qt.DropAction dropAction = drag.exec();
        }
        else {
            super.mouseMoveEvent(event);
        }
    }
}
