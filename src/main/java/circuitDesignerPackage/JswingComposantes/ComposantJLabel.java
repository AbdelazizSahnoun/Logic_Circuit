package circuitDesignerPackage.JswingComposantes;


import circuitDesignerPackage.Portes.Composante;
import circuitDesignerPackage.Portes.PorteType;

import javax.swing.*;
import javax.xml.bind.annotation.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;


@XmlRootElement(name = "ComposantJLabel")
@XmlAccessorType(XmlAccessType.NONE)
public class ComposantJLabel extends JLabel implements MouseListener, MouseMotionListener {


    //classe qui représente une composante en jlabel

    private Composante composante;
    private ConnecteurJLabel[] entres;
    private ConnecteurJLabel[] sorties;
    private int startDragX, startDragY;
    private boolean inDrag = false;


    public ComposantJLabel(String text, ImageIcon icon, Composante composante) {

        super(text, icon,JLabel.CENTER);
        this.composante = composante;
        this.setName(text);
        addMouseListener(this);
        addMouseMotionListener(this);

        //on intialise les connecteurs
        if(composante.getNb_conecteur_entree()!=0) {
            entres = new ConnecteurJLabel[composante.getNb_conecteur_entree()];
            for(int i=0;i<composante.getNb_conecteur_entree();i++){
                entres[i]=new ConnecteurJLabel(composante.getEntres()[i],this);
            }
        }
        if(composante.getNb_connecteur_sortie()!=0) {
            sorties = new ConnecteurJLabel[composante.getNb_connecteur_sortie()];
            for(int i=0;i<composante.getNb_connecteur_sortie();i++){
                sorties[i]=new ConnecteurJLabel(composante.getSorties()[i],this);
            }
        }


    }


    public void incrementConnexions(){
        this.composante.incrementConnexion();
    }
    public void decrementConnexions(){
        this.composante.decrementConnexion();
    }

    @Override
    public void paintComponent(Graphics g) {
        // dessine les connecteurs
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        if(entres!=null) {
            entres[0].setX(0);
            entres[0].setY(6);
            for (int i = 1; i < entres.length; i++) {
                entres[i].setY(getHeight() - 6 * i);
            }
            for (ConnecteurJLabel entre : entres) {
                entre.paintConnector(g2d);
            }
        }

        if(sorties!=null) {
            sorties[0].setX(getWidth()-20);
            sorties[0].setY(6);
            for (int i = 1; i < sorties.length; i++) {
                sorties[i].setX(getWidth()-20);
                sorties[i].setY(getHeight() - 6*i);

            }
            for (ConnecteurJLabel sortie : sorties) {
                sortie.paintConnector(g2d);
            }
        }



    }

    public Composante getComposante() {
        return composante;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        //permet d'activer les connecteurs lorsqu'on clicke dessus afin
        // eventuellement les liers

        if(entres!=null) {
            for (ConnecteurJLabel entre : entres) {
                if (Math.abs(entre.getX() - e.getX()) < 25 &&
                        Math.abs(entre.getY() - e.getY()) < 25) {
                    System.out.println("EnConnexion");
                    entre.activateEnconnexion();
                    System.out.println(this.getText());
                }
            }
        }

        if(sorties!=null) {
            for (ConnecteurJLabel sorty : sorties) {
                if (Math.abs(sorty.getX() - e.getX()) < 25 &&
                        Math.abs(sorty.getY() - e.getY()) < 25) {
                    System.out.println("EnConnexion");
                    sorty.activateEnconnexion();
                    System.out.println(this.getText());
                }
            }
        }



        int x_parent=this.getLocation().x;
        int y_parent=this.getLocation().y;

        MouseEvent parentMouseEvent =new MouseEvent(this, MouseEvent.MOUSE_CLICKED, e.getWhen(), e.getModifiersEx(),x_parent, y_parent, e.getClickCount(), false);
        this.getParent().dispatchEvent(parentMouseEvent);
        //System.out.println(this.getParent());

    }

    //donne le connecteur qui en connexion actuellement
    public ConnecteurJLabel getRightConnecteur(){

        if(entres!=null) {
            for (ConnecteurJLabel entre : entres) {
              if(entre.getEnConnexion()){
                  return entre;
              }
            }
        }

        if(sorties!=null) {
            for (ConnecteurJLabel sorty : sorties) {
                if(sorty.getEnConnexion()){
                    return sorty;
                }
            }
        }

        return null;

    }






    @Override
    public void mousePressed(MouseEvent e) {
        startDragX = e.getX();
        startDragY = e.getY();

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (inDrag) {
            inDrag = false;
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public ConnecteurJLabel[] getEntres() {
        return entres;
    }

    public ConnecteurJLabel[] getSorties() {
        return sorties;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        int newX = getX() + (e.getX() - startDragX);
        int newY = getY() + (e.getY() - startDragY);
        setLocation(newX, newY);
        inDrag = true;
        this.getParent().repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }


    public PorteType getTypePorte(){
        return this.composante.getPorteType();
    }


    public void setComponentName(String component_name) {
        this.setName(component_name);
        this.setText(component_name);
        this.composante.setName(component_name);
        this.repaint();
    }



    //met a jour le nombre de connexion sur un connecteur
    public void updateConnexionInConnecteur(){
        if(entres!=null) {
            for (ConnecteurJLabel connecteur : entres
            ) {
                if(connecteur.hasPrecedant()) {
                    connecteur.getPrecedant().decrementConnexions();
                }
            }

        }
        if(sorties!=null) {
            for (ConnecteurJLabel connecteur : sorties
            ) {
                if(connecteur.hasNext()) {
                    connecteur.getNext().decrementConnexions();
                }
            }
        }

    }
}
