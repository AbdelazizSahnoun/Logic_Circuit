package circuitDesignerPackage.Commandes;

import circuitDesignerPackage.JswingComposantes.CircuitJLayredPane;

import javax.swing.*;

public class ChangerText implements Commande {

    private JTextField nom_composante;
    CircuitJLayredPane circuitJLayredPane;

    public ChangerText(CircuitJLayredPane circuitJLayredPane,JTextField nom_composante) {
        this.circuitJLayredPane=circuitJLayredPane;
        this.nom_composante = nom_composante;
    }

    //la fonctionne valide l'input du textfield
    //et demande au circuit de changer le text de la composante
    //c'est le circuit qui le change si il n'y a pas de duplicats
    @Override
    public void execute(int x, int y) {

        String regex = "^[a-zA-Z0-9]+$";
        String text=this.nom_composante.getText();
        if(text.length()<=5&&text.matches(regex)) {
            this.circuitJLayredPane.changerText(this.circuitJLayredPane.getComposante(x, y)
                    , text);
        }else {
            System.out.println("Nom trop long ou ne respecte pas alphanumeric");
        }
    }
}
