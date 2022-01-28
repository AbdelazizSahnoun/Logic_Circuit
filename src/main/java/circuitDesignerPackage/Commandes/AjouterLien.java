package circuitDesignerPackage.Commandes;


import circuitDesignerPackage.JswingComposantes.CircuitJLayredPane;
import circuitDesignerPackage.JswingComposantes.ConnecteurJLabel;
import circuitDesignerPackage.JswingComposantes.ConnexionJLabel;
import circuitDesignerPackage.Portes.ConnecteurType;

public class AjouterLien implements Commande {




    CircuitJLayredPane circuitJLayredPane;


    public AjouterLien(CircuitJLayredPane circuitJLayredPane) {
        this.circuitJLayredPane = circuitJLayredPane;
    }


    /*
    Dans la liaison des 2 composantes, il faut clicker 2 fois,une fois
    pour sélectionner la première composante et la deuxième fois pour sélectionner
    la deuxième composante, puis on les lit dans cette classe
    Les deux composantes sont mit dans un tableau :
    @connecteurs_slots : va contenir les 2 connecteurs des porte à lier
    @typeConnecteur : va contenir les types des ses connecteurs(ENTREE/SORTIE) afin
    de valider si on ne fait pas des connexions entree-entree par exemple
     */


    @Override
    public void execute(int x, int y) {



        //On s'assure déjà que les deux connecteurs sont pas null
        if (PreparerConnecteur.connecteurs_slots[0] != null
                && PreparerConnecteur.connecteurs_slots[1] != null) {

           //Ensuite on s'assure qu'on n'essaye pas de connecter les conencteurs
            //de la même porte ensemble
            if (PreparerConnecteur.connecteurs_slots[0].getComposantJLabel() !=
                    PreparerConnecteur.connecteurs_slots[1].getComposantJLabel()) {

                //ON s'assure ensuite qu'on  a un connecteur entree-sortie et non
                //sortie-sortie ou entree-entree
                if (!PreparerConnecteur.typeConnecteur[0].equals
                        (PreparerConnecteur.typeConnecteur[1])) {
                    //On positionne bien comme il le faut le connecteur
                    //sortie et entree dans pour créer une nouvelle connexion
                    // car la classe Connexion va utiliser la sortie d'une manière
                    //et l'entrée d'une autre

                    ConnecteurJLabel sortie;
                    ConnecteurJLabel entree;
                    if(PreparerConnecteur.typeConnecteur[0].equals(ConnecteurType.sortie)){
                        sortie=PreparerConnecteur.connecteurs_slots[1];
                        entree=PreparerConnecteur.connecteurs_slots[0];
                    }else {
                        sortie=PreparerConnecteur.connecteurs_slots[0];
                        entree=PreparerConnecteur.connecteurs_slots[1];
                    }
                    //On crée la connexion
                    ConnexionJLabel c1 = new ConnexionJLabel(entree.getComposantJLabel(),
                            sortie.getComposantJLabel(),
                            entree, sortie);

                    //si la connexion a bien été connecté, on incremente
                    //les valeurs de connexion qui sont important pour déterminer
                    //si le circuit est valide ou non
                    if(circuitJLayredPane.addnewConnection(c1)) {
                        PreparerConnecteur.connecteurs_slots[0].getComposantJLabel().incrementConnexions();
                        PreparerConnecteur.connecteurs_slots[1].getComposantJLabel().incrementConnexions();
                        circuitJLayredPane.IscircuitValide();

                    //les cas d'erreurs, on reset la connexion des 2 connecteurs
                    }else {
                        PreparerConnecteur.connecteurs_slots[0].resetConnexion();
                        PreparerConnecteur.connecteurs_slots[1].resetConnexion();
                    }
                } else {
                    PreparerConnecteur.connecteurs_slots[0].resetConnexion();
                    PreparerConnecteur.connecteurs_slots[1].resetConnexion();
                }
            } else {
                PreparerConnecteur.connecteurs_slots[0].resetConnexion();
                PreparerConnecteur.connecteurs_slots[1].resetConnexion();

            }

        }

        PreparerConnecteur.connecteurs_slots[0] = null;
        PreparerConnecteur.connecteurs_slots[1] = null;

    }
}
