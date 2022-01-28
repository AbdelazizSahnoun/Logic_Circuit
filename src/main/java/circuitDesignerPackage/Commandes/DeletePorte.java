package circuitDesignerPackage.Commandes;

import circuitDesignerPackage.JswingComposantes.CircuitJLayredPane;

public class DeletePorte implements Commande {

    CircuitJLayredPane circuitJLayredPane;

    public DeletePorte(CircuitJLayredPane circuitJLayredPane) {
        this.circuitJLayredPane = circuitJLayredPane;

    }

    @Override
    public void execute(int x, int y) {
        this.circuitJLayredPane.supprimerByXY(x,y);

    }
}
