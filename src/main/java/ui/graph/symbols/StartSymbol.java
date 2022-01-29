package ui.graph.symbols;

import enums.ConnectableDirectionEnum;
import enums.ConnectorTypeEnum;
import enums.SymbolEnum;
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

public class StartSymbol extends BaseSymbol implements ITrueOutConnector {
    Connector trueOutConnector;

    public StartSymbol(QPointF pos, QSizeF size) {
        this.text = SymbolEnum.Start.name();
        this.setPos(pos);
        this.size = size;

        trueOutConnector = new Connector(this, new QPointF(this.size.width() / 2, this.size.height()),
                ConnectorTypeEnum.Out, ConnectableDirectionEnum.Bottom);

        ConnectSignals();
    }

    @Override
    public Connector getTrueOutConnector() {
        return trueOutConnector;
    }

    @Override
    public void UpdatePosition() {
        if (trueOutConnector != null) trueOutConnector.SymbolPositionChanged();
    }

    @Override
    public void paint(QPainter qPainter, QStyleOptionGraphicsItem qStyleOptionGraphicsItem, QWidget qWidget) {
        SetDefaultQPainterSettings(qPainter);
        QPen pen = new QPen(new QColor(Qt.GlobalColor.darkMagenta));
        pen.setWidth(3);
        qPainter.setPen(pen);
        qPainter.drawEllipse(boundingRect());
        qPainter.drawText(boundingRect(), Qt.AlignmentFlag.AlignCenter.value(), text);
    }
}
