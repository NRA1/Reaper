package ui.graph.connections;

import enums.ConnectableDirectionEnum;
import io.qt.core.QMarginsF;
import io.qt.core.QPointF;
import io.qt.core.QRectF;
import io.qt.core.Qt;
import io.qt.gui.*;
import io.qt.widgets.QGraphicsItem;
import io.qt.widgets.QStyleOptionGraphicsItem;
import io.qt.widgets.QWidget;
import ui.graph.symbols.base.BaseSymbol;
import ui.graph.symbols.connectors.Connector;
import ui.graph.symbols.connectors.MergeConnector;

public class BaseConnection implements QGraphicsItem {
    private Connector inConnector;
    private Connector outConnector;

    private boolean isBold = false;

    private QPainterPath path;

    public BaseConnection() {
        path = new QPainterPath();
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
        UpdateShape();
    }

    public void UpdateOutPos() {
        UpdateShape();
        update();
        if(inConnector.getParent() instanceof MergeConnector)
            ((MergeConnector) inConnector.getParent()).UpdatePosition();
    }

    public void setOutConnector(Connector outConnector) {
        this.outConnector = outConnector;
        UpdateOutPos();
    }

    public Connector getOutConnector() {
        return outConnector;
    }

    public void setBold(boolean bold) {
        isBold = bold;
        update();
    }

    private void UpdateShape() {
        if (inConnector == null || outConnector == null) return;
        QPointF p1 = mapFromScene(inConnector.pos());
        QPointF p5 = mapFromScene(outConnector.pos());
        QPointF p2 = null;
        if(inConnector.connectableDirection == ConnectableDirectionEnum.Left)
            p2 = p5.x() < p1.y() - 30 ? new QPointF(p5.x(), p1.y()) : new QPointF(p1.x() - 30, p1.y());
        else if(inConnector.connectableDirection == ConnectableDirectionEnum.Right)
            p2 = p5.x() > p1.x() + 30 ? new QPointF(p5.x(), p1.y()) : new QPointF(p1.x() + 30, p1.y());
        else
            p2 = p1;
        QPointF p3 = outConnector.connection().outConnector.getParent() instanceof MergeConnector
                ? new QPointF(p2.x(), p5.y())
                : new QPointF(p2.x(), (p5.y() - p1.y()) / 2);
        QPointF p4 = new QPointF(p5.x(), p3.y());
        QPainterPath path = new QPainterPath();
        path.moveTo(p1);
        path.lineTo(p2);
        path.lineTo(p3);
        path.lineTo(p4);
        path.lineTo(p5);
        this.path = path;
    }

    @Override
    public QRectF boundingRect() {
        return path.boundingRect().normalized().marginsAdded(new QMarginsF(10, 10, 10, 10));
    }

    @Override
    public void paint(QPainter qPainter, QStyleOptionGraphicsItem qStyleOptionGraphicsItem, QWidget qWidget) {
        qPainter.setRenderHints(new QPainter.RenderHints(QPainter.RenderHint.Antialiasing, QPainter.RenderHint.TextAntialiasing));
        QPen pen = new QPen(new QColor(Qt.GlobalColor.blue));
        if (isBold) pen.setWidth(10);
        else pen.setWidth(5);
        qPainter.setPen(pen);
        qPainter.drawPath(path);
    }
}
