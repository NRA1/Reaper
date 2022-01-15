package ui.graph.symbols;

import enums.ConnectorTypeEnum;
import enums.SymbolEnum;
import io.qt.core.QPointF;
import io.qt.core.QSizeF;
import io.qt.core.Qt;
import io.qt.gui.*;
import io.qt.widgets.QStyleOptionGraphicsItem;
import io.qt.widgets.QWidget;
import ui.graph.connections.BaseConnection;

public class InputSymbol extends PlacableSymbol {

    public static QPolygonF polygon;

    public InputSymbol(QPointF position, QSizeF size, BaseConnection inConnection, BaseConnection outConnection) {
        this.setPos(this.mapFromScene(position));
        this.size = size;
        this.text = SymbolEnum.Input.name();

        polygon = new QPolygonF();
        polygon.append(size.width() / 5, 0);
        polygon.append(size.width(), 0);
        polygon.append(size.width() / 5 * 4, size.height());
        polygon.append(0, size.height());

        setInConnection(inConnection);
        setOutConnection(outConnection);
    }

    public InputSymbol(QPointF position, QSizeF size) {
        this.setPos(this.mapFromScene(position));
        this.size = size;
        this.text = SymbolEnum.Input.name();

        polygon = new QPolygonF();
        polygon.append(size.width() / 5, 0);
        polygon.append(size.width(), 0);
        polygon.append(size.width() / 5 * 4, size.height());
        polygon.append(0, size.height());
    }

    @Override
    public void paint(QPainter qPainter, QStyleOptionGraphicsItem qStyleOptionGraphicsItem, QWidget qWidget) {
        SetDefaultQPainterSettings(qPainter);
        QPen pen = new QPen(new QColor(Qt.GlobalColor.red));
        pen.setWidth(3);
        qPainter.setPen(pen);
        qPainter.drawPolygon(polygon);
        qPainter.drawText(boundingRect(), Qt.AlignmentFlag.AlignCenter.value(), text);
    }
}
