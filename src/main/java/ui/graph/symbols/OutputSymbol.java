package ui.graph.symbols;

import enums.ConnectorTypeEnum;
import enums.SymbolEnum;
import io.qt.core.QPointF;
import io.qt.core.QSizeF;
import io.qt.core.Qt;
import io.qt.gui.QColor;
import io.qt.gui.QPainter;
import io.qt.gui.QPen;
import io.qt.gui.QPolygonF;
import io.qt.widgets.QStyleOptionGraphicsItem;
import io.qt.widgets.QWidget;
import ui.graph.connections.BaseConnection;

public class OutputSymbol extends PlacableSymbol {


    public static QPolygonF polygon;

    public OutputSymbol(QPointF position, QSizeF size, BaseConnection inConnection, BaseConnection outConnection) {
        this.setPos(this.mapFromScene(position));
        this.size = size;
        this.text = SymbolEnum.Output.name();

        polygon = new QPolygonF();
        polygon.append(0, 0);
        polygon.append(size.width() / 5 * 4, 0);
        polygon.append(size.width(), size.height());
        polygon.append(size.width() / 5, size.height());

        setInConnection(inConnection);
        this.setOutConnection(outConnection);
    }

    public OutputSymbol(QPointF position, QSizeF size) {
        this.setPos(this.mapFromScene(position));
        this.size = size;
        this.text = SymbolEnum.Output.name();

        polygon = new QPolygonF();
        polygon.append(0, 0);
        polygon.append(size.width() / 5 * 4, 0);
        polygon.append(size.width(), size.height());
        polygon.append(size.width() / 5, size.height());
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
