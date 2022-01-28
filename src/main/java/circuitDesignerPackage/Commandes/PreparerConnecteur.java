package circuitDesignerPackage.Commandes;

import circuitDesignerPackage.JswingComposantes.CircuitJLayredPane;
import circuitDesignerPackage.JswingComposantes.ConnecteurJLabel;
import circuitDesignerPackage.Portes.ConnecteurType;

public class PreparerConnecteur implements Commande {

    //classe qui permet de déterminer quel connecteur a été clicke lors de la liaison
    //et de la préparer à se faire lier avec un autre connecteur dans la classe
    //AjouterLien

    CircuitJLayredPane circuitJLayredPane;
    static ConnecteurType[] typeConnecteur=new ConnecteurType[2];
    static ConnecteurJLabel[] connecteurs_slots=new ConnecteurJLabel[2];


    public PreparerConnecteur(CircuitJLayredPane circuitJLayredPane) {
        this.circuitJLayredPane = circuitJLayredPane;
    }

    @Override
    public void execute(int x, int y) {

        //le connecteur clicke a le boolean enConnexion en true
        ConnecteurJLabel connecteurJLabel=this.circuitJLayredPane.getConnecteurEnConnexion();
        if(connecteurJLabel!=null) {
            //si le slot 0 est pris, alors on donne le slot 1
            //et on desactive en connexion
            if (connecteurs_slots[0] == null) {
                connecteurs_slots[0] = connecteurJLabel;
                typeConnecteur[0] = connecteurJLabel.getConnecteurType();
                connecteurJLabel.desactivateEnconnexion();
            } else {
                connecteurs_slots[1] = connecteurJLabel;
                typeConnecteur[1] = connecteurJLabel.getConnecteurType();
                connecteurJLabel.desactivateEnconnexion();
            }
        }

    }
}
