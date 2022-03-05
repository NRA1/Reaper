package ui.graph.symbols.connectors;

import enums.ConnectableDirectionEnum;
import enums.ConnectorTypeEnum;
import interfaces.symbols.IContinueConnector;
import interfaces.symbols.IInConnector;
import interfaces.symbols.ITrueOutConnector;
import io.qt.core.QPointF;
import ui.graph.connections.BaseConnection;
import ui.graph.symbols.base.BaseSymbol;

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
        UpdatePosition();
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
        if(this.trueOutConnector.connection() != null)
            System.out.println(this.trueOutConnector.connection().getOutConnector().getParent().UpdatePosition.disconnect(this::UpdatePosition));
        this.trueOutConnector.setConnection(connection);
        this.trueOutConnector.connection().getOutConnector().getParent().UpdatePosition.connect(this::UpdatePosition);
        UpdatePosition();
    }

    public QPointF scenePosition() {
        return trueOutConnector.connection() != null && trueOutConnector.connection().getOutConnector() != null
                ? trueOutConnector.connection().getOutConnector().pos().subtract(new QPointF(0, 50))
                : new QPointF(0, 0);
    }

    public void UpdatePosition() {
        if (inConnector != null) inConnector.SymbolPositionChanged();
        if (trueOutConnector != null) trueOutConnector.SymbolPositionChanged();
        if (continueInConnector != null) continueInConnector.SymbolPositionChanged();
    }

    public void ResubscribeToMoveEvents(BaseSymbol oldSymbol, BaseSymbol newSymbol) {
        oldSymbol.UpdatePosition.disconnect(this::UpdatePosition);
        newSymbol.UpdatePosition.connect(this::UpdatePosition);
        UpdatePosition();
    }
}
