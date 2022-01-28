package circuitDesignerPackage.Portes;
import javax.xml.bind.annotation.*;

 @XmlRootElement(name = "Connecteur")
 @XmlAccessorType(XmlAccessType.FIELD)
public class Connecteur {
     @XmlTransient
    private Composante actuelle;
    private Connexion connexion;
    private boolean enConnexion;
    private boolean disponible;
    private ConnecteurType connecteurType;
     @XmlTransient
    private Composante next;
     @XmlTransient
    private Composante precedant;
    
    public  Connecteur(){}
    public Connecteur(Composante actuelle,ConnecteurType connecteurType){

        this.actuelle=actuelle;
        this.disponible=true;
        this.connecteurType=connecteurType;
    }

    public ConnecteurType getConnecteurType() {
        return connecteurType;

    }

    //en mode en connexion pour savoir si une composante a été clické
    public int activateEnconnexion() {
        if(disponible) {
            this.enConnexion = true;
            return 0;
        }else {
            return -1;
        }
    }

    public void resetConnexion(){
        this.disponible=true;
        this.enConnexion=false;
    }

    public boolean getEnConnexion(){
        if(isDisponible()) {
            return enConnexion;
        }else
            return false;
    }

    public void desactivateEnconnexion() {
        this.enConnexion = false;
        this.disponible=false;

    }

    public void addConnection(Connexion c1){
        connexion =c1;
    }

    public boolean isDisponible() {
        return disponible;
    }


    public Composante getNext() {
        if(hasNext()) {
            return next;
        }else {
            return null;
        }
    }

    public void setNext(Composante next) {
        this.next = next;
    }

    public boolean hasNext(){
        return next!=null;
    }

    public boolean hasPrecedant(){
        return precedant!=null;
    }

    public void setPrecedant(Composante precedant) {
        this.precedant = precedant;
    }

    public Composante getPrecedant() {
        if(hasPrecedant()) {
            return precedant;
        }else {
            return null;
        }
    }

    public Composante getActuelle() {
        return actuelle;
    }
}
