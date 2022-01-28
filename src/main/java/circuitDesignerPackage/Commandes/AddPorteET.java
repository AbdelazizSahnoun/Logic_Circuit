package circuitDesignerPackage.Commandes;

import circuitDesignerPackage.JswingComposantes.CircuitJLayredPane;
import circuitDesignerPackage.JswingComposantes.ComposantJLabel;
import circuitDesignerPackage.Operations.Operateur;
import circuitDesignerPackage.Operations.OperateurAND;
import circuitDesignerPackage.Portes.Composante;
import circuitDesignerPackage.Portes.Porte;
import circuitDesignerPackage.Portes.PorteType;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class AddPorteET implements Commande{

    final static String PATH_ICON_AND = "src/main/resources/images/and.jpg";
    CircuitJLayredPane circuitJLayredPane;

    public AddPorteET(CircuitJLayredPane circuitJLayredPane) {
        this.circuitJLayredPane=circuitJLayredPane;
    }


    public void execute(int x,int y){
        Operateur operateurAND=new OperateurAND();
        Porte porte=new Porte(operateurAND,PorteType.AND);


        Composante composante=new Composante(porte
                ,2,1,"AND");
        ComposantJLabel composantJLabel=new ComposantJLabel("AND",
                new ImageIcon(PATH_ICON_AND),composante);

        composantJLabel.setVerticalAlignment(SwingConstants.CENTER);
        composantJLabel.setBounds(x, y, 70, 50);
        composantJLabel.setBorder(new LineBorder(Color.black, 1));
        composantJLabel.setVisible(true);
        this.circuitJLayredPane.ajouter(composantJLabel);

    }


}
