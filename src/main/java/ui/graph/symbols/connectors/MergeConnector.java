package ui.graph.symbols.connectors;

import enums.ConnectableDirectionEnum;
import enums.ConnectorTypeEnum;
import interfaces.symbols.IContinueConnector;
import interfaces.symbols.IInConnector;
import interfaces.symbols.ITrueOutConnector;
import io.qt.core.QPointF;
import ui.graph.connections.BaseConnection;

public class MergeConnector extends ConnectorParentItem implements IInConnector, IContinueConnector, ITrueOutConnector {
    private Connector continueInConnector;
    private Connector inConnector;
    private Connector trueOutConnector;

    public MergeConnector() {
        continueInConnector = new Connector(this, new QPointF(0, 0), ConnectorTypeEnum.In,
                ConnectableDirectionEnum.Right);
        inConnector = new Connector(this, new QPointF(0, 0), ConnectorTypeEnum.In,
                ConnectableDirectionEnum.Left);
        trueOutConnector = new Connector(this, new QPointF(0, 0), ConnectorTypeEnum.Out,
                ConnectableDirectionEnum.Bottom);
    }

    @Override
    public Connector getContinueInConnector() {
        return continueInConnector;
    }

    @Override
    public Connector getInConnector() {
        return inConnector;
    }

    @Override
    public Connector getTrueOutConnector() {
        return trueOutConnector;
    }

    public void setTrueOutConnection(BaseConnection connection) {
        this.trueOutConnector.setConnection(connection);
        this.trueOutConnector.connection().getOutConnector().getParent().UpdatePosition.connect(this::UpdatePosition);
    }

    public QPointF scenePosition() {
        return trueOutConnector.connection().getOutConnector().pos().subtract(new QPointF(0, 50));
    }

    public void UpdatePosition() {
        if (inConnector != null) inConnector.SymbolPositionChanged();
        if (trueOutConnector != null) trueOutConnector.SymbolPositionChanged();
        if (continueInConnector != null) continueInConnector.SymbolPositionChanged();
    }
}
