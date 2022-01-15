package ui.graph.symbols;

import enums.ConnectorTypeEnum;
import enums.SymbolEnum;
import io.qt.core.QPointF;
import io.qt.core.QSizeF;
import io.qt.core.Qt;
import io.qt.gui.QColor;
import io.qt.gui.QPainter;
import io.qt.gui.QPainterPath;
import io.qt.gui.QPen;
import io.qt.widgets.QStyleOptionGraphicsItem;
import io.qt.widgets.QWidget;
import ui.graph.connections.BaseConnection;

public class AssignmentSymbol extends PlacableSymbol {

    public AssignmentSymbol(QPointF position, QSizeF size, BaseConnection inConnection, BaseConnection outConnection) {
        this.size = size;
        this.setPos(this.mapFromScene(position));
        this.text = SymbolEnum.Assignment.name();
        setInConnection(inConnection);
        setOutConnection(inConnection);
    }

    public AssignmentSymbol(QPointF position, QSizeF size) {
        this.size = size;
        this.setPos(this.mapFromScene(position));
        this.text = SymbolEnum.Assignment.name();
    }

    @Override
    public void paint(QPainter qPainter, QStyleOptionGraphicsItem qStyleOptionGraphicsItem, QWidget qWidget) {
        SetDefaultQPainterSettings(qPainter);
        QPen pen = new QPen(new QColor(Qt.GlobalColor.green));
        pen.setWidth(3);
        qPainter.setPen(pen);
        qPainter.drawRect(boundingRect());
        qPainter.drawText(boundingRect(), Qt.AlignmentFlag.AlignCenter.value(), text);
    }
}
