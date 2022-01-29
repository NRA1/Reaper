package ui.graph.symbols;

import enums.ConnectableDirectionEnum;
import enums.ConnectorTypeEnum;
import enums.SymbolEnum;
import interfaces.symbols.IFalseOutConnector;
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

public class CaseSymbol extends BaseSymbol implements IInConnector, ITrueOutConnector, IFalseOutConnector {
    private Connector inConnector;
    private Connector trueOutConnector;
    private Connector falseOutConnector;

    public CaseSymbol(QPointF pos, QSizeF size) {
        this.setPos(pos);
        this.size = size;
        this.text = SymbolEnum.Case.name();

        this.inConnector = new Connector(this, new QPointF(size.width() / 2, 0), ConnectorTypeEnum.In,
                ConnectableDirectionEnum.Top);
        this.trueOutConnector = new Connector(this, new QPointF(0, size.height() / 2),
                ConnectorTypeEnum.Out, ConnectableDirectionEnum.Left);
        this.falseOutConnector = new Connector(this, new QPointF(size.width(), size.height() / 2),
                ConnectorTypeEnum.Out, ConnectableDirectionEnum.Right);

        ConnectSignals();
    }

    @Override
    public Connector getFalseOutConnector() {
        return falseOutConnector;
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
        if (falseOutConnector != null) falseOutConnector.SymbolPositionChanged();
    }

    @Override
    public void paint(QPainter qPainter, QStyleOptionGraphicsItem qStyleOptionGraphicsItem, QWidget qWidget) {
        SetDefaultQPainterSettings(qPainter);
        QPen pen = new QPen(new QColor(Qt.GlobalColor.darkBlue));
        pen.setWidth(3);
        qPainter.setPen(pen);
        qPainter.drawRect(boundingRect());
        qPainter.drawText(boundingRect(), Qt.AlignmentFlag.AlignCenter.value(), text);
    }
}
