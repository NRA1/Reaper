package ui.graph.connections;

import io.qt.core.QMarginsF;
import io.qt.core.QPointF;
import io.qt.core.QRectF;
import io.qt.core.Qt;
import io.qt.gui.QColor;
import io.qt.gui.QPainter;
import io.qt.gui.QPen;
import io.qt.widgets.QGraphicsItem;
import io.qt.widgets.QStyleOptionGraphicsItem;
import io.qt.widgets.QWidget;
import ui.graph.symbols.base.BaseSymbol;
import ui.graph.symbols.connectors.Connector;

public class BaseConnection implements QGraphicsItem {
    private Connector inConnector;
    private Connector outConnector;

    public BaseConnection() {}
    public BaseConnection(Connector inConnector, Connector outConnector) {
        this.inConnector = inConnector;
        this.outConnector = outConnector;
    }

    public void setInConnector(Connector inConnector) {
        this.inConnector = inConnector;
        UpdatePos();
    }

    public Connector getInConnector() {
        return inConnector;
    }

    public void UpdatePos() {
        this.setPos(inConnector.pos());
    }

    public void setOutConnector(Connector outConnector) {
        this.outConnector = outConnector;
        update();
    }

    public Connector getOutConnector() {
        return outConnector;
    }

    @Override
    public QRectF boundingRect() {
        QRectF rect = new QRectF(this.mapFromScene(inConnector.pos()), this.mapFromScene(outConnector.pos()));
        return new QRectF(0, 0, rect.width(), rect.height()).normalized().marginsAdded(
                new QMarginsF(10, 10, 10, 10));
    }

    @Override
    public void paint(QPainter qPainter, QStyleOptionGraphicsItem qStyleOptionGraphicsItem, QWidget qWidget) {
        qPainter.drawRect(boundingRect());
        qPainter.setRenderHints(new QPainter.RenderHints(QPainter.RenderHint.Antialiasing, QPainter.RenderHint.TextAntialiasing));
        QPen pen = new QPen(new QColor(Qt.GlobalColor.blue));
        pen.setWidth(5);
        qPainter.setPen(pen);
        qPainter.drawLine(mapFromScene(inConnector.pos()), mapFromScene(outConnector.pos()));
    }
}
