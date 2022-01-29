package ui.graph.symbols;

import enums.ConnectableDirectionEnum;
import enums.ConnectorTypeEnum;
import enums.SymbolEnum;
import interfaces.symbols.IInConnector;
import interfaces.symbols.ITrueOutConnector;
import io.qt.core.QPointF;
import io.qt.core.QSizeF;
import io.qt.core.Qt;
import io.qt.gui.*;
import io.qt.widgets.QStyleOptionGraphicsItem;
import io.qt.widgets.QWidget;
import ui.graph.symbols.base.BaseSymbol;
import ui.graph.symbols.connectors.Connector;

public class InputSymbol extends BaseSymbol implements IInConnector, ITrueOutConnector {
    private Connector inConnector;
    public Connector trueOutConnector;
    private QPolygonF polygon;

    public InputSymbol(QPointF pos, QSizeF size) {
        this.setPos(pos);
        this.size = size;
        this.text = SymbolEnum.Input.name();

        this.inConnector = new Connector(this, new QPointF(size.width() / 2, 0), ConnectorTypeEnum.In,
                ConnectableDirectionEnum.Top);
        this.trueOutConnector = new Connector(this, new QPointF(size.width() / 2, size.height()),
                ConnectorTypeEnum.Out, ConnectableDirectionEnum.Bottom);

        ConnectSignals();

        polygon = new QPolygonF();
        polygon.append(0, 0);
        polygon.append(size.width() / 5 * 4, 0);
        polygon.append(size.width(), size.height());
        polygon.append(size.width() / 5, size.height());
    }

    @Override
    public Connector getInConnector() {
        return inConnector;
    }

    @Override
    public Connector getTrueOutConnector() {
        return trueOutConnector;
    }

    @Override
    public void UpdatePosition() {
        if (inConnector != null) inConnector.SymbolPositionChanged();
        if (trueOutConnector != null) trueOutConnector.SymbolPositionChanged();
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
