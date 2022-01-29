package ui.graph.symbols.connectors;

import io.qt.core.QObject;
import io.qt.core.QPointF;

public abstract class ConnectorParentItem extends QObject {
    public final Signal0 UpdatePosition = new Signal0();
    public abstract QPointF scenePosition();
}
