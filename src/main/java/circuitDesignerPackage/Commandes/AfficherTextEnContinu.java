package circuitDesignerPackage.Commandes;

import circuitDesignerPackage.JswingComposantes.CircuitJLayredPane;
import circuitDesignerPackage.JswingComposantes.ComposantJLabel;

import javax.swing.*;

public class AfficherTextEnContinu implements Commande {

    JTextField textField;
    CircuitJLayredPane circuitJLayredPane;

    public AfficherTextEnContinu(CircuitJLayredPane circuitJLayredPane,JTextField textField) {
        this.circuitJLayredPane=circuitJLayredPane;
        this.textField = textField;
    }

    //On obient la composante cliqué et on change le textfield avec
    //le nom de la composante
    @Override
    public void execute(int x, int y) {
        ComposantJLabel c1=this.circuitJLayredPane.getComposante(x, y);
        if(c1!=null) {
            this.textField.setText(c1.getText());
        }
    }
}
