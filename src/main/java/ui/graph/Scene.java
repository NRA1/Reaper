package ui.graph;

import enums.SymbolEnum;
import io.qt.core.QList;
import io.qt.core.QPointF;
import io.qt.core.QSizeF;
import io.qt.widgets.QGraphicsItem;
import io.qt.widgets.QGraphicsScene;
import io.qt.widgets.QGraphicsSceneDragDropEvent;
import ui.graph.connections.BaseConnection;
import ui.graph.symbols.*;

public class Scene extends QGraphicsScene {
    BaseSymbol currCreatedSymbol;

    public Scene() {
        BaseConnection connection = new BaseConnection();
        StartSymbol startSymbol = new StartSymbol(new QPointF(-50, -100), new QSizeF(100, 50), connection);
        this.addItem(startSymbol);
        EndSymbol endSymbol = new EndSymbol(new QPointF(-50, 100), new QSizeF(100, 50), connection);
        this.addItem(endSymbol);
        this.addItem(connection);

        this.changed.connect(this, "updateSceneRect()");
    }

    private void updateSceneRect() {/*
        if (this.views().length() < 1) return;
        QGraphicsView view = this.views().at(0);
        QRectF sceneRect = this.itemsBoundingRect().united(new QRectF(0, 0, view.width(), view.height()));
        this.setSceneRect(sceneRect);*/
    }

    @Override
    protected void dragEnterEvent(QGraphicsSceneDragDropEvent event) {
        if (event.mimeData().hasFormat(SymbolEnum.class.getName())) {
            event.acceptProposedAction();
            String data = event.mimeData().data(SymbolEnum.class.getName()).toString();

            if (data.equals(SymbolEnum.Assignment.name())) {
                currCreatedSymbol = new AssignmentSymbol(event.scenePos().subtract(new QPointF(50, 25)),
                        new QSizeF(100, 50));
            } else if (data.equals(SymbolEnum.Input.name())) {
                currCreatedSymbol = new InputSymbol(event.scenePos().subtract(new QPointF(50, 25)),
                        new QSizeF(100, 50));
            } else if (data.equals(SymbolEnum.Output.name())) {
                currCreatedSymbol = new OutputSymbol(event.scenePos().subtract(new QPointF(50, 25)),
                        new QSizeF(100, 50));
            } else {
                currCreatedSymbol = new BaseSymbol(event.scenePos().subtract(new QPointF(50, 25)),
                        new QSizeF(100, 50), event.mimeData().data(SymbolEnum.class.getName()).toString());
            }
            currCreatedSymbol.setOpacity(0.5);
            this.addItem(currCreatedSymbol);
        } else {
            super.dragEnterEvent(event);
        }
    }

    @Override
    protected void dragMoveEvent(QGraphicsSceneDragDropEvent event) {
        if (event.mimeData().hasFormat(SymbolEnum.class.getName())) {
            currCreatedSymbol.setPos(event.scenePos().subtract(
                    new QPointF(50, 25)));
            return;
        }
        super.dragMoveEvent(event);
    }

    @Override
    protected void dropEvent(QGraphicsSceneDragDropEvent event) {
        if (event.mimeData().hasFormat(SymbolEnum.class.getName())) {
            currCreatedSymbol.setPos(event.scenePos().subtract(
                    new QPointF(50, 25)));
            currCreatedSymbol.setOpacity(1);

            QList<QGraphicsItem> underItems = this.items(event.scenePos());
            QGraphicsItem underItem;
            //underItems.removeIf(item -> item == currCreatedSymbol);
            for (QGraphicsItem item : underItems) {
                if (item == currCreatedSymbol) underItems.remove(item);
            }
            if (underItems.length() == 0) {
                super.dropEvent(event);
                return;
            } else {
                underItem = underItems.at(0);
            }

            if (underItem instanceof BaseConnection) {
                BaseConnection inConn = (BaseConnection) underItem;
                if (currCreatedSymbol instanceof PlacableSymbol) {
                    BaseConnection outConn = new BaseConnection();
                    PlacableSymbol symbol = (PlacableSymbol) currCreatedSymbol;

                    BaseSymbol prevOutSymbol = inConn.getOutSymbol();
                    if (prevOutSymbol instanceof PlacableSymbol) {
                        ((PlacableSymbol) prevOutSymbol).setInConnection(outConn);
                    } else if (prevOutSymbol instanceof EndSymbol) {
                        ((EndSymbol) prevOutSymbol).setInConnection(outConn);
                    } else return;
                    symbol.setOutConnection(outConn);
                    this.addItem(outConn);

                    symbol.setInConnection(inConn);
                }
            }

        } else {
            super.dropEvent(event);
        }
    }
}
