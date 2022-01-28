package circuitDesignerPackage.JswingComposantes;

import circuitDesignerPackage.Portes.Connecteur;
import circuitDesignerPackage.Portes.ConnecteurType;

import javax.swing.*;
import javax.xml.bind.annotation.*;
import java.awt.*;

public class ConnecteurJLabel extends JLabel {

    private Connecteur connecteur;
    private ComposantJLabel composantJLabel;
    private ComposantJLabel next;
    private ComposantJLabel precedant;

    private int x, y;
    private int w = 20, h = 10;

    Shape shape;
    @XmlAnyElement
    Color color;

    public ConnecteurJLabel(){}
    public ComposantJLabel getComposantJLabel() {
        return composantJLabel;
    }

    public ConnecteurJLabel(Connecteur connecteur, ComposantJLabel composantJLabel) {
        this.connecteur = connecteur;
        this.composantJLabel=composantJLabel;
        Initalize();
    }

    private void Initalize(){

        Polygon p = new Polygon();
        p.addPoint(x, y - h / 2);
        p.addPoint(x, y + h / 2);
        p.addPoint(x + w, y);
        shape = p;
        color = Color.blue;
    }

    public void paintConnector(Graphics2D g2d) {
        if (connecteur.isDisponible()) {
            g2d.setColor(color);
        } else {
            g2d.setColor(Color.red);
        }
        g2d.fill(shape);

    }

    public ComposantJLabel getNext() {
        if(hasNext()) {
            return next;
        }else {
            return null;
        }
    }

    public void setNext(ComposantJLabel next) {
        this.next = next;
    }

    public boolean hasNext(){
        return next!=null;
    }

    public boolean hasPrecedant(){
        return precedant!=null;
    }

    public void setPrecedant(ComposantJLabel precedant) {
        this.precedant = precedant;
    }

    public ComposantJLabel getPrecedant() {
        if(hasPrecedant()) {
            return precedant;
        }else {
            return null;
        }
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
        Initalize();
    }

    public void setY(int y) {
        this.y = y;
        Initalize();
    }

    public void activateEnconnexion() {
        this.connecteur.activateEnconnexion();
    }

    public void resetConnexion(){ this.connecteur.resetConnexion();}

    public boolean getEnConnexion(){
        return this.connecteur.getEnConnexion();
    }

    public ConnecteurType getConnecteurType(){
        return this.connecteur.getConnecteurType();
    }


    public void desactivateEnconnexion(){
        this.connecteur.desactivateEnconnexion();
    }

    public boolean isDisponible(){
        return this.connecteur.isDisponible();
    }

    public Connecteur getConnecteur() {
        return connecteur;
    }
}
