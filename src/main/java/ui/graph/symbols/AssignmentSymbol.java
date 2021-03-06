package ui.graph.symbols;

import enums.ConnectableDirectionEnum;
import enums.ConnectorTypeEnum;
import enums.SymbolEnum;
import interfaces.symbols.IInConnector;
import interfaces.symbols.ITrueOutConnector;
import io.qt.core.QPointF;
import io.qt.core.QSizeF;
import io.qt.core.Qt;
import io.qt.gui.QColor;
import io.qt.gui.QPainter;
import io.qt.gui.QPen;
import io.qt.widgets.QStyleOptionGraphicsItem;
import io.qt.widgets.QWidget;
import ui.graph.symbols.base.BaseSymbol;
import ui.graph.symbols.connectors.Connector;

public class AssignmentSymbol extends BaseSymbol implements IInConnector, ITrueOutConnector {
    private Connector inConnector;
    private Connector trueOutConnector;

    public AssignmentSymbol(QPointF position, QSizeF size)
    {
        this.setPos(position);
        this.size = size;
        this.text = SymbolEnum.Assignment.name();

        inConnector = new Connector(this, new QPointF(size.width() / 2, 0), ConnectorTypeEnum.In,
                ConnectableDirectionEnum.Top);
        trueOutConnector = new Connector(this, new QPointF(size.width() / 2, size.height()),
                ConnectorTypeEnum.Out, ConnectableDirectionEnum.Bottom);

        ConnectSignals();
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
        QPen pen = new QPen(new QColor(Qt.GlobalColor.green));
        pen.setWidth(3);
        qPainter.setPen(pen);
        qPainter.drawRect(boundingRect());
        qPainter.drawText(boundingRect(), Qt.AlignmentFlag.AlignCenter.value(), text);
    }
}
