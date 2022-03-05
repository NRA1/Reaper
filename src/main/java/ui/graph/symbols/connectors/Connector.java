package ui.graph.symbols.connectors;

import enums.ConnectableDirectionEnum;
import enums.ConnectorTypeEnum;
import io.qt.core.QPointF;
import ui.graph.connections.BaseConnection;

public class Connector {
    public ConnectableDirectionEnum connectableDirection;
    public ConnectorTypeEnum type;
    private BaseConnection connection;

    private ConnectorParentItem parent;
    private QPointF posTransform;

    public Connector(ConnectorParentItem parent, QPointF posTransform, ConnectorTypeEnum type,
                     ConnectableDirectionEnum connectableDirection) {
        this.parent = parent;
        this.posTransform = posTransform;
        this.type = type;
        this.connectableDirection = connectableDirection;
    }

    public QPointF pos() {
        return parent.scenePosition().add(posTransform);
    }

    public void SymbolPositionChanged() {
        if (connection != null) {
            if (type == ConnectorTypeEnum.Out) connection.UpdatePos();
            else connection.UpdateOutPos();
        }
    }

    public ConnectorParentItem getParent() {
        return parent;
    }

    public void setConnection(BaseConnection connection) {
        this.connection = connection;
        if (type == ConnectorTypeEnum.Out)
            this.connection.setInConnector(this);
        else
            this.connection.setOutConnector(this);
    }

    public BaseConnection connection() {
        return connection;
    }
}
