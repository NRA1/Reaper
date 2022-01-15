package ui.graph.symbols;

import ui.graph.connections.BaseConnection;

public class PlacableSymbol extends BaseSymbol {
    private BaseConnection inConnection;
    private BaseConnection outConnection;

    public void setInConnection(BaseConnection inConnection) {
        this.inConnection = inConnection;
        this.inConnection.setOutSymbol(this);
    }

    public BaseConnection getInConnection() {
        return inConnection;
    }

    public BaseConnection getOutConnection() {
        return outConnection;
    }

    public void setOutConnection(BaseConnection outConnection) {
        this.outConnection = outConnection;
        this.outConnection.setInSymbol(this);
    }

    @Override
    protected void UpdatePosition() {
        if (inConnection != null) {
            inConnection.update();
        }
        if (outConnection != null) {
            outConnection.UpdatePos();
        }
    }
}
