package ui.graph.symbols.base;

import ui.graph.symbols.connectors.ConnectorParentItem;
import io.qt.core.*;
import io.qt.gui.QColor;
import io.qt.gui.QPainter;
import io.qt.gui.QPen;
import io.qt.widgets.QGraphicsItem;
import io.qt.widgets.QStyleOptionGraphicsItem;
import io.qt.widgets.QWidget;

public class BaseSymbol extends ConnectorParentItem implements QGraphicsItem {
    public QSizeF size;
    protected String text;


    public BaseSymbol(QPointF position, QSizeF size, String text) {
        this.setPos(this.mapFromScene(position));
        this.size = size;
        this.text = text;
        this.setFlags(GraphicsItemFlag.ItemIsMovable, GraphicsItemFlag.ItemIsSelectable,
                GraphicsItemFlag.ItemSendsGeometryChanges);

        UpdatePosition();

    }

    public BaseSymbol() {
        this.setFlags(GraphicsItemFlag.ItemIsMovable, GraphicsItemFlag.ItemIsSelectable,
                GraphicsItemFlag.ItemSendsGeometryChanges);
    }

    public void ConnectSignals() {
        UpdatePosition.connect(this::PrintTest);
    }

    protected Object itemChange(GraphicsItemChange change, Object value) {
        if (change == GraphicsItemChange.ItemPositionChange) {
            UpdatePosition();
            UpdatePosition.emit();
        }
        return value;
    }

    public void PrintTest() {
        System.out.println("Test");
    }

    protected void UpdatePosition() { }

    @Override
    public QRectF boundingRect() {
        return new QRectF(new QPointF(0, 0), size);
    }

    @Override
    public void paint(QPainter qPainter, QStyleOptionGraphicsItem qStyleOptionGraphicsItem, QWidget qWidget) {
        SetDefaultQPainterSettings(qPainter);
        QPen pen = new QPen(new QColor(Qt.GlobalColor.black));
        pen.setWidth(3);
        qPainter.setPen(pen);
        qPainter.drawRect(boundingRect());
        qPainter.drawText(boundingRect(), Qt.AlignmentFlag.AlignCenter.value(), text);
    }

    protected void SetDefaultQPainterSettings(QPainter qPainter) {
        qPainter.setRenderHints(new QPainter.RenderHints(QPainter.RenderHint.Antialiasing, QPainter.RenderHint.TextAntialiasing));
    }


    public QPointF scenePosition() {
        return scenePos();
    }
}
