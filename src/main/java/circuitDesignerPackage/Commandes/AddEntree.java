package circuitDesignerPackage.Commandes;

import circuitDesignerPackage.JswingComposantes.CircuitJLayredPane;
import circuitDesignerPackage.JswingComposantes.ComposantJLabel;
import circuitDesignerPackage.Operations.EntreeSortie;
import circuitDesignerPackage.Operations.Operateur;
import circuitDesignerPackage.Portes.Composante;
import circuitDesignerPackage.Portes.Porte;
import circuitDesignerPackage.Portes.PorteType;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;


public class AddEntree implements Commande{

    final static String PATH_ICON_ENTREE = "src/main/resources/images/entree.jpg";

    CircuitJLayredPane circuitJLayredPane;

    public AddEntree(CircuitJLayredPane circuitJLayredPane) {
        this.circuitJLayredPane=circuitJLayredPane;
    }


    public void execute(int x,int y){

            Operateur entreeSortie = new EntreeSortie();
            Porte porte = new Porte(entreeSortie, PorteType.ENTREE);
            Composante composante = new Composante(porte, 0,
                    2,("E" + (circuitJLayredPane.getNb_entree()+1)));
            ComposantJLabel composantJLabel = new ComposantJLabel
                    (("E" + (circuitJLayredPane.getNb_entree()+1)),
                            new ImageIcon(PATH_ICON_ENTREE), composante);

            composantJLabel.setVerticalAlignment(SwingConstants.CENTER);
            composantJLabel.setBounds(x, y, 70, 50);
            composantJLabel.setBorder(new LineBorder(Color.black, 1));
            composantJLabel.setVisible(true);
            this.circuitJLayredPane.ajouter(composantJLabel);

    }


}
