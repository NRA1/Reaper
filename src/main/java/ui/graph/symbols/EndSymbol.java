package ui.graph.symbols;

import enums.ConnectableDirectionEnum;
import enums.ConnectorTypeEnum;
import enums.SymbolEnum;
import interfaces.symbols.IInConnector;
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

public class EndSymbol extends BaseSymbol implements IInConnector {
    private Connector inConnector;

    public EndSymbol(QPointF pos, QSizeF size) {
        this.text = SymbolEnum.End.name();
        this.setPos(pos);
        this.size = size;

        this.inConnector = new Connector(this, new QPointF(size.width() / 2, 0), ConnectorTypeEnum.In,
                ConnectableDirectionEnum.Top);

        ConnectSignals();
    }

    @Override
    public Connector getInConnector() {
        return inConnector;
    }

    @Override
    public void UpdatePosition() {
        if (inConnector != null) inConnector.SymbolPositionChanged();
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
