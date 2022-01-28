package circuitDesignerPackage.JswingComposantes;


import circuitDesignerPackage.Portes.*;


import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

public class CircuitJLayredPane extends JLayeredPane {


    private Circuit circuit;
    private List<ComposantJLabel> composantJLabelList;
    private List<ComposantJLabel> entrees;
    private List<ComposantJLabel> sorties;
    private List<ConnexionJLabel> conexionJLabelList;

    //permet de met a jour la table de verite a chaque changement
    private final PropertyChangeSupport news;
    private boolean circuit_a_change=false;

    private int nb_entree=0;
    private int nb_sortie=0;

    public int getNb_entree() {
        return nb_entree;
    }

    public int getNb_sortie() {
        return nb_sortie;
    }

    public CircuitJLayredPane() {
        super();
        this.circuit = new Circuit();
        entrees=new ArrayList<>();
        sorties=new ArrayList<>();
        conexionJLabelList=new ArrayList<>();
        composantJLabelList=new ArrayList<>();
        this.news=new PropertyChangeSupport(this.circuit);
    }

    public boolean addnewConnection(ConnexionJLabel connexionJLabel){

        //On ajoute les connexion en double,un pour le jlabel l'autre pour le circuit

        Connexion connexion=new Connexion
                (connexionJLabel.getConnecteurEntree().getConnecteur().getActuelle(),
                        connexionJLabel.getConnecteurSortie().getConnecteur().getActuelle(),
                        connexionJLabel.getConnecteurEntree().getConnecteur(),
                        connexionJLabel.getConnecteurSortie().getConnecteur());
        circuit.addnewConnection(connexion);


        connexionJLabel.getConnecteurEntree().setNext(connexionJLabel.getSortie());
        connexionJLabel.getConnecteurSortie().setPrecedant(connexionJLabel.getEntree());
        connexion.getConnecteurEntree().setNext(connexion.getSortie());
        connexion.getConnecteurSortie().setPrecedant(connexion.getEntree());

        conexionJLabelList.add(connexionJLabel);
        this.repaint();
        this.sendUpdate();
        return true;
    }


    public Circuit getCircuit() {
        return circuit;
    }

    public boolean ajouter(ComposantJLabel c1){


        if(c1.getTypePorte()==PorteType.ENTREE){
            if(nb_entree<5) {
                nb_entree++;
                circuit.ajouter(c1.getComposante());
            }else {
                System.out.println("Trop d'entree max:5");
                return false;
            }
        }else if(c1.getTypePorte()==PorteType.SORTIE){
            if(nb_sortie<5) {
                nb_sortie++;
                circuit.ajouter(c1.getComposante());
            }else {
                System.out.println("Trop de sortie max:5");
                return false;
            }
        }else {
            circuit.ajouter(c1.getComposante());
        }
        if(composantJLabelList.size()<=50) {
            this.add(c1);
            composantJLabelList.add(c1);
            if(c1.getComposante().getPorteType()== PorteType.ENTREE){
                entrees.add(c1);
            }else if (c1.getComposante().getPorteType()== PorteType.SORTIE){
                sorties.add(c1);
            }
            this.sendUpdate();
            return true;
        }else {
            System.out.println("Trop de portes max:50");
            return false;
        }

    }


    public void supprimerByXY(int x,int y){

        ComposantJLabel c1=getComposante(x, y);

        if(c1!=null) {

            if (c1.getTypePorte() == PorteType.ENTREE) {
                if (nb_entree != 1) {
                    nb_entree--;
                } else {
                    System.out.println("On ne peut supprimer l'unique entrée du circuit");
                    return;
                }
            } else if (c1.getTypePorte() == PorteType.SORTIE) {
                if (nb_sortie != 1) {
                    nb_sortie--;
                } else {
                    System.out.println("On ne peut supprimer l'unique sortie du circuit");
                    return;
                }
            }
        }

        supprimer(c1);
    }

    public void supprimer(ComposantJLabel c1){
        if(c1!=null) {
            conexionJLabelList.removeIf(connec -> connec.removeConnectionFromComponent(c1));
            composantJLabelList.remove(c1);
            c1.updateConnexionInConnecteur();
            this.circuit.getConnexions().removeIf(connexion ->
                    connexion.removeConnectionFromComponent(c1.getComposante()));
            this.circuit.supprimer(c1.getComposante());
            if (c1.getTypePorte() == PorteType.ENTREE) {
                entrees.remove(c1);
            } else if (c1.getTypePorte() == PorteType.SORTIE) {
                sorties.remove(c1);
            }
            this.remove(c1);
            this.repaint();
            this.sendUpdate();
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        for (ConnexionJLabel c1: conexionJLabelList
        ) {

            g2d.drawLine(c1.getConnecteurEntree().getX()+c1.getEntree().getX(),
                     c1.getConnecteurEntree().getY()+c1.getEntree().getY()
                    , c1.getConnecteurSortie().getX()+c1.getSortie().getX()
                    , c1.getConnecteurSortie().getY()+c1.getSortie().getY()
              );



        }


    }

    public ComposantJLabel getComposante(int x,int y){
        ComposantJLabel c1=null;
        for (ComposantJLabel composantJLabel: composantJLabelList
        ) {

            if(composantJLabel.getX()==x&&composantJLabel.getY()==y){
                c1=composantJLabel;
                break;
            }

        }

        return c1;
    }


    public ConnecteurJLabel getConnecteurEnConnexion(){
        ConnecteurJLabel connecteurJLabel=null;
        for (ComposantJLabel porte : composantJLabelList
        ) {


            if(porte.getRightConnecteur()!=null){
                    return porte.getRightConnecteur();
            }


        }

        return connecteurJLabel;
    }







    public boolean changerText(ComposantJLabel c1,String text){
        if(c1.getTypePorte()!=PorteType.ENTREE||c1.getTypePorte()!=PorteType.SORTIE) {
            boolean text_deja_existant = false;
            for (ComposantJLabel c : composantJLabelList
            ) {
                if (c.getText().equals(text)) {
                    text_deja_existant = true;
                    break;
                }

            }
            if (!text_deja_existant) {
                c1.setComponentName(text);
                return true;
            } else {
                return false;
            }
        }else {
            return false;
        }

    }


    public boolean IscircuitValide(){
        return this.circuit.IscircuitValide();
    }

    public TableVeriteModel getTableVerite(){
        TableVerite tableVerite=new TableVerite(this.circuit.getComposantes(),this.circuit.getEntrees(),circuit.IscircuitValide());
        this.circuit.speedCalculValeurTableVerite(1,0);
        return new TableVeriteModel(tableVerite);

    }

    public TableVeriteModel updateTruthTable(){
        TableVerite tableVerite=new TableVerite(this.circuit.getComposantes(),this.circuit.getEntrees(),circuit.IscircuitValide());
        return new TableVeriteModel(tableVerite);
    }


    public void ajouterPropertyChangeListener(PropertyChangeListener propertyChangeListener) {
        news.addPropertyChangeListener(propertyChangeListener);
    }

    public void deletePropertyChangeListener(PropertyChangeListener propertyChangeListener) {
        news.removePropertyChangeListener(propertyChangeListener);
    }

    public void sendUpdate() {
        news.firePropertyChange("updateTruthTable", java.util.Optional.of(this.circuit_a_change), true);
    }









}
