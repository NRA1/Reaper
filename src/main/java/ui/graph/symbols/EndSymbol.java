package ui.graph.symbols;

import enums.ConnectorTypeEnum;
import io.qt.core.QPointF;
import io.qt.core.QSizeF;
import io.qt.core.Qt;
import io.qt.gui.QColor;
import io.qt.gui.QPainter;
import io.qt.gui.QPen;
import io.qt.widgets.QStyleOptionGraphicsItem;
import io.qt.widgets.QWidget;
import ui.graph.connections.BaseConnection;

public class EndSymbol extends BaseSymbol {
    private BaseConnection inConnection;

    public EndSymbol(QPointF position, QSizeF size, BaseConnection inConnection) {
        this.setPos(this.mapFromScene(position));
        this.size = size;
        this.text = "End";
        this.inConnection = inConnection;
        this.inConnection.setOutSymbol(this);
    }

    public void setInConnection(BaseConnection inConnection) {
        this.inConnection = inConnection;
        this.inConnection.setOutSymbol(this);
    }

    @Override
    public void paint(QPainter qPainter, QStyleOptionGraphicsItem qStyleOptionGraphicsItem, QWidget qWidget) {
        QPen pen = new QPen(new QColor(Qt.GlobalColor.darkMagenta));
        pen.setWidth(3);
        qPainter.setPen(pen);
        qPainter.drawEllipse(boundingRect());
        qPainter.drawText(boundingRect(), Qt.AlignmentFlag.AlignCenter.value(), text);
    }
}
