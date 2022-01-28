package circuitDesignerPackage.Commandes;

import circuitDesignerPackage.JswingComposantes.CircuitJLayredPane;
import circuitDesignerPackage.JswingComposantes.ComposantJLabel;
import circuitDesignerPackage.Operations.Operateur;
import circuitDesignerPackage.Operations.OperateurNOT;
import circuitDesignerPackage.Portes.Composante;
import circuitDesignerPackage.Portes.Porte;
import circuitDesignerPackage.Portes.PorteType;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;


public class AddPorteNOT implements Commande{

    final static String PATH_ICON_NOT= "src/main/resources/images/not.jpg";
    CircuitJLayredPane circuitJLayredPane;

    public AddPorteNOT(CircuitJLayredPane circuitJLayredPane) {
        this.circuitJLayredPane=circuitJLayredPane;
    }




    public void execute(int x,int y){
        Operateur operateurNOT=new OperateurNOT();
        Porte porte=new Porte(operateurNOT,PorteType.NOT);

        Composante composante=new Composante(porte,
                1,1,"NOT");
        ComposantJLabel composantJLabel=new ComposantJLabel("NOT",
                new ImageIcon(PATH_ICON_NOT),composante);

        composantJLabel.setVerticalAlignment(SwingConstants.CENTER);
        composantJLabel.setBounds(x, y, 70, 50);
        composantJLabel.setBorder(new LineBorder(Color.black, 1));
        composantJLabel.setVisible(true);
        this.circuitJLayredPane.ajouter(composantJLabel);
    }


}
