package circuitDesignerPackage.Commandes;

import circuitDesignerPackage.JswingComposantes.CircuitJLayredPane;
import circuitDesignerPackage.JswingComposantes.ComposantJLabel;
import circuitDesignerPackage.Operations.Operateur;
import circuitDesignerPackage.Operations.OperateurOR;
import circuitDesignerPackage.Portes.Composante;
import circuitDesignerPackage.Portes.Porte;
import circuitDesignerPackage.Portes.PorteType;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;

public class AddPorteOU implements Commande{

    final static String PATH_ICON_OR= "src/main/resources/images/or.jpg";
    CircuitJLayredPane circuitJLayredPane;

    public AddPorteOU(CircuitJLayredPane circuitJLayredPane) {
        this.circuitJLayredPane=circuitJLayredPane;
    }




    public void execute(int x,int y){
        Operateur operateurOR=new OperateurOR();
        Porte porte=new Porte(operateurOR,PorteType.OR);

        Composante composante=new Composante(porte,
                2,1,"OU");
        ComposantJLabel composantJLabel=new ComposantJLabel("OR",
                new ImageIcon(PATH_ICON_OR),composante);

        composantJLabel.setVerticalAlignment(SwingConstants.CENTER);
        composantJLabel.setBounds(x, y, 70, 50);
        composantJLabel.setBorder(new LineBorder(Color.black, 1));
        composantJLabel.setVisible(true);
        this.circuitJLayredPane.ajouter(composantJLabel);
    }


}
