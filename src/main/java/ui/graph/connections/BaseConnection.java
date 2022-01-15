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
import ui.graph.symbols.BaseSymbol;

public class BaseConnection implements QGraphicsItem {
    private BaseSymbol inSymbol;
    private BaseSymbol outSymbol;

    public BaseConnection() {}
    public BaseConnection(BaseSymbol inSymbol, BaseSymbol outSymbol) {
        this.inSymbol = inSymbol;
        this.outSymbol = outSymbol;
    }

    public BaseSymbol getInSymbol() {
        return inSymbol;
    }

    public void setInSymbol(BaseSymbol inSymbol) {
        this.inSymbol = inSymbol;
        UpdatePos();
    }

    public BaseSymbol getOutSymbol() {
        return outSymbol;
    }

    public void UpdatePos() {
        this.setPos(inSymbol.scenePos().add(new QPointF(this.inSymbol.size.width() / 2,
                this.inSymbol.size.height())));
    }

    public void setOutSymbol(BaseSymbol outSymbol) {
        this.outSymbol = outSymbol;
        update();
    }

    @Override
    public QRectF boundingRect() {
        QRectF rect = new QRectF(this.mapFromScene(inSymbol.scenePos()), this.mapFromScene(outSymbol.scenePos()
                .subtract(new QPointF(0, outSymbol.size.height()))));
        return new QRectF(0, 0, rect.width(), rect.height()).normalized().marginsAdded(
                new QMarginsF(10, 10, 10, 10));
    }

    @Override
    public void paint(QPainter qPainter, QStyleOptionGraphicsItem qStyleOptionGraphicsItem, QWidget qWidget) {
        qPainter.setRenderHints(new QPainter.RenderHints(QPainter.RenderHint.Antialiasing, QPainter.RenderHint.TextAntialiasing));
        QPen pen = new QPen(new QColor(Qt.GlobalColor.blue));
        pen.setWidth(5);
        qPainter.setPen(pen);
        qPainter.drawLine(new QPointF(0, 0), this.mapFromScene(new QPointF(outSymbol.scenePos().x()
                + outSymbol.size.width() / 2, outSymbol.scenePos().y())));
    }
}
