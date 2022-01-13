package ui.graph.symbols;

import ui.graph.connections.BaseConnection;

public class PlacableSymbol extends BaseSymbol {
    private BaseConnection inConnection;
    private BaseConnection outConnection;

    public void setInConnection(BaseConnection inConnection) {
        this.inConnection = inConnection;
        this.inConnection.setOutSymbol(this);
    }

    public void setOutConnection(BaseConnection outConnection) {
        this.outConnection = outConnection;
        this.outConnection.setInSymbol(this);
    }
}
