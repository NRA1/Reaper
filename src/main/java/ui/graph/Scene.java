package ui.graph;

import enums.SymbolEnum;
import interfaces.symbols.IContinueConnector;
import interfaces.symbols.IFalseOutConnector;
import interfaces.symbols.IInConnector;
import interfaces.symbols.ITrueOutConnector;
import io.qt.core.QPointF;
import io.qt.core.QSizeF;
import io.qt.widgets.QGraphicsScene;
import io.qt.widgets.QGraphicsSceneDragDropEvent;
import ui.graph.connections.BaseConnection;
import ui.graph.symbols.*;
import ui.graph.symbols.base.BaseSymbol;
import ui.graph.symbols.connectors.MergeConnector;

public class Scene extends QGraphicsScene {

    private BaseSymbol droppingSymbol;
    private BaseConnection boldConnection;

    public Scene() {
        StartSymbol startSymbol = new StartSymbol(new QPointF(0, -100), new QSizeF(100, 50));
        EndSymbol endSymbol = new EndSymbol(new QPointF(0, 100), new QSizeF(100, 50));

        BaseConnection connection = new BaseConnection();
        startSymbol.getTrueOutConnector().setConnection(connection);
        endSymbol.getInConnector().setConnection(connection);

        this.addItem(startSymbol);
        this.addItem(endSymbol);
        this.addItem(connection);
    }

    @Override
    protected void dragEnterEvent(QGraphicsSceneDragDropEvent event) {
        if (event.mimeData().hasFormat(SymbolEnum.class.getName())) {
            event.acceptProposedAction();
            String data = event.mimeData().data(SymbolEnum.class.getName()).toString();

            if (data.equals(SymbolEnum.Assignment.name()))
                droppingSymbol = new AssignmentSymbol(event.scenePos().subtract(new QPointF(50, 25)),
                        new QSizeF(100, 50));
            else if (data.equals(SymbolEnum.Input.name()))
                droppingSymbol = new InputSymbol(event.scenePos().subtract(new QPointF(50, 25)),
                        new QSizeF(100, 50));
            else if (data.equals(SymbolEnum.Output.name()))
                droppingSymbol = new OutputSymbol(event.scenePos().subtract(new QPointF(50, 25)),
                        new QSizeF(100, 50));
            else if (data.equals(SymbolEnum.Case.name()))
                droppingSymbol = new CaseSymbol(event.scenePos().subtract(new QPointF(50, 25)),
                        new QSizeF(100, 50));
            else if (data.equals(SymbolEnum.Loop.name()))
                droppingSymbol = new LoopSymbol(event.scenePos().subtract(new QPointF(50, 25)),
                        new QSizeF(100, 50));
            else
                droppingSymbol = new BaseSymbol(event.scenePos().subtract(new QPointF(50, 25)),
                        new QSizeF(100, 50), data);

            droppingSymbol.setOpacity(0.5);

            this.addItem(droppingSymbol);
        } else {
            super.dragEnterEvent(event);
        }
    }

    @Override
    protected void dragMoveEvent(QGraphicsSceneDragDropEvent event) {
        if (event.mimeData().hasFormat(SymbolEnum.class.getName())) {
            droppingSymbol.setPos(event.scenePos().subtract(new QPointF(droppingSymbol.size.width() / 2,
                    droppingSymbol.size.height() / 2)));

            var connection = findUnderlyingConnection(event.scenePos());
            if (connection == boldConnection) return;
            else if (connection == null) {
                boldConnection.setBold(false);
                boldConnection = null;
            } else if (boldConnection == null) {
                boldConnection = connection;
                boldConnection.setBold(true);
            } else {
                boldConnection.setBold(false);
                boldConnection = connection;
                boldConnection.setBold(true);
            }

            return;
        }
        super.dragMoveEvent(event);
    }

    @Override
    protected void dropEvent(QGraphicsSceneDragDropEvent event) {
        if (boldConnection != null) {
            boldConnection.setBold(false);
            boldConnection = null;
        }

        if (event.mimeData().hasFormat(SymbolEnum.class.getName())) {
            droppingSymbol.setPos(event.scenePos().subtract(new QPointF(droppingSymbol.size.width() / 2,
                    droppingSymbol.size.height() / 2)));

            var outConnection = findUnderlyingConnection(event.scenePos());

            var prevInConnector = outConnection.getInConnector();

            if (droppingSymbol instanceof CaseSymbol) {
                MergeConnector mergeConnector = new MergeConnector();
                CaseSymbol symbol = (CaseSymbol) droppingSymbol;

                BaseConnection inConnection = new BaseConnection();
                prevInConnector.setConnection(inConnection);
                symbol.getInConnector().setConnection(inConnection);

                BaseConnection trueOutConnection = new BaseConnection();
                BaseConnection falseOutConnection = new BaseConnection();
                symbol.getTrueOutConnector().setConnection(trueOutConnection);
                symbol.getFalseOutConnector().setConnection(falseOutConnection);

                mergeConnector.getInConnector().setConnection(trueOutConnection);
                mergeConnector.getContinueInConnector().setConnection(falseOutConnection);
                mergeConnector.setTrueOutConnection(outConnection);

                this.addItem(inConnection);
                this.addItem(trueOutConnection);
                this.addItem(falseOutConnection);
            } else {
                if (droppingSymbol instanceof ITrueOutConnector)
                    ((ITrueOutConnector) droppingSymbol).getTrueOutConnector().setConnection(outConnection);
                var inConnection = new BaseConnection();
                    prevInConnector.setConnection(inConnection);
                if (droppingSymbol instanceof IInConnector)
                    ((IInConnector) droppingSymbol).getInConnector().setConnection(inConnection);
                if (droppingSymbol instanceof LoopSymbol) {
                    LoopSymbol symbol = (LoopSymbol) droppingSymbol;
                    BaseConnection continueConnection = new BaseConnection();
                    symbol.getFalseOutConnector().setConnection(continueConnection);
                    symbol.getContinueInConnector().setConnection(continueConnection);
                    this.addItem(continueConnection);
                }

                this.addItem(inConnection);
            }
            this.droppingSymbol.setOpacity(1);

        } else {
            super.dropEvent(event);
        }
    }

    private BaseConnection findUnderlyingConnection(QPointF pos) {
        var items = this.items(pos);
        for (var item : items) {
            if (item instanceof  BaseConnection) return (BaseConnection) item;
        }
        return null;
    }
}
